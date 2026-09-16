package com.shopsphere.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    private String orderId;
    private String customerId;
    private String customerName;
    private List<OrderItem> items;
    private LocalDateTime orderDate;
    private OrderStatus status;
    private double subtotal;
    private double discount;
    private double tax;
    private double totalAmount;

    public Order(String orderId,
                 String customerId,
                 String customerName,
                 List<OrderItem> items,
                 double discount,
                 double tax) {

        this.orderId = orderId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.items = new ArrayList<>(items);
        this.orderDate = LocalDateTime.now();
        this.status = OrderStatus.PLACED;

        this.subtotal = calculateSubtotal();
        this.discount = discount;
        this.tax = tax;
        this.totalAmount = subtotal - discount + tax;
    }

    private double calculateSubtotal() {

        return items.stream()
                .mapToDouble(OrderItem::getSubtotal)
                .sum();
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public List<OrderItem> getItems() {
        return new ArrayList<>(items);
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getDiscount() {
        return discount;
    }

    public double getTax() {
        return tax;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format(
                "%-10s | %-20s | %-19s | %-12s | ₹%.2f",
                orderId,
                customerName,
                orderDate.toString().replace("T", " "),
                status,
                totalAmount
        );
    }
}