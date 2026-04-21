package com.turisticka_agencija.dodatne_aktivnosti.service;

import com.turisticka_agencija.dodatne_aktivnosti.dto.RegistrationRequest;
import com.turisticka_agencija.dodatne_aktivnosti.model.Customer;
import com.turisticka_agencija.dodatne_aktivnosti.model.Registration;

import java.util.List;

public interface ICustomerService {
    List<Customer> findAll();
    Customer findById(Long id);
    Customer save(Customer customer);
    Customer update(Long id, Customer customer);
    void delete(Long id);

    Registration addRegistration(Long customerId, RegistrationRequest request);
    List<Registration> findAllRegistrationsByCustomerId(Long customerId);
    Registration findRegistrationById(Long customerId, Long registrationId);
    Registration updateRegistration(Long customerId, Long registrationId, RegistrationRequest request);
    void deleteRegistration(Long customerId, Long registrationId);

    Customer addFavoriteCategory(Long customerId, Long categoryId);
    Customer removeFavoriteCategory(Long customerId, Long categoryId);

    Customer addBookedArrangement(Long customerId, Long arrangementId);
    Customer removeBookedArrangement(Long customerId, Long arrangementId);
}