package com.turisticka_agencija.dodatne_aktivnosti.controller;

import com.turisticka_agencija.dodatne_aktivnosti.dto.RegistrationRequest;
import com.turisticka_agencija.dodatne_aktivnosti.model.Customer;
import com.turisticka_agencija.dodatne_aktivnosti.model.Registration;
import com.turisticka_agencija.dodatne_aktivnosti.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final ICustomerService customerService;

    @Autowired
    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<Customer>> findAll() {
        return new ResponseEntity<>(customerService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> findById(@PathVariable Long id) {
        return new ResponseEntity<>(customerService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Customer> save(@RequestBody Customer customer) {
        return new ResponseEntity<>(customerService.save(customer), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> update(@PathVariable Long id, @RequestBody Customer customer) {
        return new ResponseEntity<>(customerService.update(id, customer), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        customerService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{customerId}/registrations")
    public ResponseEntity<List<Registration>> findAllRegistrations(@PathVariable Long customerId) {
        return new ResponseEntity<>(customerService.findAllRegistrationsByCustomerId(customerId), HttpStatus.OK);
    }

    @GetMapping("/{customerId}/registrations/{registrationId}")
    public ResponseEntity<Registration> findRegistrationById(@PathVariable Long customerId,
                                                             @PathVariable Long registrationId) {
        return new ResponseEntity<>(customerService.findRegistrationById(customerId, registrationId), HttpStatus.OK);
    }

    @PostMapping("/{customerId}/registrations")
    public ResponseEntity<Registration> addRegistration(@PathVariable Long customerId,
                                                        @RequestBody RegistrationRequest request) {
        return new ResponseEntity<>(customerService.addRegistration(customerId, request), HttpStatus.CREATED);
    }

    @PutMapping("/{customerId}/registrations/{registrationId}")
    public ResponseEntity<Registration> updateRegistration(@PathVariable Long customerId,
                                                           @PathVariable Long registrationId,
                                                           @RequestBody RegistrationRequest request) {
        return new ResponseEntity<>(
                customerService.updateRegistration(customerId, registrationId, request),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{customerId}/registrations/{registrationId}")
    public ResponseEntity<Void> deleteRegistration(@PathVariable Long customerId,
                                                   @PathVariable Long registrationId) {
        customerService.deleteRegistration(customerId, registrationId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{customerId}/favorite-categories/{categoryId}")
    public ResponseEntity<Customer> addFavoriteCategory(@PathVariable Long customerId,
                                                        @PathVariable Long categoryId) {
        return new ResponseEntity<>(
                customerService.addFavoriteCategory(customerId, categoryId),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{customerId}/favorite-categories/{categoryId}")
    public ResponseEntity<Customer> removeFavoriteCategory(@PathVariable Long customerId,
                                                           @PathVariable Long categoryId) {
        return new ResponseEntity<>(
                customerService.removeFavoriteCategory(customerId, categoryId),
                HttpStatus.OK
        );
    }

    @PostMapping("/{customerId}/booked-arrangements/{arrangementId}")
    public ResponseEntity<Customer> addBookedArrangement(@PathVariable Long customerId,
                                                         @PathVariable Long arrangementId) {
        return new ResponseEntity<>(
                customerService.addBookedArrangement(customerId, arrangementId),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{customerId}/booked-arrangements/{arrangementId}")
    public ResponseEntity<Customer> removeBookedArrangement(@PathVariable Long customerId,
                                                            @PathVariable Long arrangementId) {
        return new ResponseEntity<>(
                customerService.removeBookedArrangement(customerId, arrangementId),
                HttpStatus.OK
        );
    }
}