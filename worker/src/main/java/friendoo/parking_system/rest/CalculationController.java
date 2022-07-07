package friendoo.parking_system.rest;

import friendoo.parking_system.models.ReceiptResponse;
import friendoo.parking_system.models.TicketRequest;
import friendoo.parking_system.services.CalculationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RequestMapping(name = "Calculation Controller", path = "calculation")
public class CalculationController {
    private final CalculationService calculationService;

    public ResponseEntity<ReceiptResponse> calculationController(@RequestBody TicketRequest ticketRequest) {
        ReceiptResponse receiptResponse = calculationService.sendCalculateCalculationRequest(ticketRequest);
        if (receiptResponse == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(receiptResponse);

    }
}
