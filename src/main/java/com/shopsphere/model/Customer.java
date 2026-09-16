package com.shopsphere.model;

import java.io.Serializable;

public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    private String customerId;
    private String name;
    private String email;
    private String phone;
    private String address;

    public Customer(String customerId, String name, String email,
                    String phone, String address) {

        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return String.format(
                "%-8s | %-20s | %-30s | %-12s | %s",
                customerId,
                name,
                email,
                phone,
                address
        );
    }
}