package friendoo.parkingsystem.services;

import com.iamceph.resulter.core.DataResultable;
import friendoo.parkingsystem.jpa.domain.Floor;
import friendoo.parkingsystem.jpa.domain.Slot;
import friendoo.parkingsystem.jpa.enums.SlotStatus;
import friendoo.parkingsystem.jpa.repository.SlotRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
public record SlotService(SlotRepository slotRepository, FloorService floorService) {
    public DataResultable<Slot> findEmptySlot() {
        DataResultable<List<Floor>> maybeFloors = floorService.findFloors();
        if (maybeFloors.isFail()) {
            return maybeFloors.transform();
        }
        List<Floor> floors = maybeFloors.data();
        return floors
                .stream()
                .map(this::findSlot)
                .findFirst()
                .orElse(DataResultable.fail("No slots found in any floor"));
    }

    private DataResultable<Slot> findSlot(Floor floor) {
        DataResultable<Slot> maybeSlot = findLastAvailableSlot(floor);
        if (maybeSlot.isFail()) {
            return maybeSlot.transform();
        }

        int toSlotNumber = maybeSlot.data().getSlotNumber() + 1;
        Slot slot = new Slot(toSlotNumber, floor.getFloorNumber(), SlotStatus.Empty);
        return DataResultable.ok(slot);
    }

    private DataResultable<Slot> findLastAvailableSlot(Floor floor) {
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
        return DataResultable.failIfNull(allByFloor
                .stream()
                .max(Comparator.comparing(Slot::getSlotNumber))
                .orElse(null)
        );
    }

}
