public class Task04 {
    static class ScannerStation extends Thread {
        private final String station;
        private final int scripts;
        ScannerStation(String station, int scripts) { this.station = station; this.scripts = scripts; }
        public void run() {
            try {
                System.out.println(station + " started");
                Thread.sleep(100 + (int)(Math.random() * 250));
                System.out.println(station + " scanned " + scripts + " scripts");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(station + " interrupted");
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        System.out.println("ANSWER-SCRIPT SCANNER STATIONS");
        Thread[] stations = {
            new ScannerStation("Station-1", 25), new ScannerStation("Station-2", 30),
            new ScannerStation("Station-3", 20), new ScannerStation("Station-4", 25)
        };
        for (Thread t : stations) t.start();
        for (Thread t : stations) t.join();
        System.out.println("All stations completed. Total scripts scanned: 100");
    }
}
