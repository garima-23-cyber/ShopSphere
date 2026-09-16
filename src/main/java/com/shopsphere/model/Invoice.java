package com.shopsphere.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Invoice implements Serializable {

    private static final long serialVersionUID = 1L;

    private String invoiceId;
    private String orderId;
    private String customerName;
    private double subtotal;
    private double discount;
    private double tax;
    private double total;
    private LocalDateTime generatedAt;

    public Invoice(String invoiceId,
                   String orderId,
                   String customerName,
                   double subtotal,
                   double discount,
                   double tax,
                   double total) {

        this.invoiceId = invoiceId;
        this.orderId = orderId;
        this.customerName = customerName;
        this.subtotal = subtotal;
        this.discount = discount;
        this.tax = tax;
        this.total = total;
        this.generatedAt = LocalDateTime.now();
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
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

    public double getTotal() {
        return total;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }

    @Override
    public String toString() {

        return "\n" +
                "========================================\n" +
                "              SHOPSPHERE\n" +
                "                INVOICE\n" +
                "========================================\n" +
                "Invoice ID : " + invoiceId + "\n" +
                "Order ID   : " + orderId + "\n" +
                "Customer   : " + customerName + "\n" +
                "Date       : " + generatedAt + "\n" +
                "----------------------------------------\n" +
                String.format("Subtotal   : ₹%.2f%n", subtotal) +
                String.format("Discount   : ₹%.2f%n", discount) +
                String.format("Tax        : ₹%.2f%n", tax) +
                "----------------------------------------\n" +
                String.format("TOTAL      : ₹%.2f%n", total) +
                "========================================\n";
    }
}