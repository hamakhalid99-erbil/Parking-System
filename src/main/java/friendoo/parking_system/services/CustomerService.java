package friendoo.parking_system.services;

import com.iamceph.resulter.core.DataResultable;
import com.iamceph.resulter.core.Resultable;
import friendoo.parking_system.jpa.domain.Customer;
import friendoo.parking_system.jpa.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Slf4j
public class CustomerService {
    private final CustomerRepository customerRepository;

    public List<Customer> findCustomers() {
        log.trace("Received request to fetch all customers in db");
        return customerRepository.findAll();
    }

    public DataResultable<Customer> findCustomerById(String id) {
        log.trace("Received request to fetch customer with id {}", id);
        Optional<Customer> byId = customerRepository.findById(id);
        if (byId.isPresent()) {
            return DataResultable.ok(byId.get());
        }
        log.warn("Customer with id {} was not found", id);
        return DataResultable.fail("Customer not found");
    }

    public Resultable checkBalanceById(Integer toCheckBalance, String id) {
        DataResultable<Customer> customerById = findCustomerById(id);
        if (customerById.isFail()) {
            return customerById.transform();
        }
        Customer customerData = customerById.data();
        if (customerData.getBalance() >= toCheckBalance) {
            return Resultable.ok();

        }
        log.warn("Customer with id {} has not enough balance", id);
        return Resultable.fail("Not Enough balance");
    }

}
