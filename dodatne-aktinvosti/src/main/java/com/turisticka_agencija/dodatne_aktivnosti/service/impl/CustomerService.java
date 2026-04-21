package com.turisticka_agencija.dodatne_aktivnosti.service.impl;

import com.turisticka_agencija.dodatne_aktivnosti.dto.RegistrationRequest;
import com.turisticka_agencija.dodatne_aktivnosti.model.AdditionalActivity;
import com.turisticka_agencija.dodatne_aktivnosti.model.Arrangement;
import com.turisticka_agencija.dodatne_aktivnosti.model.Category;
import com.turisticka_agencija.dodatne_aktivnosti.model.Customer;
import com.turisticka_agencija.dodatne_aktivnosti.model.Registration;
import com.turisticka_agencija.dodatne_aktivnosti.repository.AdditionalActivityRepository;
import com.turisticka_agencija.dodatne_aktivnosti.repository.ArrangementRepository;
import com.turisticka_agencija.dodatne_aktivnosti.repository.CategoryRepository;
import com.turisticka_agencija.dodatne_aktivnosti.repository.CustomerRepository;
import com.turisticka_agencija.dodatne_aktivnosti.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService implements ICustomerService {

    private final CustomerRepository customerRepository;
    private final AdditionalActivityRepository additionalActivityRepository;
    private final CategoryRepository categoryRepository;
    private final ArrangementRepository arrangementRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository,
                           AdditionalActivityRepository additionalActivityRepository,
                           CategoryRepository categoryRepository,
                           ArrangementRepository arrangementRepository) {
        this.customerRepository = customerRepository;
        this.additionalActivityRepository = additionalActivityRepository;
        this.categoryRepository = categoryRepository;
        this.arrangementRepository = arrangementRepository;
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer update(Long id, Customer customer) {
        Customer existingCustomer = findById(id);

        existingCustomer.setFirstName(customer.getFirstName());
        existingCustomer.setLastName(customer.getLastName());
        existingCustomer.setAge(customer.getAge());
        existingCustomer.setEmail(customer.getEmail());
        existingCustomer.setContact(customer.getContact());
        existingCustomer.setFavoriteCategories(customer.getFavoriteCategories());
        existingCustomer.setRegistrations(customer.getRegistrations());
        existingCustomer.setBookedArrangements(customer.getBookedArrangements());

        return customerRepository.save(existingCustomer);
    }

    @Override
    public void delete(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Registration addRegistration(Long customerId, RegistrationRequest request) {
        Customer customer = findById(customerId);

        AdditionalActivity activity = additionalActivityRepository.findById(request.getActivityId())
                .orElseThrow(() -> new RuntimeException("Activity not found with id: " + request.getActivityId()));

        Registration registration = new Registration();
        registration.setActivity(activity);
        registration.setRegistrationDate(request.getRegistrationDate());
        registration.setNumberOfPeople(request.getNumberOfPeople());

        customer.getRegistrations().add(registration);
        Customer savedCustomer = customerRepository.save(customer);

        return savedCustomer.getRegistrations().stream()
                .filter(r -> r.getActivity() != null
                        && r.getActivity().getActivityId().equals(activity.getActivityId())
                        && r.getRegistrationDate().equals(request.getRegistrationDate())
                        && r.getNumberOfPeople().equals(request.getNumberOfPeople()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Registration was created, but could not be retrieved."));
    }

    @Override
    public List<Registration> findAllRegistrationsByCustomerId(Long customerId) {
        Customer customer = findById(customerId);
        return new ArrayList<>(customer.getRegistrations());
    }

    @Override
    public Registration findRegistrationById(Long customerId, Long registrationId) {
        Customer customer = findById(customerId);

        return customer.getRegistrations().stream()
                .filter(registration -> registration.getId() != null && registration.getId().equals(registrationId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Registration not found with id: " + registrationId));
    }

    @Override
    public Registration updateRegistration(Long customerId, Long registrationId, RegistrationRequest request) {
        Customer customer = findById(customerId);

        AdditionalActivity activity = additionalActivityRepository.findById(request.getActivityId())
                .orElseThrow(() -> new RuntimeException("Activity not found with id: " + request.getActivityId()));

        Registration registrationToUpdate = customer.getRegistrations().stream()
                .filter(registration -> registration.getId() != null && registration.getId().equals(registrationId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Registration not found with id: " + registrationId));

        registrationToUpdate.setActivity(activity);
        registrationToUpdate.setRegistrationDate(request.getRegistrationDate());
        registrationToUpdate.setNumberOfPeople(request.getNumberOfPeople());

        customerRepository.save(customer);
        return registrationToUpdate;
    }

    @Override
    public void deleteRegistration(Long customerId, Long registrationId) {
        Customer customer = findById(customerId);

        boolean removed = customer.getRegistrations().removeIf(
                registration -> registration.getId() != null && registration.getId().equals(registrationId)
        );

        if (!removed) {
            throw new RuntimeException("Registration not found with id: " + registrationId);
        }

        customerRepository.save(customer);
    }

    @Override
    public Customer addFavoriteCategory(Long customerId, Long categoryId) {
        Customer customer = findById(customerId);
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryId));

        customer.getFavoriteCategories().add(category);
        return customerRepository.save(customer);
    }

    @Override
    public Customer removeFavoriteCategory(Long customerId, Long categoryId) {
        Customer customer = findById(customerId);

        boolean removed = customer.getFavoriteCategories().removeIf(
                category -> category.getCategoryId() != null && category.getCategoryId().equals(categoryId)
        );

        if (!removed) {
            throw new RuntimeException("Favorite category not found for customer. Category id: " + categoryId);
        }

        return customerRepository.save(customer);
    }

    @Override
    public Customer addBookedArrangement(Long customerId, Long arrangementId) {
        Customer customer = findById(customerId);
        Arrangement arrangement = arrangementRepository.findById(arrangementId)
                .orElseThrow(() -> new RuntimeException("Arrangement not found with id: " + arrangementId));

        customer.getBookedArrangements().add(arrangement);
        return customerRepository.save(customer);
    }

    @Override
    public Customer removeBookedArrangement(Long customerId, Long arrangementId) {
        Customer customer = findById(customerId);

        boolean removed = customer.getBookedArrangements().removeIf(
                arrangement -> arrangement.getArrangementId() != null
                        && arrangement.getArrangementId().equals(arrangementId)
        );

        if (!removed) {
            throw new RuntimeException("Booked arrangement not found for customer. Arrangement id: " + arrangementId);
        }

        return customerRepository.save(customer);
    }
}