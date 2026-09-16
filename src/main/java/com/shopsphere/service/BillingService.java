package com.shopsphere.service;

import com.shopsphere.model.Invoice;
import com.shopsphere.model.Order;
import com.shopsphere.util.IdGenerator;

public class BillingService {

    public Invoice generateInvoice(Order order) {

        return new Invoice(
                IdGenerator.generateInvoiceId(),
                order.getOrderId(),
                order.getCustomerName(),
                order.getSubtotal(),
                order.getDiscount(),
                order.getTax(),
                order.getTotalAmount()
        );
    }

    public double calculateDiscount(double subtotal) {

        if (subtotal >= 10000) {
            return subtotal * 0.10;
        }

        if (subtotal >= 5000) {
            return subtotal * 0.05;
        }

        return 0;
    }

    public double calculateTax(double amountAfterDiscount) {

        return amountAfterDiscount * 0.18;
    }
}