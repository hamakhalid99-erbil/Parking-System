package friendoo.parkingsystem.services;

import com.iamceph.resulter.core.DataResultable;
import friendoo.parkingsystem.jpa.domain.Ticket;
import friendoo.parkingsystem.jpa.enums.TicketStatus;
import friendoo.parkingsystem.jpa.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class TicketService {

    private final TicketRepository ticketRepository;

    public DataResultable<Ticket> findById(String id) {
        log.trace("Fetching ticket with Id {}", id);
        Optional<Ticket> ticketId = ticketRepository.findById(id);
        if (ticketId.isPresent()) {
            return DataResultable.ok(ticketId.get());
        }
        log.warn("Ticket not found by Id {} ", id);
        return DataResultable.fail("Ticket Not Found");
    }

    public DataResultable<List<Ticket>> findTicketsForDate(LocalDate forDate) {
        log.trace("Fetching ticket with date {}", forDate);
        List<Ticket> ticketByDate = ticketRepository.findTicketByDate(forDate);
        if (ticketByDate.isEmpty()) {
            log.error("No ticket found for date {}", forDate);
            return DataResultable.fail("No ticket found");
        }
        return DataResultable.ok(ticketByDate);
    }

    public DataResultable<List<Ticket>> findPaidTicketForDate(LocalDate forDate) {
        List<Ticket> tickets = ticketRepository.findTicketByStatusAndTime(TicketStatus.PAID.ordinal(),forDate);
        if (tickets.isEmpty()) {
            log.error("No paid ticket found for date {}", forDate);
            return DataResultable.fail("No paid ticket found");
        }
        return DataResultable.ok(tickets);
    }

    public DataResultable<List<Ticket>> findUnpaidTicketsForDate(LocalDate forDate) {
        DataResultable<List<Ticket>> ticketsForDate = findTicketsForDate(forDate);
        if (ticketsForDate.isFail()) {
            return ticketsForDate.transform();
        }
        List<Ticket> toList = ticketsForDate.data()
                .stream()
                .filter(ticket -> ticket.getStatus().isNotPaid())
                .toList();
        if (toList.isEmpty()) {
            log.warn("No unpaid tickets for date {} ", forDate);
            return DataResultable.fail("No Unpaid tickets found for date");
        }
        return DataResultable.ok(toList);
    }

}
