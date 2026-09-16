package com.shopsphere.service;

import com.shopsphere.exception.ProductNotFoundException;
import com.shopsphere.model.Product;
import com.shopsphere.repository.DataStore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    private static final Path DATA_FILE = Path.of("shopsphere.dat");

    @BeforeEach
    void cleanBeforeTest() throws IOException {
        Files.deleteIfExists(DATA_FILE);
    }

    @AfterEach
    void cleanAfterTest() throws IOException {
        Files.deleteIfExists(DATA_FILE);
    }

    @Test
    void shouldAddProduct() {

        DataStore dataStore = new DataStore();
        ProductService service = new ProductService(dataStore);

        Product product = new Product(
                "P1001",
                "Wireless Mouse",
                "Electronics",
                599.00,
                20
        );

        service.addProduct(product);

        assertEquals(1, service.getProductCount());
    }

    @Test
    void shouldFindProductById() {

        DataStore dataStore = new DataStore();
        ProductService service = new ProductService(dataStore);

        Product product = new Product(
                "P1002",
                "Keyboard",
                "Electronics",
                999.00,
                15
        );

        service.addProduct(product);

        Product result = service.findProductById("P1002");

        assertEquals("Keyboard", result.getName());
    }

    @Test
    void shouldSearchProductByName() {

        DataStore dataStore = new DataStore();
        ProductService service = new ProductService(dataStore);

        service.addProduct(new Product(
                "P1003",
                "Java Programming Book",
                "Books",
                699.00,
                10
        ));

        List<Product> result = service.searchByName("Java");

        assertEquals(1, result.size());
    }

    @Test
    void shouldRejectDuplicateProductId() {

        DataStore dataStore = new DataStore();
        ProductService service = new ProductService(dataStore);

        Product product = new Product(
                "P1004",
                "Laptop",
                "Electronics",
                55000.00,
                5
        );

        service.addProduct(product);

        assertThrows(
                IllegalArgumentException.class,
                () -> service.addProduct(product)
        );
    }

    @Test
    void shouldThrowExceptionForMissingProduct() {

        DataStore dataStore = new DataStore();
        ProductService service = new ProductService(dataStore);

        assertThrows(
                ProductNotFoundException.class,
                () -> service.findProductById("P9999")
        );
    }
}