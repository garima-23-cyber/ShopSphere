package com.shopsphere.service;

import com.shopsphere.model.Product;

public class InventoryService {

    private final ProductService productService;

    public InventoryService(ProductService productService) {
        this.productService = productService;
    }


    // =========================================================
    // ADD STOCK
    // =========================================================

    public void addStock(String productId, int quantity) {

        if (quantity <= 0) {
            throw new IllegalArgumentException(
                    "Quantity must be greater than zero."
            );
        }

        Product product =
                productService.findProductById(productId);

        product.increaseStock(quantity);
    }


    // =========================================================
    // REMOVE STOCK
    // =========================================================

    public void removeStock(String productId, int quantity) {

        if (quantity <= 0) {
            throw new IllegalArgumentException(
                    "Quantity must be greater than zero."
            );
        }

        Product product =
                productService.findProductById(productId);

        if (quantity > product.getStockQuantity()) {
            throw new IllegalArgumentException(
                    "Insufficient stock for " +
                            product.getName()
            );
        }

        product.decreaseStock(quantity);
    }


    // =========================================================
    // CHECK STOCK AVAILABILITY
    // =========================================================

    public boolean isAvailable(String productId, int quantity) {

        if (quantity <= 0) {
            return false;
        }

        Product product =
                productService.findProductById(productId);

        return product.getStockQuantity() >= quantity;
    }


    // =========================================================
    // CHECK LOW STOCK
    // =========================================================

    public boolean isLowStock(String productId) {

        Product product =
                productService.findProductById(productId);

        return product.getStockQuantity() <= 5;
    }


    // =========================================================
    // GET CURRENT STOCK
    // =========================================================

    public int getStock(String productId) {

        Product product =
                productService.findProductById(productId);

        return product.getStockQuantity();
    }
}