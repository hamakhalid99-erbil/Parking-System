package friendoo.parkingsystem.models;

import friendoo.parkingsystem.jpa.domain.Floor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FloorsResponse {
    List<Floor> floors;
}
