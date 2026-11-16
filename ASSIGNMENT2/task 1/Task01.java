public class Task01 {
    static String buildPlus(int fields) {
        String s = "";
        for (int i = 0; i < fields; i++) s += "FIELD" + i + "|";
        return s;
    }
    static String buildBuilder(int fields) {
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < fields; i++) b.append("FIELD").append(i).append("|");
        return b.toString();
    }
    static String buildBuffer(int fields) {
        StringBuffer b = new StringBuffer();
        for (int i = 0; i < fields; i++) b.append("FIELD").append(i).append("|");
        return b.toString();
    }
    static long measure(int mode, int fields, int repetitions) {
        long start = System.nanoTime();
        long checksum = 0;
        for (int r = 0; r < repetitions; r++) {
            String s = mode == 0 ? buildPlus(fields) :
                       mode == 1 ? buildBuilder(fields) : buildBuffer(fields);
            checksum += s.length();
        }
        long elapsed = System.nanoTime() - start;
        if (checksum == 0) System.out.println(" ");
        return elapsed / repetitions;
    }
    public static void main(String[] args) {
        int[][] workloads = {{5, 100}, {50, 1000}, {200, 500}};
        String[] names = {"Short ticket", "10,000-field workload (reduced repetitions)", "Large ticket"};
        for (int w = 0; w < workloads.length; w++) {
            int fields = workloads[w][0], reps = workloads[w][1];
            System.out.println("\\n" + names[w] + " | fields=" + fields + ", repetitions=" + reps);
            System.out.println("String (+) avg ns: " + measure(0, fields, reps));
            System.out.println("StringBuilder avg ns: " + measure(1, fields, reps));
            System.out.println("StringBuffer avg ns: " + measure(2, fields, reps));
        }
        System.out.println("\\nRecommendation: StringBuilder for a local ticket; StringBuffer or synchronized logging for a shared mutable log.");
        System.out.println("Complexity: repeated concatenation may copy O(n^2) characters; StringBuilder append is amortized O(n).");
    }
}
