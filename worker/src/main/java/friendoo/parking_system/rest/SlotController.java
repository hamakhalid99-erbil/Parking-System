package friendoo.parking_system.rest;

import com.iamceph.resulter.core.DataResultable;
import friendoo.parking_system.jpa.domain.Slot;
import friendoo.parking_system.services.SlotService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(name = "Slot Controller", path = "api/v1/sl")
public record SlotController (SlotService slotsService) {
    @GetMapping(path = "/slot", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Finding empty Slot")
    public ResponseEntity<Slot> findEmptySlot() {
        DataResultable<Slot> emptySlot = slotsService.findEmptySlot();
        if (emptySlot.isFail()) {
            log.error("no slots found");
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(emptySlot.data());
    }

}
