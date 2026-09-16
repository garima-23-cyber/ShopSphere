package com.shopsphere.service;

import com.shopsphere.model.CartItem;
import com.shopsphere.model.Product;

import java.util.ArrayList;
import java.util.List;

public class CartService {

    private final ProductService productService;
    private final List<CartItem> cartItems;

    public CartService(ProductService productService) {
        this.productService = productService;
        this.cartItems = new ArrayList<>();
    }

    // =========================================================
    // ADD PRODUCT TO CART
    // =========================================================

    public void addToCart(String productId, int quantity) {

        if (productId == null || productId.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Product ID cannot be empty."
            );
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException(
                    "Quantity must be greater than zero."
            );
        }

        Product product =
                productService.findProductById(productId);

        if (quantity > product.getStockQuantity()) {
            throw new IllegalArgumentException(
                    "Insufficient stock."
            );
        }

        // Check if product already exists in cart
        for (CartItem item : cartItems) {

            if (item.getProduct()
                    .getProductId()
                    .equalsIgnoreCase(productId.trim())) {

                int newQuantity =
                        item.getQuantity() + quantity;

                if (newQuantity > product.getStockQuantity()) {
                    throw new IllegalArgumentException(
                            "Requested quantity exceeds available stock."
                    );
                }

                item.setQuantity(newQuantity);
                return;
            }
        }

        // Add new product to cart
        cartItems.add(
                new CartItem(product, quantity)
        );
    }


    // =========================================================
    // GET CART ITEMS
    // =========================================================

    public List<CartItem> getItems() {
        return new ArrayList<>(cartItems);
    }


    // =========================================================
    // GET CART TOTAL
    // =========================================================

    public double getTotal() {

        return cartItems.stream()
                .mapToDouble(CartItem::getSubtotal)
                .sum();
    }


    // =========================================================
    // CHECK IF CART IS EMPTY
    // =========================================================

    public boolean isEmpty() {
        return cartItems.isEmpty();
    }


    // =========================================================
    // REMOVE PRODUCT FROM CART
    // =========================================================

    public void removeFromCart(String productId) {

        if (productId == null || productId.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Product ID cannot be empty."
            );
        }

        cartItems.removeIf(item ->
                item.getProduct()
                        .getProductId()
                        .equalsIgnoreCase(productId.trim())
        );
    }


    // =========================================================
    // CLEAR CART
    // =========================================================

    public void clear() {
        cartItems.clear();
    }


    // =========================================================
    // GET NUMBER OF DIFFERENT PRODUCTS
    // =========================================================

    public int getItemCount() {
        return cartItems.size();
    }
}