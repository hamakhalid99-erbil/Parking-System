package friendoo.parking_system.services;

import com.iamceph.resulter.core.DataResultable;
import friendoo.parking_system.jpa.domain.Ticket;
import friendoo.parking_system.models.TicketResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public record ReportService(TicketService ticketService) {
    public DataResultable<TicketResponse> getReportForDate(LocalDate forDate) {

        DataResultable<List<Ticket>> paidTicketForDate = ticketService.findPaidTicketForDate(forDate);
        DataResultable<List<Ticket>> unpaidTicketsForDate = ticketService.findUnpaidTicketsForDate(forDate);

        if (paidTicketForDate.isFail()) {
            return DataResultable.ok(TicketResponse.builder()
                    .result("Fail")
                    .resultMessage(paidTicketForDate.message())
                    .build());
        }
        List<Ticket> paidTickets = paidTicketForDate.data();

        int totalAmount = paidTickets
                .stream()
                .mapToInt(Ticket::getPrice)
                .sum();
        List<Ticket> unPaidTickets = unpaidTicketsForDate.data();
        if (unpaidTicketsForDate.isFail()) {
            return DataResultable.ok(TicketResponse.builder()
                    .result("ok")
                    .resultMessage(unpaidTicketsForDate.message())
                    .unPaidTickets(new ArrayList<>())
                    .paidTickets(paidTickets)
                    .totalAmount(totalAmount)
                    .build());
        }
        return DataResultable.ok(TicketResponse.builder()
                .result("ok")
                .resultMessage("Operation Succeed")
                .totalAmount(totalAmount)
                .paidTickets(paidTickets)
                .unPaidTickets(unPaidTickets)
                .build());

    }
}




