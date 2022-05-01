package garage.util;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ErrorConstants {

    INVALID_REQUEST("GATS-001", "Invalid request!"),
    COMMAND_TYPE_NOT_FOUND("GATS-002", "Command type not found!"),
    VEHICLE_TYPE_NOT_FOUND("GATS-003", "Vehicle type not found!"),
    INVALID_LEAVE_REQUEST_PARAMETER("GATS-004", "Invalid leave request parameter!"),
    LEAVE_REQUEST_SECOND_PARAMETER_MUST_BE_NUMBER("GATS-005", "Leave request second parameter must be number!"),
    INVALID_PARK_REQUEST_PARAMETER_COUNT("GATS-006", "Invalid park request parameter count!");

    String code = "SYSTEM";
    String message;

    ErrorConstants(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
