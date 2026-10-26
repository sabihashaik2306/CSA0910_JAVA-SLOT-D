import java.util.Objects;

abstract class Vehicle {
    private final String regNo;
    Vehicle(String regNo) {
        if (regNo == null || regNo.isBlank()) throw new IllegalArgumentException("Registration required");
        this.regNo = regNo;
    }
    public String getRegNo() { return regNo; }
    abstract double calculateFee(int minutes);
    @Override public String toString() { return getClass().getSimpleName() + "[" + regNo + "]"; }
    @Override public boolean equals(Object o) {
        return o instanceof Vehicle v && regNo.equals(v.regNo);
    }
    @Override public int hashCode() { return Objects.hash(regNo); }
}
class Car extends Vehicle {
    Car(String regNo) { super(regNo); }
    @Override double calculateFee(int minutes) { return ((minutes + 59) / 60) * 30.0; }
}
public class Task7 {
    public static void main(String[] args) {
        Car a = new Car("TN02B2");
        Car b = new Car("TN02B2");
        System.out.println(a.calculateFee(61));
        System.out.println(a);
        System.out.println("Equal registrations: " + a.equals(b));
        System.out.println("Same hash: " + (a.hashCode() == b.hashCode()));
    }
}