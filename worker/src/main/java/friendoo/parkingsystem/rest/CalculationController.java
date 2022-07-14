package friendoo.parkingsystem.rest;

import friendoo.parkingsystem.models.ReceiptResponse;
import friendoo.parkingsystem.models.TicketRequest;
import friendoo.parkingsystem.services.CalculationService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = "Calculation Controller", path = "calculation")
public record CalculationController(CalculationService calculationService) {
    @PostMapping(path = "/calculate")
    @Operation(summary = "triggers calculation to be sent to calculation module")
    public ResponseEntity<ReceiptResponse> calculationController(@RequestBody TicketRequest ticketRequest) {
        ReceiptResponse receiptResponse = calculationService.sendCalculateCalculationRequest(ticketRequest);
        if (receiptResponse == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(receiptResponse);

    }
}
