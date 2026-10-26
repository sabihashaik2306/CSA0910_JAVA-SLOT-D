class ValidatedSlot {
    private int floor;
    private int slot;
    private String regNo;

    ValidatedSlot(int floor, int slot) {
        setPosition(floor, slot);
    }
    public void setPosition(int floor, int slot) {
        if (floor < 0 || slot < 0) throw new IllegalArgumentException("Negative position");
        this.floor = floor;
        this.slot = slot;
    }
    public void park(String regNo) {
        if (regNo == null || regNo.isBlank()) throw new IllegalArgumentException("Registration required");
        if (this.regNo != null) throw new IllegalStateException("Slot already occupied");
        this.regNo = regNo;
    }
    public void release() { regNo = null; }
    public String getStatus() {
        return "floor=" + floor + ", slot=" + slot + ", vehicle=" + (regNo == null ? "free" : regNo);
    }
}
public class Task10 {
    public static void main(String[] args) {
        ValidatedSlot slot = new ValidatedSlot(1, 4);
        slot.park("TN01AB1111");
        System.out.println(slot.getStatus());
        slot.release();
        System.out.println(slot.getStatus());
        Integer a = 1000, b = 1000;
        System.out.println("Use equals for Integer values: " + a.equals(b));
    }
}