package friendoo.calculation.repo;

import friendoo.calculation.repo.domain.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiptRepository extends JpaRepository<Receipt,String> {
}
