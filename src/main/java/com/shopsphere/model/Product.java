package com.shopsphere.model;

import java.io.Serializable;

public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    private String productId;
    private String name;
    private String category;
    private double price;
    private int stockQuantity;

    public Product(String productId, String name, String category,
                   double price, int stockQuantity) {

        this.productId = productId;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public void increaseStock(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive.");
        }

        stockQuantity += quantity;
    }

    public void decreaseStock(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive.");
        }

        if (quantity > stockQuantity) {
            throw new IllegalArgumentException("Insufficient stock.");
        }

        stockQuantity -= quantity;
    }

    @Override
    public String toString() {
        return String.format(
                "%-8s | %-25s | %-15s | ₹%-10.2f | Stock: %d",
                productId,
                name,
                category,
                price,
                stockQuantity
        );
    }
}