package friendoo.calculation.repo.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
@Builder
public class Receipt {
    @Id
    @Column(name = "uuid")
    private String uuid;
    @Column(name = "total_amount", nullable = false)
    private Long totalAmount;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "created_time", nullable = false)
    private LocalDateTime createdTime;
}
