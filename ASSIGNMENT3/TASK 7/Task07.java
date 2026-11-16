import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;

public class Task07 {
    static class Result {
        final String file;
        final boolean success;
        final int score;
        final String message;
        Result(String file, boolean success, int score, String message) {
            this.file=file; this.success=success; this.score=score; this.message=message;
        }
    }
    static class ScoreTask implements Callable<Result> {
        private final Path path;
        ScoreTask(Path path) { this.path=path; }
        public Result call() {
            try {
                String value = Files.readString(path).trim();
                int score = Integer.parseInt(value);
                if (score < 0 || score > 100) throw new IllegalArgumentException("Score outside 0-100");
                return new Result(path.getFileName().toString(), true, score, "OK");
            } catch (Exception e) {
                return new Result(path.getFileName().toString(), false, 0, e.getClass().getSimpleName());
            }
        }
    }
    public static void main(String[] args) throws Exception {
        Path dir = Paths.get("score_files");
        Files.createDirectories(dir);
        for (int i=1;i<=8;i++) Files.writeString(dir.resolve("student"+i+".txt"), Integer.toString(60+i));
        Files.writeString(dir.resolve("corrupt.txt"), "not-a-number");
        // missing.txt is intentionally not created to test missing-file handling.
        List<Path> inputs = new ArrayList<>();
        try (DirectoryStream<Path> ds = Files.newDirectoryStream(dir, "*.txt")) {
            for (Path p : ds) inputs.add(p);
        }
        inputs.add(dir.resolve("missing.txt"));
        System.out.println("SEMESTER RESULT PROCESSING");
        for (int poolSize : new int[]{1,2,4,8}) {
            ExecutorService pool = Executors.newFixedThreadPool(poolSize);
            List<Future<Result>> futures = new ArrayList<>();
            long start = System.nanoTime();
            for (Path p : inputs) futures.add(pool.submit(new ScoreTask(p)));
            int success=0, failure=0, total=0;
            for (Future<Result> f : futures) {
                Result r = f.get();
                if (r.success) { success++; total += r.score; }
                else { failure++; System.out.println("Handled " + r.file + ": " + r.message); }
            }
            pool.shutdown();
            pool.awaitTermination(10, TimeUnit.SECONDS);
            long elapsed = System.nanoTime()-start;
            System.out.println("Pool=" + poolSize + " | success=" + success + " | failure=" + failure
                + " | scoreTotal=" + total + " | elapsedNs=" + elapsed);
        }
        System.out.println("Summary: 8 successful files and 2 failed files (corrupt + missing).");
    }
}
