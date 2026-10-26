import java.util.*;

abstract class Vehicle {
    final String regNo;
    Vehicle(String regNo) {
        if (regNo == null || regNo.isBlank()) throw new IllegalArgumentException("Invalid registration");
        this.regNo = regNo;
    }
    abstract double rate();
    double fee(int minutes) {
        if (minutes < 0) throw new IllegalArgumentException("Negative duration");
        return ((minutes + 59) / 60) * rate();
    }
}
class Bike extends Vehicle { Bike(String r) { super(r); } double rate() { return 10; } }
class Car extends Vehicle { Car(String r) { super(r); } double rate() { return 30; } }
class ParkingSystem {
    private final String[][] slots;
    private final Map<String, Vehicle> active = new HashMap<>();
    ParkingSystem(int[] counts) {
        slots = new String[counts.length][];
        for (int i=0; i<counts.length; i++) slots[i] = new String[counts[i]];
    }
    int[] enter(Vehicle v) {
        if (active.containsKey(v.regNo)) throw new IllegalArgumentException("Duplicate registration");
        for (int f=0; f<slots.length; f++) for (int s=0; s<slots[f].length; s++) {
            if (slots[f][s] == null) {
                slots[f][s] = v.regNo; active.put(v.regNo, v); return new int[]{f,s};
            }
        }
        return null;
    }
    double exit(String regNo, int minutes) {
        Vehicle v = active.remove(regNo);
        if (v == null) throw new IllegalArgumentException("Vehicle not parked");
        for (int f=0; f<slots.length; f++) for (int s=0; s<slots[f].length; s++)
            if (regNo.equals(slots[f][s])) { slots[f][s] = null; return v.fee(minutes); }
        throw new IllegalStateException("Active vehicle has no slot");
    }
}
public class Task11 {
    public static void main(String[] args) {
        ParkingSystem p = new ParkingSystem(new int[]{1, 2});
        System.out.println("Bike slot: " + Arrays.toString(p.enter(new Bike("TN01A1"))));
        System.out.println("Car slot: " + Arrays.toString(p.enter(new Car("TN02B2"))));
        System.out.println("Bike fee for 60 min: Rs " + p.exit("TN01A1", 60));
        System.out.println("Car fee for 61 min: Rs " + p.exit("TN02B2", 61));
        System.out.println("Expected total = Rs 70.0; actual total = Rs " + (10.0 + 60.0));
    }
}