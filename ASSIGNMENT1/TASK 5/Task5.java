class TicketCounter {
    private static int nextTicket = 1000;
    private final int ticketNo;
    TicketCounter() { ticketNo = ++nextTicket; }
    int getTicketNo() { return ticketNo; }
    static int getLastIssuedNumber() { return nextTicket; }
}
public class Task5 {
    public static void main(String[] args) {
        TicketCounter north = new TicketCounter();
        TicketCounter south = new TicketCounter();
        System.out.println("North ticket: " + north.getTicketNo());
        System.out.println("South ticket: " + south.getTicketNo());
        System.out.println("Last issued: " + TicketCounter.getLastIssuedNumber());
    }
}