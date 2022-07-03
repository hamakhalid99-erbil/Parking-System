package friendoo.parking_system.jpa.repository;

import friendoo.parking_system.jpa.domain.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report,String> {
}
