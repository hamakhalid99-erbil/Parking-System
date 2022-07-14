package friendoo.parkingsystem.jpa.domain;


import friendoo.parkingsystem.jpa.enums.TicketStatus;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @Column(name = "id")
    private String uuid;
    @Column(name = "price", nullable = false)
    private Integer price;
    @Column(name = "created_time", nullable = false)
    private LocalDateTime createdTime;
    @Column(name = "ticket_status", nullable = false)
    private TicketStatus status;
}