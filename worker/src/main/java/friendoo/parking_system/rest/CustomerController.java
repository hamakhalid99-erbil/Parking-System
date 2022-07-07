package friendoo.parking_system.rest;

import com.iamceph.resulter.core.DataResultable;
import friendoo.parking_system.jpa.domain.Customer;
import friendoo.parking_system.models.CustomersResponse;
import friendoo.parking_system.services.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping(name = "Customer Controller", path = "api/v1/cs")
public class CustomerController {
    private final CustomerService customerService;


    @GetMapping(path = "/customers", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Getting Customers")
    public ResponseEntity<CustomersResponse> getCustomers() {
        List<Customer> customers = customerService.findCustomers();
        if (customers.isEmpty()) {
            log.error("No customer found");
            return ResponseEntity.badRequest().build();
        } else {
            CustomersResponse customersResponse = new CustomersResponse();
            customersResponse.setCustomers(customers);
            return ResponseEntity.ok(customersResponse);
        }
    }


    @GetMapping(path = "/customers/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Getting customers by ID")
    public ResponseEntity<Customer> findCustomerById(@PathVariable String id) {
        DataResultable<Customer> customerById = customerService.findCustomerById(id);
        if (customerById.isFail()) {
            log.error("No customer found by id {}", id);
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(customerById.data());
    }
}
