package friendoo.parkingsystem.jpa.repository;

import friendoo.parkingsystem.jpa.domain.Floor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FloorRepository extends JpaRepository<Floor,Integer> {
}
