package friendoo.parkingsystem.rest;

import com.iamceph.resulter.core.DataResultable;
import friendoo.parkingsystem.jpa.domain.Customer;
import friendoo.parkingsystem.models.CustomersResponse;
import friendoo.parkingsystem.services.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(name = "Customer Controller", path = "api/v1/cs")
public record CustomerController(CustomerService customerService) {

    @GetMapping(path = "/customers", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Getting Customers")
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
    @Operation(summary = "Getting customers by ID")
    public ResponseEntity<Customer> findCustomerById(@PathVariable String id) {
        DataResultable<Customer> customerById = customerService.findCustomerById(id);
        if (customerById.isFail()) {
            log.error("No customer found by id {}", id);
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(customerById.data());
    }
}
