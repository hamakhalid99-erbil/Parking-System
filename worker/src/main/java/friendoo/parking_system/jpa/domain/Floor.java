package friendoo.parking_system.jpa.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "floor")
public class Floor {
    @Id
    @Column(name = "floor_number")
    private Integer floorNumber;
    @Column(name = "floor_capacity", nullable = false)
    private Integer floorCapacity;
}