package friendoo.parking_system.jpa.repository;

import friendoo.parking_system.jpa.domain.Floor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FloorRepository extends JpaRepository<Floor,Integer> {
}
