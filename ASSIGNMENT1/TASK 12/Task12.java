import java.time.LocalTime;

interface PricingRule {
    double fee(int minutes, LocalTime entry, LocalTime exit);
}
abstract class Vehicle {
    final String regNo;
    Vehicle(String regNo) { this.regNo = regNo; }
    abstract double fee(int minutes, LocalTime entry, LocalTime exit);
}
class EBike extends Vehicle {
    EBike(String regNo) { super(regNo); }
    @Override double fee(int minutes, LocalTime in, LocalTime out) { return 15.0; }
}
class DeliveryVan extends Vehicle {
    DeliveryVan(String regNo) { super(regNo); }
    @Override double fee(int minutes, LocalTime in, LocalTime out) {
        int hours = (minutes + 59) / 60;
        double total = hours * 30.0;
        boolean daytime = !in.isBefore(LocalTime.of(9, 0)) && in.isBefore(LocalTime.of(17, 0));
        return daytime ? total * 2 : total;
    }
}
interface Pass {
    boolean isValid();
}
class OneDayPass implements Pass {
    private final boolean paid;
    OneDayPass(boolean paid) { this.paid = paid; }
    public boolean isValid() { return paid; }
}
public class Task12 {
    public static void main(String[] args) {
        Vehicle ebike = new EBike("TN10EB1234");
        Vehicle van = new DeliveryVan("TN10DV5678");
        System.out.println("E-bike flat fee: Rs " + ebike.fee(45, LocalTime.of(10,0), LocalTime.of(10,45)));
        System.out.println("Delivery van daytime fee: Rs " + van.fee(60, LocalTime.of(10,0), LocalTime.of(11,0)));
        Pass pass = new OneDayPass(true);
        System.out.println("Visitor one-day pass valid: " + pass.isValid());
    }
}