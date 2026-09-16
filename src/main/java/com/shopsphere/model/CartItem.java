package com.shopsphere.model;

import java.io.Serializable;

public class CartItem implements Serializable {

    private static final long serialVersionUID = 1L;

    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return product.getPrice() * quantity;
    }

    @Override
    public String toString() {
        return String.format(
                "%-25s | Qty: %-3d | ₹%.2f",
                product.getName(),
                quantity,
                getSubtotal()
        );
    }
}