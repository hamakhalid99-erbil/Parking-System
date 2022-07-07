package friendoo.parking_system.models;

import friendoo.parking_system.jpa.domain.Ticket;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketResponse {
    private String result;
    private String resultMessage;
    private Integer totalAmount;
    private List<Ticket> paidTickets;
    private List<Ticket> unPaidTickets;
}
