package garage.util;

import garage.exception.GarageAutomatedTicketingSystemRequestException;
import garage.model.Vehicle;
import org.apache.commons.lang3.StringUtils;

import java.util.StringJoiner;

public class GarageAutomatedTicketingSystemUtil {

    public static void validateRequest(String request) {
        if (request == null && StringUtils.isBlank(request))
            throw new GarageAutomatedTicketingSystemRequestException(ErrorConstants.INVALID_REQUEST);
    }

    public static String[] splitRequest(String request) {
        return request.split(StringUtils.SPACE);
    }



}
