import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class Task05 {
    static class SeatBooking {
        private final boolean[] booked;
        private int bookingsMade = 0;
        SeatBooking(int seats) { booked = new boolean[seats]; }

        public synchronized void book(int seat) {
            if (!booked[seat]) {
                booked[seat] = true;
                bookingsMade++;
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        SeatBooking screen = new SeatBooking(10);
        Thread[] users = new Thread[100];
        for (int i = 0; i < users.length; i++) {
            final int seat = i % 10;
            users[i] = new Thread(() -> screen.book(seat));
            users[i].start();
        }
        for (Thread t : users) t.join();
        System.out.println("Users competing: 100");
        System.out.println("Available seats: 10");
        System.out.println("Seats sold: " + screen.bookingsMade);
        System.out.println("Double booking prevented: " + (screen.bookingsMade == 10));
    }
}
