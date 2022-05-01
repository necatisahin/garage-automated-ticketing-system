package garage.model;

public class Garage {
    private static boolean[] slots;

    public Garage(int size) {
        slots = new boolean[size];
    }

    public static boolean[] getSlots() {
        return slots;
    }
}
