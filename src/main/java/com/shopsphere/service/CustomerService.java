package com.shopsphere.service;

import com.shopsphere.model.Customer;
import com.shopsphere.repository.DataStore;
import com.shopsphere.util.IdGenerator;

import java.util.ArrayList;
import java.util.List;

public class CustomerService {

    private final DataStore dataStore;

    public CustomerService(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    // =========================================================
    // REGISTER CUSTOMER
    // =========================================================

    public Customer registerCustomer(String name,
                                     String email,
                                     String phone,
                                     String address) {

        // Basic validation
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Customer name cannot be empty."
            );
        }

        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Email cannot be empty."
            );
        }

        if (phone == null || phone.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Phone cannot be empty."
            );
        }

        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Address cannot be empty."
            );
        }

        // Check duplicate email
        boolean emailExists =
                dataStore.getCustomers()
                        .stream()
                        .anyMatch(customer ->
                                customer.getEmail()
                                        .equalsIgnoreCase(email.trim())
                        );

        if (emailExists) {
            throw new IllegalArgumentException(
                    "A customer with this email already exists."
            );
        }

        // Generate customer ID
        String customerId =
                IdGenerator.generateCustomerId();

        Customer customer =
                new Customer(
                        customerId,
                        name.trim(),
                        email.trim(),
                        phone.trim(),
                        address.trim()
                );

        dataStore.getCustomers().add(customer);

        // Save to file
        dataStore.save();

        return customer;
    }

    // =========================================================
    // ADD CUSTOMER
    // =========================================================

    public void addCustomer(Customer customer) {

        if (customer == null) {
            throw new IllegalArgumentException(
                    "Customer cannot be null."
            );
        }

        boolean emailExists =
                dataStore.getCustomers()
                        .stream()
                        .anyMatch(c ->
                                c.getEmail()
                                        .equalsIgnoreCase(
                                                customer.getEmail()
                                        )
                        );

        if (emailExists) {
            throw new IllegalArgumentException(
                    "A customer with this email already exists."
            );
        }

        dataStore.getCustomers().add(customer);

        dataStore.save();
    }

    // =========================================================
    // GET CUSTOMER BY ID
    // =========================================================

    public Customer getCustomer(String customerId) {

        if (customerId == null ||
                customerId.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Customer ID cannot be empty."
            );
        }

        return dataStore.getCustomers()
                .stream()
                .filter(customer ->
                        customer.getCustomerId()
                                .equalsIgnoreCase(
                                        customerId.trim()
                                )
                )
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Customer not found: " +
                                        customerId
                        )
                );
    }

    // =========================================================
    // FIND CUSTOMER BY ID
    // =========================================================

    public Customer findById(String customerId) {

        return dataStore.getCustomers()
                .stream()
                .filter(customer ->
                        customer.getCustomerId()
                                .equalsIgnoreCase(customerId)
                )
                .findFirst()
                .orElse(null);
    }

    // =========================================================
    // FIND CUSTOMER BY EMAIL
    // =========================================================

    public Customer findByEmail(String email) {

        if (email == null) {
            return null;
        }

        return dataStore.getCustomers()
                .stream()
                .filter(customer ->
                        customer.getEmail()
                                .equalsIgnoreCase(email.trim())
                )
                .findFirst()
                .orElse(null);
    }

    // =========================================================
    // GET ALL CUSTOMERS
    // =========================================================

    public List<Customer> getAllCustomers() {

        return new ArrayList<>(
                dataStore.getCustomers()
        );
    }

    // =========================================================
    // UPDATE CUSTOMER
    // =========================================================

    public void updateCustomer(String customerId,
                               String name,
                               String email,
                               String phone,
                               String address) {

        Customer customer =
                getCustomer(customerId);

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Customer name cannot be empty."
            );
        }

        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Email cannot be empty."
            );
        }

        if (phone == null || phone.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Phone cannot be empty."
            );
        }

        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Address cannot be empty."
            );
        }

        // Check whether another customer already uses the email
        boolean emailUsedByAnotherCustomer =
                dataStore.getCustomers()
                        .stream()
                        .anyMatch(c ->
                                !c.getCustomerId()
                                        .equalsIgnoreCase(
                                                customerId
                                        )
                                        &&
                                        c.getEmail()
                                                .equalsIgnoreCase(
                                                        email.trim()
                                                )
                        );

        if (emailUsedByAnotherCustomer) {
            throw new IllegalArgumentException(
                    "Another customer already uses this email."
            );
        }

        customer.setName(name.trim());
        customer.setEmail(email.trim());
        customer.setPhone(phone.trim());
        customer.setAddress(address.trim());

        dataStore.save();
    }
}