package com.shopsphere.repository;

import com.shopsphere.model.Customer;
import com.shopsphere.model.Order;
import com.shopsphere.model.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataStore {

    private static final String DATA_FILE = "shopsphere.dat";

    private List<Product> products;
    private List<Customer> customers;
    private List<Order> orders;

    public DataStore() {

        products = new ArrayList<>();
        customers = new ArrayList<>();
        orders = new ArrayList<>();

        load();
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void save() {

        try (ObjectOutputStream output =
                     new ObjectOutputStream(
                             new FileOutputStream(DATA_FILE))) {

            output.writeObject(products);
            output.writeObject(customers);
            output.writeObject(orders);

        } catch (IOException e) {

            System.out.println(
                    "Warning: Unable to save application data."
            );
        }
    }

    @SuppressWarnings("unchecked")
    private void load() {

        File file = new File(DATA_FILE);

        if (!file.exists()) {
            return;
        }

        try (ObjectInputStream input =
                     new ObjectInputStream(
                             new FileInputStream(DATA_FILE))) {

            products = (List<Product>) input.readObject();
            customers = (List<Customer>) input.readObject();
            orders = (List<Order>) input.readObject();

        } catch (IOException | ClassNotFoundException e) {

            System.out.println(
                    "Warning: Existing data could not be loaded. Starting fresh."
            );

            products = new ArrayList<>();
            customers = new ArrayList<>();
            orders = new ArrayList<>();
        }
    }
}