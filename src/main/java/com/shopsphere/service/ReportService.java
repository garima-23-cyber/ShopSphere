package com.shopsphere.service;

import com.shopsphere.model.Order;
import com.shopsphere.model.Product;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReportService {

    private final ProductService productService;
    private final OrderService orderService;

    public ReportService(ProductService productService,
                         OrderService orderService) {
        this.productService = productService;
        this.orderService = orderService;
    }

    // ================= INVENTORY REPORT =================

    public void showInventoryReport() {

        System.out.println();
        System.out.println("========== INVENTORY REPORT ==========");

        List<Product> products =
                productService.getAllProducts();

        System.out.println(
                "Total Products: " + products.size()
        );

        int totalStock = products.stream()
                .mapToInt(Product::getStockQuantity)
                .sum();

        System.out.println(
                "Total Units in Stock: " + totalStock
        );

        double inventoryValue = products.stream()
                .mapToDouble(product ->
                        product.getPrice()
                                * product.getStockQuantity())
                .sum();

        System.out.printf(
                "Inventory Value: ₹%.2f%n",
                inventoryValue
        );

        System.out.println();
        System.out.println("Product Inventory:");

        if (products.isEmpty()) {
            System.out.println("No products available.");
            return;
        }

        System.out.printf(
                "%-8s %-25s %-15s %-12s %s%n",
                "ID",
                "PRODUCT",
                "CATEGORY",
                "PRICE",
                "STOCK"
        );

        System.out.println(
                "----------------------------------------------------------------"
        );

        for (Product product : products) {
            System.out.println(product);
        }
    }

    // ================= SALES REPORT =================

    public void showSalesReport() {

        System.out.println();
        System.out.println("========== SALES REPORT ==========");

        List<Order> orders =
                orderService.getAllOrders();

        long completedOrders = orders.stream()
                .filter(order ->
                        !order.getStatus().name()
                                .equals("CANCELLED"))
                .count();

        double revenue = orders.stream()
                .filter(order ->
                        !order.getStatus().name()
                                .equals("CANCELLED"))
                .mapToDouble(Order::getTotalAmount)
                .sum();

        System.out.println(
                "Total Orders: " + orders.size()
        );

        System.out.println(
                "Completed/Active Orders: "
                        + completedOrders
        );

        System.out.printf(
                "Total Revenue: ₹%.2f%n",
                revenue
        );

        if (completedOrders > 0) {

            double averageOrderValue =
                    revenue / completedOrders;

            System.out.printf(
                    "Average Order Value: ₹%.2f%n",
                    averageOrderValue
            );
        }

        System.out.println();

        if (orders.isEmpty()) {
            System.out.println("No sales recorded yet.");
        } else {

            System.out.println("Order Summary:");

            for (Order order : orders) {

                System.out.printf(
                        "%s | %-20s | %-10s | ₹%.2f%n",
                        order.getOrderId(),
                        order.getCustomerName(),
                        order.getStatus(),
                        order.getTotalAmount()
                );
            }
        }
    }

    // ================= LOW STOCK REPORT =================

    public void showLowStockProducts() {

        System.out.println();
        System.out.println("========== LOW STOCK REPORT ==========");

        List<Product> lowStockProducts =
                productService.getAllProducts()
                        .stream()
                        .filter(product ->
                                product.getStockQuantity() <= 5)
                        .collect(Collectors.toList());

        if (lowStockProducts.isEmpty()) {

            System.out.println(
                    "No low-stock products."
            );

            return;
        }

        System.out.println(
                "Products with stock <= 5:"
        );

        System.out.println();

        System.out.printf(
                "%-8s %-25s %-15s %s%n",
                "ID",
                "PRODUCT",
                "CATEGORY",
                "STOCK"
        );

        System.out.println(
                "------------------------------------------------------------"
        );

        for (Product product : lowStockProducts) {

            System.out.printf(
                    "%-8s %-25s %-15s %d%n",
                    product.getProductId(),
                    product.getName(),
                    product.getCategory(),
                    product.getStockQuantity()
            );
        }
    }

    // ================= CUSTOMER REPORT =================

    public void showCustomerReport() {

        System.out.println();
        System.out.println("========== CUSTOMER REPORT ==========");

        // Customer report is handled through CustomerService
        // in the main application. This method is kept here
        // for future expansion.
        System.out.println(
                "Customer report is currently available through "
                        + "the customer management section."
        );
    }

    // ================= TOP PRODUCTS =================

    public void showTopProducts() {

        System.out.println();
        System.out.println("========== PRODUCT SALES ==========");

        List<Order> orders =
                orderService.getAllOrders();

        Map<String, Integer> productSales =
                orders.stream()
                        .filter(order ->
                                !order.getStatus().name()
                                        .equals("CANCELLED"))
                        .flatMap(order ->
                                order.getItems().stream())
                        .collect(Collectors.groupingBy(
                                item -> item.getProductName(),
                                Collectors.summingInt(
                                        item -> item.getQuantity()
                                )
                        ));

        if (productSales.isEmpty()) {

            System.out.println(
                    "No product sales recorded yet."
            );

            return;
        }

        productSales.entrySet()
                .stream()
                .sorted(
                        Map.Entry
                                .<String, Integer>
                                        comparingByValue()
                                .reversed()
                )
                .forEach(entry ->
                        System.out.println(
                                entry.getKey()
                                        + " -> "
                                        + entry.getValue()
                                        + " units"
                        )
                );
    }
}