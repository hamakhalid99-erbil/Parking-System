package friendoo.parkingsystem.jpa.repository;

import friendoo.parkingsystem.jpa.domain.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SlotRepository extends JpaRepository<Slot,String> {

    List<Slot> findAllByFloor(Integer floor);
}
