package friendoo.calculation.rest;

import com.iamceph.resulter.core.DataResultable;
import friendoo.calculation.models.TicketRequest;
import friendoo.calculation.repo.domain.Receipt;
import friendoo.calculation.service.CalculationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = "Calculation Controller", path = "calculation")
public record CalculationController(CalculationService service) {
    @PostMapping(
            path = "calculation",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Receipt> calculateTicket(@RequestBody TicketRequest ticketRequest) {
        DataResultable<Receipt> maybeReceipt = service.calculateTicket(ticketRequest);
        if (maybeReceipt.isFail()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(maybeReceipt.data());
    }

}
