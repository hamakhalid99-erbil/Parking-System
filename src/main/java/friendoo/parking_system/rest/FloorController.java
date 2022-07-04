package friendoo.parking_system.rest;

import com.iamceph.resulter.core.DataResultable;
import friendoo.parking_system.jpa.domain.Floor;
import friendoo.parking_system.models.FloorsResponse;
import friendoo.parking_system.services.FloorService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(name = "Floor Controller", path = "api/v1/fl")
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FloorController {

    private final FloorService floorService;

    @GetMapping(path = "/floors", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Finding floors")
    public ResponseEntity<FloorsResponse> findFloors() {
        DataResultable<List<Floor>> floors = floorService.findFloors();

        if (floors.isFail()) {
            log.error("No floors found");
            return ResponseEntity.badRequest().build();
        }
        FloorsResponse floorResponse = new FloorsResponse();
        floorResponse.setFloors(floors.data());
        return ResponseEntity.ok(floorResponse);
    }

}