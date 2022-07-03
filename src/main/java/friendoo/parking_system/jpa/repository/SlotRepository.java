package friendoo.parking_system.jpa.repository;

import friendoo.parking_system.jpa.domain.Slot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SlotRepository extends JpaRepository<Slot,String> {
}
