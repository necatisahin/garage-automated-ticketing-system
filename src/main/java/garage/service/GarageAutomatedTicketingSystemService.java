package garage.service;

import garage.exception.GarageAutomatedTicketingSystemRequestException;
import garage.model.*;
import garage.util.ErrorConstants;
import garage.util.GarageAutomatedTicketingSystemUtil;
import garage.util.MessageContants;
import garage.util.ParametersConstants;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GarageAutomatedTicketingSystemService {


    private static Garage garage = new Garage(ParametersConstants.garageSize);
    private static List<Ticket> TICKETS = new ArrayList<>();

    public String command(String request) {
        GarageAutomatedTicketingSystemUtil.validateRequest(request);
        String[] splitedRequest = GarageAutomatedTicketingSystemUtil.splitRequest(request);

        CommandType commandType = CommandType.validateCommandType(splitedRequest[0]);

        if (commandType == CommandType.PARK) {
            Ticket ticket = park(splitedRequest);
            if (ticket == null) {
                return MessageContants.GARAGE_AREA_IS_FULL;
            } else {
                return ticket.getAllocationMessage();
            }

        } else if (commandType == CommandType.LEAVE)
            return leave(Integer.parseInt(splitedRequest[1]));
        else
            return status();


    }

    public Ticket park(String[] splitedRequest) {

        VehicleType vehicleType = VehicleType.validateVehicleType(splitedRequest[3]);
        Vehicle vehicle = Vehicle.builder()
                .licensePlate(splitedRequest[1])
                .colour(splitedRequest[2])
                .type(vehicleType)
                .build();

        if (vehicle == null) {
            throw new GarageAutomatedTicketingSystemRequestException(ErrorConstants.INVALID_REQUEST);
        }
        int requiredSlots = vehicle.getType().getSize();
        Ticket ticket = null;
        boolean vehicleFound = false;
        int vehicleCount = 0;
        int lastIndex = garage.getSlots().length - requiredSlots;
        for (int i = 0; i < lastIndex; i++) {
            // if slot is not empty
            if (garage.getSlots()[i]) {
                vehicleFound = true;
            } else {
                // vehicle found
                if (vehicleFound) {
                    vehicleCount++;
                    vehicleFound = false;
                }

                boolean slotFound = true;
                // start from next slot if it is not 0 index
                int startIndex = i == 0 ? i : i + 1;
                for (int j = startIndex; j < startIndex + requiredSlots; j++) {
                    if (garage.getSlots()[j]) {
                        slotFound = false;
                        break;
                    }
                }
                if (slotFound) {
                    List<Integer> slots = new ArrayList<>();
                    for (int j = startIndex; j < startIndex + requiredSlots; j++) {
                        garage.getSlots()[j] = true;
                        slots.add(j);
                    }
                    ticket = new Ticket(vehicle, slots);
                    TICKETS.add(vehicleCount, ticket);
                    break;
                }

            }
        }

        return ticket;
    }

    public String leave(int index) {
        index = index - 1;
        int ticketsSize = TICKETS.size();
        if (index < 0 || index >= ticketsSize) {
            throw new GarageAutomatedTicketingSystemRequestException(ErrorConstants.INVALID_LEAVE_REQUEST_PARAMETER);
        }
        Ticket ticket = TICKETS.remove(index);
        for (int slot : ticket.getSlots()) {
            garage.getSlots()[slot] = false;
        }
        return MessageContants.COMMAND_TYPE_LEAVE_EMPTY_MESSAGE;
    }

    public String status() {
        StringBuffer res = new StringBuffer();
        for (Ticket ticket : TICKETS) {
            res.append(ticket.toString() + ParametersConstants.newLine);
        }
        return res.toString();
    }


}
