public class Task2 {
    public static void main(String[] args) {
        int[] slotsPerFloor = {3, 2, 4}; // jagged layout: floors may differ
        String[][] slots = new String[slotsPerFloor.length][];
        for (int f = 0; f < slotsPerFloor.length; f++) {
            slots[f] = new String[slotsPerFloor[f]];
        }
        int floor = 1, slot = 0;
        slots[floor][slot] = "TN02CD2222";
        int minutes = 61;
        int billableHours = (minutes + 59) / 60; // ceiling division
        double ratePerHour = 30.0;
        double fee = billableHours * ratePerHour;
        System.out.println("Floors: " + slots.length);
        System.out.println("Slots on floor 2: " + slots[1].length);
        System.out.println("Vehicle: " + slots[1][0]);
        System.out.println("Billable hours for 61 minutes: " + billableHours);
        System.out.println("Fee: Rs " + fee);
        System.out.println("Wrong integer division 61/60 = " + (61 / 60));
    }
}