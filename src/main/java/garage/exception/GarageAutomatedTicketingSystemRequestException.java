package garage.exception;

import garage.util.ErrorConstants;
import lombok.Getter;

@Getter
public class GarageAutomatedTicketingSystemRequestException extends RuntimeException {

    private final ErrorConstants errorConstants;

    public GarageAutomatedTicketingSystemRequestException(ErrorConstants errorConstants) {
        super(errorConstants.getMessage());
        this.errorConstants = errorConstants;
    }
}
