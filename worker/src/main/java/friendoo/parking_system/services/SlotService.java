package friendoo.parking_system.services;

import com.iamceph.resulter.core.DataResultable;
import friendoo.parking_system.jpa.domain.Floor;
import friendoo.parking_system.jpa.domain.Slot;
import friendoo.parking_system.jpa.enums.SlotStatus;
import friendoo.parking_system.jpa.repository.SlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SlotService {
    private final SlotRepository slotRepository;
    private final FloorService floorService;

    public DataResultable<Slot> findEmptySlot() {
        DataResultable<List<Floor>> maybeFloors = floorService.findFloors();
        if (maybeFloors.isFail()) {
            return maybeFloors.transform();
        }
        List<Floor> floors = maybeFloors.data();

        return floors
                .stream()
                .<DataResultable<Slot>>map(floor -> {
                    List<Slot> allByFloor = slotRepository.findAllByFloor(floor.getFloorNumber());

                    Slot emptySlot = allByFloor
                            .stream()
                            .filter(slot -> slot.getStatus().isEmpty())
                            .findFirst()
                            .orElse(null);

                    if (Objects.nonNull(emptySlot)) {
                        return DataResultable.ok(emptySlot);
                    }


                    if (allByFloor.size() == floor.getFloorCapacity()) {
                        return DataResultable.fail("No empty slot found!");
                    }
                    Slot lastSlot = allByFloor
                            .stream()
                            .max(Comparator.comparing(Slot::getSlotNumber))
                            .orElse(null);

                    int toSlotNumber = 1;
                    if (lastSlot != null) {
                        toSlotNumber = lastSlot.getSlotNumber() + 1;
                    }
                    Slot slot = new Slot(toSlotNumber, floor.getFloorNumber(), SlotStatus.Empty);
                    return DataResultable.ok(slot);
                })
                .findFirst()
                .orElse(DataResultable.fail("No slots found in any floor"));
    }


}
