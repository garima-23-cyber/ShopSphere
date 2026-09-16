package com.shopsphere.service;

import com.shopsphere.exception.ProductNotFoundException;
import com.shopsphere.model.Product;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    @Test
    void shouldAddProduct() {

        ProductService service = new ProductService();

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

        ProductService service = new ProductService();

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

        ProductService service = new ProductService();

        service.addProduct(new Product(
                "P1003",
                "Java Programming Book",
                "Books",
                699.00,
                10
        ));

        List<Product> result =
                service.searchByName("Java");

        assertEquals(1, result.size());
    }

    @Test
    void shouldRejectDuplicateProductId() {

        ProductService service = new ProductService();

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

        ProductService service = new ProductService();

        assertThrows(
                ProductNotFoundException.class,
                () -> service.findProductById("P9999")
        );
    }
}