package friendoo.parking_system.jpa.domain;

import friendoo.parking_system.jpa.enums.SlotStatus;
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
    // TODO: 6/15/2022 remove uuid and set slot number and floor combined as primary key
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Slot_Number", nullable = false)
    private Integer slotNumber;
    @Column(name = "floor", nullable = false)
    private Integer floor;
    @Column(name = "status", nullable = false)
    private SlotStatus status;

}
