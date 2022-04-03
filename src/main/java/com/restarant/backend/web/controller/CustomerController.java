package com.restarant.backend.web.controller;

import com.restarant.backend.entity.Customer;
import com.restarant.backend.service.ICustomerService;
import com.restarant.backend.dto.CustomerDto;
import com.restarant.backend.service.validate.exception.InvalidDataExeception;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.Set;


@Slf4j
@RestController
@RequestMapping("/api")
@Transactional
public class CustomerController {

    private static final String ENTITY_NAME = "customer";


    private final ICustomerService customerService;

    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * {@code POST  /customers} : Create a new customer.
     *
     * @param customer the customer to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new customer, or with status {@code 400 (Bad Request)} if the customer has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/customers")
    public ResponseEntity<Customer> createCustomer(@RequestBody CustomerDto customerDto) throws URISyntaxException {
        log.debug("REST request to save Customer : {}", customerDto);

        try {
            Customer result = customerService.save(customerDto);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
        } catch (InvalidDataExeception e) {
            log.error("Error when save customer", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    /**
     * {@code PUT  /customers/:id} : Updates an existing customer.
     *
     * @param id the id of the customer to save.
     * @param customer the customer to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated customer,
     * or with status {@code 400 (Bad Request)} if the customer is not valid,
     * or with status {@code 500 (Internal Server Error)} if the customer couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
//    @PutMapping("/customers/{id}")
//    public ResponseEntity<?> updateCustomer(
//        @PathVariable(value = "id", required = false) final Long id, @RequestBody Customer customer) throws URISyntaxException {
//        log.debug("REST request to update Customer : {}, {}", id, customer);
//        if (!customerService.isExistById(id)) {
//            return ResponseEntity.badRequest().body("entity not exit");
//        }
//        Customer result = customerService.save(customer);
//        return ResponseEntity.ok().body(result);
//    }


    @GetMapping("/customers")
    public Set<Customer> getAllCustomers() {
        log.debug("REST request to get all Customers");
        return customerService.getAll();
    }

    /**
     * {@code GET  /customers/:id} : get the "id" customer.
     *
     * @param id the id of the customer to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the customer, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
        log.debug("REST request to get Customer : {}", id);
        Customer customer = customerService.getCustomerById(id);
        if(customer == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.ok(customer);
    }

    /**
     * {@code DELETE  /customers/:id} : delete the "id" customer.
     *
     * @param id the id of the customer to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/customers/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        log.debug("REST request to delete Customer : {}", id);
        try {
            customerService.detele(id);
            return ResponseEntity.ok(null);
        } catch (InvalidDataExeception e) {
            log.error("Error when delete customer", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
