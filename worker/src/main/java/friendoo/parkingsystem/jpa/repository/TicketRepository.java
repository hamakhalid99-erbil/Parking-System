package friendoo.parkingsystem.jpa.repository;

import friendoo.parkingsystem.jpa.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,String> {
    @Query(value = "select t from Ticket t where t.createdTime =:date")
    List<Ticket> findTicketByDate(LocalDate date);

    @Query(value = "select p from Ticket p where p.createdTime =:date and p.status =:status")
    List<Ticket> findTicketByStatusAndTime(Integer status,LocalDate date);
}
