package com.shopsphere.service;

import com.shopsphere.exception.InsufficientStockException;
import com.shopsphere.exception.ProductNotFoundException;
import com.shopsphere.model.CartItem;
import com.shopsphere.model.Order;
import com.shopsphere.model.OrderItem;
import com.shopsphere.model.OrderStatus;
import com.shopsphere.model.Product;
import com.shopsphere.repository.DataStore;
import com.shopsphere.util.IdGenerator;

import java.util.ArrayList;
import java.util.List;

public class OrderService {

    private final DataStore dataStore;
    private final CustomerService customerService;
    private final InventoryService inventoryService;
    private final BillingService billingService;

    public OrderService(DataStore dataStore,
                        CustomerService customerService,
                        InventoryService inventoryService) {

        this.dataStore = dataStore;
        this.customerService = customerService;
        this.inventoryService = inventoryService;
        this.billingService = new BillingService();
    }

    public Order placeOrder(String customerId,
                            CartService cartService) {

        // Validate customer
        var customer = customerService.getCustomer(customerId);

        // Validate cart
        if (cartService.isEmpty()) {
            throw new IllegalStateException(
                    "Cannot place an empty order."
            );
        }

        List<CartItem> cartItems = cartService.getItems();

        // Check stock before changing anything
        for (CartItem item : cartItems) {

            Product product = item.getProduct();

            if (!inventoryService.isAvailable(
                    product.getProductId(),
                    item.getQuantity())) {

                throw new InsufficientStockException(
                        "Insufficient stock for product: "
                                + product.getName()
                );
            }
        }

        // Convert cart items into order items
        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem item : cartItems) {

            Product product = item.getProduct();

            orderItems.add(
                    new OrderItem(
                            product.getProductId(),
                            product.getName(),
                            product.getPrice(),
                            item.getQuantity()
                    )
            );
        }

        // Calculate subtotal
        double subtotal = orderItems.stream()
                .mapToDouble(OrderItem::getSubtotal)
                .sum();

        // Calculate discount
        double discount =
                billingService.calculateDiscount(subtotal);

        // Calculate tax after discount
        double amountAfterDiscount =
                subtotal - discount;

        double tax =
                billingService.calculateTax(amountAfterDiscount);

        // Create order
        String orderId =
                IdGenerator.generateOrderId();

        Order order =
                new Order(
                        orderId,
                        customer.getCustomerId(),
                        customer.getName(),
                        orderItems,
                        discount,
                        tax
                );

        // Remove stock only after all validations succeed
        for (CartItem item : cartItems) {

            Product product = item.getProduct();

            inventoryService.removeStock(
                    product.getProductId(),
                    item.getQuantity()
            );
        }

        // Store order
        dataStore.getOrders().add(order);

        // Empty cart
        cartService.clear();

        // Save data
        dataStore.save();

        return order;
    }

    public List<Order> getAllOrders() {

        return new ArrayList<>(
                dataStore.getOrders()
        );
    }

    public Order getOrder(String orderId) {

        for (Order order : dataStore.getOrders()) {

            if (order.getOrderId().equals(orderId)) {
                return order;
            }
        }

        throw new ProductNotFoundException(
                "Order not found: " + orderId
        );
    }

    public void updateOrderStatus(String orderId,
                                  OrderStatus status) {

        Order order = getOrder(orderId);

        order.setStatus(status);

        dataStore.save();
    }
}