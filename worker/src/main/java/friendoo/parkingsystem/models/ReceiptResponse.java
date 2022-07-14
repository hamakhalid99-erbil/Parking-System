package friendoo.parkingsystem.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReceiptResponse {
    private String uuid;
    private Long totalAmount;
    private String description;
    private LocalDateTime createdTime;
}
