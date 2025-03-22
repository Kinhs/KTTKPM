package com.example.demo.controller;

import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Optional<Customer> customer = customerService.getCustomerById(id);
        return customer.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/identity/{identityCard}")
    public ResponseEntity<Customer> getCustomerByIdentityCard(@PathVariable String identityCard) {
        Customer customer = customerService.getCustomerByIdentityCard(identityCard);
        return customer != null ? ResponseEntity.ok(customer) : ResponseEntity.notFound().build();
    }

    @GetMapping("/room/{roomId}")
    public ResponseEntity<List<Customer>> getCustomersByRoom(@PathVariable Long roomId) {
        List<Customer> customers = customerService.getCustomersByRoom(roomId);
        return customers != null ? ResponseEntity.ok(customers) : ResponseEntity.notFound().build();
    }

    @GetMapping("/current")
    public ResponseEntity<List<Customer>> getCurrentCustomers() {
        return ResponseEntity.ok(customerService.getCurrentCustomers());
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        return new ResponseEntity<>(customerService.saveCustomer(customer), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        Optional<Customer> existingCustomer = customerService.getCustomerById(id);
        if (existingCustomer.isPresent()) {
            customer.setId(id);
            return ResponseEntity.ok(customerService.saveCustomer(customer));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/checkout")
    public ResponseEntity<Customer> checkOutCustomer(@PathVariable Long id) {
        Customer customer = customerService.checkOutCustomer(id);
        return customer != null ? ResponseEntity.ok(customer) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        Optional<Customer> existingCustomer = customerService.getCustomerById(id);
        if (existingCustomer.isPresent()) {
            customerService.deleteCustomer(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

