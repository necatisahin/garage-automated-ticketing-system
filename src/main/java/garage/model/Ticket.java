package garage.model;

import garage.util.ParametersConstants;

import java.util.ArrayList;
import java.util.List;

public class Ticket {

    private Vehicle vehicle;
    private List<Integer> slots;

    public Ticket(Vehicle vehicle, List<Integer> slots) {
        this.vehicle = vehicle;
        this.slots = slots;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public List<Integer> getSlots() {
        return slots;
    }

    public void setSlots(List<Integer> slots) {
        this.slots = slots;
    }

    @Override
    public String toString() {
        List<Integer> indexes = new ArrayList<>();
        for (int index : slots) {
            indexes.add(index + 1);
        }
        return vehicle.toString() + " " + indexes.toString();
    }

    public String getAllocationMessage() {
        return "Allocated " + vehicle.getType().getSize() +
                " slot" + (vehicle.getType().getSize() > 1 ? "s." : ".") + ParametersConstants.newLine;
    }
}
