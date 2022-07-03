package friendoo.parking_system.jpa.repository;

import friendoo.parking_system.jpa.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,String> {
    @Query(value = "select t.* from Ticket t where Date(created_time)= :dare",nativeQuery = true)
    List<Ticket> findTicketByDate(LocalDate forDate);

    @Query(value = "select p.* from Ticket p where ticket_status = :status and Date(created_time) = :date",nativeQuery = true)
    List<Ticket> findTicketByStatusAndTime(LocalDate forDate);


}
