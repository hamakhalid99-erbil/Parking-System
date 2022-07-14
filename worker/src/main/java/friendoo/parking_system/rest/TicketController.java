package friendoo.parking_system.rest;

import com.iamceph.resulter.core.DataResultable;
import friendoo.parking_system.jpa.domain.Ticket;
import friendoo.parking_system.models.TicketsResponse;
import friendoo.parking_system.services.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping(name = "Ticket Controller", path = "/api/v1/tk")
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class TicketController {

    private final TicketService ticketService;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @GetMapping(path = "/ticket/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Find Tickets By id")
    public ResponseEntity<Ticket> findById(@PathVariable String id) {
        DataResultable<Ticket> byId = ticketService.findById(id);
        if (byId.isFail()) {
            log.error("Ticket with id {} not found!", id);
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(byId.data());
    }

    @GetMapping(path = "/tickets/{forDate}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Find tickets for specific date, date format should be (dd-MM-yyyy)")
    public ResponseEntity<TicketsResponse> findTicketsForDate(@PathVariable String forDate) {
        DataResultable<List<Ticket>> ticketsForDate = ticketService.findTicketsForDate(LocalDate.parse(forDate, formatter));
        if (ticketsForDate.isFail()) {
            log.error("No tickets found for that Date {}", forDate);
            return ResponseEntity.badRequest().build();
        }
        TicketsResponse ticketsResponse = new TicketsResponse(ticketsForDate.data());
        return ResponseEntity.ok(ticketsResponse);
    }



    @GetMapping(path = "/tickets/paid/{forDate}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Find paid tickets for specific dte, date format should be (dd-MM-yyy)")
    public ResponseEntity<TicketsResponse> findPaidTicketsForDate(@PathVariable("forDate") String forDate) {
        DataResultable<List<Ticket>> paidTicketsForDate = ticketService.findPaidTicketForDate(LocalDate.parse(forDate, formatter));
        if (paidTicketsForDate.isFail()) {
            log.error("No paid tickets found for date {} ", forDate);
            return ResponseEntity.badRequest().build();
        }
        TicketsResponse ticketsResponse = new TicketsResponse(paidTicketsForDate.data());
        return ResponseEntity.ok(ticketsResponse);
    }

    @GetMapping(path = "tickets/unpaid/{forDate}")
    @Operation(summary = "Find unpaid tickets for specific date, date format should be (dd-MM-yyyy)")
    public ResponseEntity<TicketsResponse> findNotPaidTicketsForDate(@PathVariable String forDate) {
        DataResultable<List<Ticket>> notPaidTicketsForDate = ticketService.findUnpaidTicketsForDate(LocalDate.parse(forDate, formatter));
        if (notPaidTicketsForDate.isFail()) {
            log.error("No unpaid tickets found for date{}", forDate);
            return ResponseEntity.badRequest().build();
        }
        TicketsResponse ticketsResponse = new TicketsResponse(notPaidTicketsForDate.data());
        return ResponseEntity.ok(ticketsResponse);
    }
}




