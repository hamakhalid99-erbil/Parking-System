package friendoo.parking_system.models;

import friendoo.parking_system.jpa.domain.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketsResponse {
    List<Ticket> tickets;
}