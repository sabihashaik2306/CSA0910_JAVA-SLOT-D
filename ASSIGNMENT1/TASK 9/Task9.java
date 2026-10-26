interface Chargeable {
    double chargingFee(int kWh);
    default String chargingMessage() { return "Charging service available"; }
    static boolean validEnergy(int kWh) { return kWh >= 0; }
}
abstract class Vehicle {
    private final String regNo;
    protected Vehicle(String regNo) { this.regNo = regNo; }
    public String getRegNo() { return regNo; }
    public abstract double calculateFee(int minutes);
}
class ElectricCar extends Vehicle implements Chargeable {
    ElectricCar(String regNo) { super(regNo); }
    @Override public double calculateFee(int minutes) {
        return ((minutes + 59) / 60) * 30.0;
    }
    @Override public double chargingFee(int kWh) {
        if (!Chargeable.validEnergy(kWh)) throw new IllegalArgumentException("Invalid kWh");
        return kWh * 12.0;
    }
}
public class Task9 {
    public static void main(String[] args) {
        ElectricCar ev = new ElectricCar("TN03EV3333");
        System.out.println("Parking fee: Rs " + ev.calculateFee(70));
        System.out.println("Charging fee: Rs " + ev.chargingFee(5));
        System.out.println(ev.chargingMessage());
    }
}