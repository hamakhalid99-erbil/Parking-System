package friendoo.parkingsystem.jpa.domain;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "report")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String uuid;
    @Column(name = "total_amount", nullable = false)
    private Integer totalAmount;
    @Column(name = "date", nullable = false)
    private LocalDateTime date;
    @Column(name = "total_cars", nullable = false)
    private Integer total_cars;


}
