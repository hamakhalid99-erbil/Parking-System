package friendoo.parking_system.services;

import com.iamceph.resulter.core.DataResultable;
import com.iamceph.resulter.core.Resultable;
import friendoo.parking_system.jpa.domain.Floor;
import friendoo.parking_system.jpa.repository.FloorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FloorService {
    private final FloorRepository floorRepository;

    public DataResultable<List<Floor>> findFloors() {
        List<Floor> floors = floorRepository.findAll();
        if (floors.isEmpty()) {
            log.error("No floor found");
            return DataResultable.fail("No floor found");
        }
        return DataResultable.ok(floors);
    }

    public Resultable addFloor(Floor floor) {
        try {
            if (floor == null) {
                return Resultable.fail("Floor can not be null");
            }
            floorRepository.save(floor);
            return Resultable.ok();
        } catch (Exception e) {
            log.error("Error happened during {}", e.getMessage(), e);
            return Resultable.fail("failed to add");
        }

    }

}
