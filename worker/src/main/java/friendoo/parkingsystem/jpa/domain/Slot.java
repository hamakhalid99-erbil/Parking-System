package friendoo.parkingsystem.jpa.domain;

import friendoo.parkingsystem.jpa.enums.SlotStatus;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "slot")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Slot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Slot_Number", nullable = false)
    private Integer slotNumber;
    @Column(name = "floor", nullable = false)
    private Integer floor;
    @Column(name = "status", nullable = false)
    private SlotStatus status;

}
