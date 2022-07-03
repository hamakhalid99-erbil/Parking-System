package friendoo.parking_system.jpa.repository;

import friendoo.parking_system.jpa.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,String> {
}
