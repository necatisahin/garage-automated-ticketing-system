package garage.model;

import garage.exception.GarageAutomatedTicketingSystemRequestException;
import garage.util.ErrorConstants;
import lombok.Getter;


@Getter
public enum CommandType {

    PARK, LEAVE, STATUS;

    public static CommandType validateCommandType(final String name) {
        for (CommandType enumValue : values()) {
            if (enumValue.name().equalsIgnoreCase(name)) {
                return enumValue;
            }
        }
        throw new GarageAutomatedTicketingSystemRequestException(ErrorConstants.COMMAND_TYPE_NOT_FOUND);
    }

}
