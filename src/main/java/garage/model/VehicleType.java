package garage.model;

import garage.exception.GarageAutomatedTicketingSystemRequestException;
import garage.util.ErrorConstants;
import lombok.Getter;

@Getter
public enum VehicleType {

    CAR(1), JEEP(2), TRUCK(4);

    private final int size;

    VehicleType(int size) {
        this.size = size;
    }

    public static VehicleType validateVehicleType(final String name) {
        for (VehicleType enumValue : values()) {
            if (enumValue.name().equalsIgnoreCase(name)) {
                return enumValue;
            }
        }
        throw new GarageAutomatedTicketingSystemRequestException(ErrorConstants.VEHICLE_TYPE_NOT_FOUND);
    }

}
