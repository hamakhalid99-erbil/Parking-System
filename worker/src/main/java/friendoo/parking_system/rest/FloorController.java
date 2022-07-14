package friendoo.parking_system.rest;

import com.iamceph.resulter.core.DataResultable;
import friendoo.parking_system.jpa.domain.Floor;
import friendoo.parking_system.models.FloorsResponse;
import friendoo.parking_system.services.FloorService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(name = "Floor Controller", path = "api/v1/fl")
@Slf4j
public record FloorController(FloorService floorService) {

    @GetMapping(path = "/floors", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Finding floors")
    public ResponseEntity<FloorsResponse> findFloors() {
        DataResultable<List<Floor>> floors = floorService.findFloors();
        if (floors.isFail()) {
            log.warn("no floor was provided returning empty list");
            return ResponseEntity.ok(new FloorsResponse(new ArrayList<>()));
        }
        FloorsResponse floorResponse = new FloorsResponse();
        floorResponse.setFloors(floors.data());
        return ResponseEntity.ok(floorResponse);
    }

}
