package friendoo.calculation.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketRequest {
    private String uuid;
    private Integer price;
    private LocalDateTime createdTime;
    private String status;
}
