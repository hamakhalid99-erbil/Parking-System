package friendoo.parking_system.rest;

import com.iamceph.resulter.core.DataResultable;
import friendoo.parking_system.models.TicketResponse;
import friendoo.parking_system.services.ReportService;
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

@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Slf4j
@RequestMapping(name = "Report Controller", path = "api/v1/rp")
public class ReportController {
    private final ReportService reportService;
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @GetMapping(path = "/report/{forDate}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Getting a Report for a specific date, date format should be (dd-MM-yyyy)")
    public ResponseEntity<TicketResponse> getReportForDate(@PathVariable(name = "forDate") String forDate) {
        DataResultable<TicketResponse> ticketResponseReport = reportService.getReportForDate(LocalDate.parse(forDate, dateTimeFormatter));
        if (ticketResponseReport.isFail()) {
            log.error("The Report not found for the date {}", forDate);
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(ticketResponseReport.data());

    }
}