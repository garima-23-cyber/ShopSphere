package com.shopsphere.util;

import java.util.concurrent.atomic.AtomicInteger;

public class IdGenerator {

    private static final AtomicInteger productCounter =
            new AtomicInteger(1000);

    private static final AtomicInteger customerCounter =
            new AtomicInteger(1000);

    private static final AtomicInteger orderCounter =
            new AtomicInteger(1000);

    private static final AtomicInteger invoiceCounter =
            new AtomicInteger(1000);

    public static String generateProductId() {
        return "P" + productCounter.incrementAndGet();
    }

    public static String generateCustomerId() {
        return "C" + customerCounter.incrementAndGet();
    }

    public static String generateOrderId() {
        return "ORD" + orderCounter.incrementAndGet();
    }

    public static String generateInvoiceId() {
        return "INV" + invoiceCounter.incrementAndGet();
    }
}