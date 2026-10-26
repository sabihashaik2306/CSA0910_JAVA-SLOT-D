import java.util.*;

class Vehicle {
    String regNo;
    String type;
    long entryTime;
    Vehicle(String regNo, String type, long entryTime) {
        this.regNo = regNo; this.type = type; this.entryTime = entryTime;
    }
}
class ParkingSlot {
    int floor, number;
    Vehicle vehicle;
    ParkingSlot(int floor, int number) { this.floor = floor; this.number = number; }
    boolean isFree() { return vehicle == null; }
}
class ParkingTicket {
    static int nextId = 1000;
    final int ticketNo = ++nextId;
    final Vehicle vehicle;
    final long entryTime;
    ParkingTicket(Vehicle vehicle, long entryTime) {
        this.vehicle = vehicle; this.entryTime = entryTime;
    }
}
public class Task1 {
    public static void main(String[] args) {
        Vehicle v = new Vehicle("TN01AB1111", "Car", System.currentTimeMillis());
        ParkingSlot s = new ParkingSlot(0, 1);
        s.vehicle = v;
        ParkingTicket t = new ParkingTicket(v, v.entryTime);
        System.out.println("Vehicle: " + v.regNo + " (" + v.type + ")");
        System.out.println("Slot: floor " + s.floor + ", slot " + s.number);
        System.out.println("Ticket: " + t.ticketNo);
        System.out.println("Slot occupied: " + !s.isFree());
    }
}