import java.io.*;

public class Task03 {
    static void generateWithTryResources(File input, File output) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(input));
             PrintWriter writer = new PrintWriter(new FileWriter(output))) {
            String line;
            int total = 0;
            while ((line = reader.readLine()) != null) {
                total += Integer.parseInt(line.trim());
            }
            writer.println("Daily sales total: " + total);
        }
    }

    static void generateManual(File input, File output) throws IOException {
        BufferedReader reader = null;
        PrintWriter writer = null;
        try {
            reader = new BufferedReader(new FileReader(input));
            writer = new PrintWriter(new FileWriter(output));
            String line;
            int total = 0;
            while ((line = reader.readLine()) != null) total += Integer.parseInt(line.trim());
            writer.println("Daily sales total: " + total);
        } finally {
            if (writer != null) writer.close();
            if (reader != null) reader.close();
        }
    }

    public static void main(String[] args) throws Exception {
        File input = new File("sales.txt");
        try (PrintWriter p = new PrintWriter(new FileWriter(input))) {
            p.println("120"); p.println("250"); p.println("330");
        }
        File safe = new File("report_try_resources.txt");
        File manual = new File("report_manual.txt");
        generateWithTryResources(input, safe);
        generateManual(input, manual);
        System.out.println("Try-with-resources report: " + readFirstLine(safe));
        System.out.println("Manual-close report: " + readFirstLine(manual));
        System.out.println("Both reports match: " + readFirstLine(safe).equals(readFirstLine(manual)));
        System.out.println("Resources are closed automatically by try-with-resources, including on exceptions.");
    }
    static String readFirstLine(File f) throws IOException {
        try (BufferedReader r = new BufferedReader(new FileReader(f))) { return r.readLine(); }
    }
}
