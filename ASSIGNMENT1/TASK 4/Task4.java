class ParkingTicket {
    private final int ticketNo;
    private final String regNo;
    private final long entryTime;

    ParkingTicket() {
        this(0, "UNKNOWN", System.currentTimeMillis());
    }
    ParkingTicket(int ticketNo, String regNo) {
        this(ticketNo, regNo, System.currentTimeMillis());
    }
    ParkingTicket(int ticketNo, String regNo, long entryTime) {
        if (regNo == null || regNo.isBlank()) throw new IllegalArgumentException("Registration required");
        this.ticketNo = ticketNo;
        this.regNo = regNo;
        this.entryTime = entryTime;
    }
    public String toString() {
        return "Ticket " + ticketNo + " for " + regNo + " at " + entryTime;
    }
}
public class Task4 {
    public static void main(String[] args) {
        ParkingTicket a = new ParkingTicket();
        ParkingTicket b = new ParkingTicket(1001, "TN01AB1111");
        ParkingTicket c = new ParkingTicket(1002, "TN02CD2222", 1761450000000L);
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }
}