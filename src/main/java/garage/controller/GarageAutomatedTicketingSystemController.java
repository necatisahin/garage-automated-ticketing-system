package garage.controller;

import garage.service.GarageAutomatedTicketingSystemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/automatedTicketingSystem")
public class GarageAutomatedTicketingSystemController {
    private final GarageAutomatedTicketingSystemService garageAutomatedTicketingSystemService;

    public GarageAutomatedTicketingSystemController(GarageAutomatedTicketingSystemService garageAutomatedTicketingSystemService) {
        this.garageAutomatedTicketingSystemService = garageAutomatedTicketingSystemService;
    }

    @PostMapping
    public ResponseEntity<String> command(@RequestBody String request) {
        return ResponseEntity.ok(garageAutomatedTicketingSystemService.command(request));
    }
}
