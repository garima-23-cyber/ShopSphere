package com.shopsphere.service;

import com.shopsphere.exception.ProductNotFoundException;
import com.shopsphere.model.Product;
import com.shopsphere.repository.DataStore;
import com.shopsphere.util.IdGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductService {

    private final DataStore dataStore;

    public ProductService(DataStore dataStore) {
        this.dataStore = dataStore;
    }


    // =========================================================
    // ADD PRODUCT
    // =========================================================

    public Product addProduct(String name,
                              String category,
                              double price,
                              int stockQuantity) {

        // Validate product name
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Product name cannot be empty."
            );
        }

        // Validate category
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Category cannot be empty."
            );
        }

        // Validate price
        if (price < 0) {
            throw new IllegalArgumentException(
                    "Price cannot be negative."
            );
        }

        // Validate stock
        if (stockQuantity < 0) {
            throw new IllegalArgumentException(
                    "Stock cannot be negative."
            );
        }

        // Generate product ID
        String productId = IdGenerator.generateProductId();

        // Create product
        Product product = new Product(
                productId,
                name.trim(),
                category.trim(),
                price,
                stockQuantity
        );

        // Add product to DataStore
        dataStore.getProducts().add(product);

        // Save data
        dataStore.save();

        return product;
    }


    // =========================================================
    // ADD EXISTING PRODUCT
    // =========================================================

    public void addProduct(Product product) {

        if (product == null) {
            throw new IllegalArgumentException(
                    "Product cannot be null."
            );
        }

        if (product.getProductId() == null ||
                product.getProductId().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Product ID cannot be empty."
            );
        }

        if (product.getName() == null ||
                product.getName().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Product name cannot be empty."
            );
        }

        if (product.getCategory() == null ||
                product.getCategory().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Category cannot be empty."
            );
        }

        if (product.getPrice() < 0) {
            throw new IllegalArgumentException(
                    "Price cannot be negative."
            );
        }

        if (product.getStockQuantity() < 0) {
            throw new IllegalArgumentException(
                    "Stock cannot be negative."
            );
        }

        boolean exists = dataStore.getProducts()
                .stream()
                .anyMatch(p ->
                        p.getProductId()
                                .equalsIgnoreCase(
                                        product.getProductId()
                                )
                );

        if (exists) {
            throw new IllegalArgumentException(
                    "Product ID already exists."
            );
        }

        dataStore.getProducts().add(product);
        dataStore.save();
    }


    // =========================================================
    // GET ALL PRODUCTS
    // =========================================================

    public List<Product> getAllProducts() {

        return new ArrayList<>(
                dataStore.getProducts()
        );
    }


    // =========================================================
    // FIND PRODUCT BY ID
    // =========================================================

    public Product findProductById(String productId) {

        if (productId == null ||
                productId.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Product ID cannot be empty."
            );
        }

        return dataStore.getProducts()
                .stream()
                .filter(p ->
                        p.getProductId()
                                .equalsIgnoreCase(
                                        productId.trim()
                                )
                )
                .findFirst()
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product " +
                                        productId +
                                        " not found."
                        )
                );
    }


    // =========================================================
    // FIND PRODUCT BY ID - ALIAS
    // =========================================================

    public Product findById(String productId) {

        return findProductById(productId);
    }


    // =========================================================
    // SEARCH PRODUCTS
    // =========================================================

    public List<Product> searchProducts(String keyword) {

        if (keyword == null ||
                keyword.trim().isEmpty()) {

            return new ArrayList<>();
        }

        String searchKeyword =
                keyword.trim().toLowerCase();

        return dataStore.getProducts()
                .stream()
                .filter(p ->
                        p.getName()
                                .toLowerCase()
                                .contains(searchKeyword)
                                ||
                                p.getCategory()
                                        .toLowerCase()
                                        .contains(searchKeyword)
                )
                .collect(Collectors.toList());
    }


    // =========================================================
    // SEARCH BY NAME
    // =========================================================

    public List<Product> searchByName(String keyword) {

        if (keyword == null ||
                keyword.trim().isEmpty()) {

            return new ArrayList<>();
        }

        String searchKeyword =
                keyword.trim().toLowerCase();

        return dataStore.getProducts()
                .stream()
                .filter(p ->
                        p.getName()
                                .toLowerCase()
                                .contains(searchKeyword)
                )
                .collect(Collectors.toList());
    }


    // =========================================================
    // SEARCH BY CATEGORY
    // =========================================================

    public List<Product> searchByCategory(String category) {

        if (category == null ||
                category.trim().isEmpty()) {

            return new ArrayList<>();
        }

        String searchCategory =
                category.trim();

        return dataStore.getProducts()
                .stream()
                .filter(p ->
                        p.getCategory()
                                .equalsIgnoreCase(
                                        searchCategory
                                )
                )
                .collect(Collectors.toList());
    }


    // =========================================================
    // UPDATE PRODUCT
    // =========================================================

    public void updateProduct(String productId,
                              String name,
                              String category,
                              double price) {

        if (name == null ||
                name.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Product name cannot be empty."
            );
        }

        if (category == null ||
                category.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Category cannot be empty."
            );
        }

        if (price < 0) {
            throw new IllegalArgumentException(
                    "Price cannot be negative."
            );
        }

        Product product =
                findProductById(productId);

        product.setName(name.trim());
        product.setCategory(category.trim());
        product.setPrice(price);

        dataStore.save();
    }


    // =========================================================
    // DELETE PRODUCT
    // =========================================================

    public void deleteProduct(String productId) {

        Product product =
                findProductById(productId);

        dataStore.getProducts().remove(product);

        dataStore.save();
    }


    // =========================================================
    // UPDATE STOCK
    // =========================================================

    public void updateStock(String productId,
                            int stock) {

        if (stock < 0) {
            throw new IllegalArgumentException(
                    "Stock cannot be negative."
            );
        }

        Product product =
                findProductById(productId);

        product.setStockQuantity(stock);

        dataStore.save();
    }


    // =========================================================
    // GET PRODUCT COUNT
    // =========================================================

    public int getProductCount() {

        return dataStore.getProducts().size();
    }
}