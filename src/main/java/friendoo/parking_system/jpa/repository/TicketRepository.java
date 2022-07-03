package friendoo.parking_system.jpa.repository;

import friendoo.parking_system.jpa.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket,String> {
}
