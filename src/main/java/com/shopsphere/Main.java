package com.shopsphere;

import com.shopsphere.model.Customer;
import com.shopsphere.model.Invoice;
import com.shopsphere.model.Order;
import com.shopsphere.model.OrderStatus;
import com.shopsphere.model.Product;
import com.shopsphere.repository.DataStore;
import com.shopsphere.service.BillingService;
import com.shopsphere.service.CartService;
import com.shopsphere.service.CustomerService;
import com.shopsphere.service.InventoryService;
import com.shopsphere.service.OrderService;
import com.shopsphere.service.ProductService;
import com.shopsphere.service.ReportService;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        // Central data store
        DataStore dataStore = new DataStore();

        // Services
        ProductService productService = new ProductService(dataStore);
        CustomerService customerService = new CustomerService(dataStore);
        InventoryService inventoryService = new InventoryService(productService);
        CartService cartService = new CartService(productService);

        OrderService orderService = new OrderService(
                dataStore,
                customerService,
                inventoryService
        );

        BillingService billingService = new BillingService();

        ReportService reportService = new ReportService(
                productService,
                orderService
        );

        // Load sample data only when database is empty
        loadSampleData(productService, customerService);

        printWelcome();

        boolean running = true;

        while (running) {

            printMenu();

            try {

                int choice = Integer.parseInt(scanner.nextLine().trim());

                switch (choice) {

                    case 1:
                        addProduct(productService);
                        break;

                    case 2:
                        viewProducts(productService);
                        break;

                    case 3:
                        searchProducts(productService);
                        break;

                    case 4:
                        registerCustomer(customerService);
                        break;

                    case 5:
                        viewCustomers(customerService);
                        break;

                    case 6:
                        addToCart(cartService);
                        break;

                    case 7:
                        viewCart(cartService);
                        break;

                    case 8:
                        placeOrder(
                                customerService,
                                cartService,
                                orderService,
                                billingService
                        );
                        break;

                    case 9:
                        viewOrders(orderService);
                        break;

                    case 10:
                        updateOrderStatus(orderService);
                        break;

                    case 11:
                        reportService.showInventoryReport();
                        break;

                    case 12:
                        reportService.showSalesReport();
                        break;

                    case 13:
                        reportService.showLowStockProducts();
                        break;

                    case 14:
                        running = false;

                        System.out.println();
                        System.out.println("Thank you for using ShopSphere!");
                        System.out.println("Application closed successfully.");
                        break;

                    default:
                        System.out.println(
                                "Invalid choice. Please select 1-14."
                        );
                }

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid input. Please enter a valid number."
                );

            } catch (Exception e) {

                System.out.println(
                        "Error: " + e.getMessage()
                );
            }
        }

        scanner.close();
    }


    // =========================================================
    // WELCOME SCREEN
    // =========================================================

    private static void printWelcome() {

        System.out.println();
        System.out.println(
                "=================================================="
        );
        System.out.println(
                "                 SHOPSPHERE"
        );
        System.out.println(
                "          CONSOLE E-COMMERCE SYSTEM"
        );
        System.out.println(
                "=================================================="
        );
        System.out.println(
                "Manage products, customers, carts, orders and billing."
        );
    }


    // =========================================================
    // MAIN MENU
    // =========================================================

    private static void printMenu() {

        System.out.println();
        System.out.println(
                "--------------- MAIN MENU ----------------"
        );

        System.out.println("1.  Add Product");
        System.out.println("2.  View Products");
        System.out.println("3.  Search Products");
        System.out.println("4.  Register Customer");
        System.out.println("5.  View Customers");
        System.out.println("6.  Add Product To Cart");
        System.out.println("7.  View Cart");
        System.out.println("8.  Place Order");
        System.out.println("9.  View Orders");
        System.out.println("10. Update Order Status");
        System.out.println("11. Inventory Report");
        System.out.println("12. Sales Report");
        System.out.println("13. Low Stock Report");
        System.out.println("14. Exit");

        System.out.println(
                "------------------------------------------"
        );

        System.out.print("Enter your choice: ");
    }


    // =========================================================
    // PRODUCT MANAGEMENT
    // =========================================================

    private static void addProduct(
            ProductService productService) {

        System.out.println();
        System.out.println("========== ADD PRODUCT ==========");

        System.out.print("Product name: ");
        String name = scanner.nextLine();

        System.out.print("Category: ");
        String category = scanner.nextLine();

        System.out.print("Price: ");
        double price = Double.parseDouble(scanner.nextLine());

        System.out.print("Stock quantity: ");
        int stock = Integer.parseInt(scanner.nextLine());

        Product product = productService.addProduct(
                name,
                category,
                price,
                stock
        );

        System.out.println();
        System.out.println("Product added successfully!");

        // Correct method from Product.java
        System.out.println(
                "Generated Product ID: " +
                        product.getProductId()
        );
    }


    private static void viewProducts(
            ProductService productService) {

        System.out.println();
        System.out.println(
                "================ PRODUCTS ================"
        );

        List<Product> products =
                productService.getAllProducts();

        if (products.isEmpty()) {

            System.out.println(
                    "No products available."
            );

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


    private static void searchProducts(
            ProductService productService) {

        System.out.println();
        System.out.println(
                "========== SEARCH PRODUCTS =========="
        );

        System.out.print(
                "Enter product name/category: "
        );

        String keyword = scanner.nextLine();

        List<Product> products =
                productService.searchProducts(keyword);

        if (products.isEmpty()) {

            System.out.println(
                    "No matching products found."
            );

            return;
        }

        System.out.println();
        System.out.println("Search Results:");

        for (Product product : products) {

            System.out.println(product);
        }
    }


    // =========================================================
    // CUSTOMER MANAGEMENT
    // =========================================================

    private static void registerCustomer(
            CustomerService customerService) {

        System.out.println();
        System.out.println(
                "========== REGISTER CUSTOMER =========="
        );

        System.out.print("Customer name: ");
        String name = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Phone: ");
        String phone = scanner.nextLine();

        System.out.print("Address: ");
        String address = scanner.nextLine();

        Customer customer =
                customerService.registerCustomer(
                        name,
                        email,
                        phone,
                        address
                );

        System.out.println();
        System.out.println(
                "Customer registered successfully!"
        );

        // Correct method from Customer.java
        System.out.println(
                "Customer ID: " +
                        customer.getCustomerId()
        );
    }


    private static void viewCustomers(
            CustomerService customerService) {

        System.out.println();
        System.out.println(
                "================ CUSTOMERS ================"
        );

        List<Customer> customers =
                customerService.getAllCustomers();

        if (customers.isEmpty()) {

            System.out.println(
                    "No customers registered."
            );

            return;
        }

        System.out.printf(
                "%-8s %-20s %-30s %-15s%n",
                "ID",
                "NAME",
                "EMAIL",
                "PHONE"
        );

        System.out.println(
                "----------------------------------------------------------------"
        );

        for (Customer customer : customers) {

            System.out.println(customer);
        }
    }


    // =========================================================
    // CART MANAGEMENT
    // =========================================================

    private static void addToCart(
            CartService cartService) {

        System.out.println();
        System.out.println(
                "========== ADD TO CART =========="
        );

        System.out.print("Product ID: ");
        String productId = scanner.nextLine();

        System.out.print("Quantity: ");
        int quantity =
                Integer.parseInt(scanner.nextLine());

        cartService.addToCart(
                productId,
                quantity
        );

        System.out.println(
                "Product successfully added to cart."
        );
    }


    private static void viewCart(
            CartService cartService) {

        System.out.println();
        System.out.println(
                "================ SHOPPING CART ================"
        );

        if (cartService.isEmpty()) {

            System.out.println(
                    "Your cart is empty."
            );

            return;
        }

        System.out.printf(
                "%-25s %-10s %-12s%n",
                "PRODUCT",
                "QUANTITY",
                "TOTAL"
        );

        System.out.println(
                "------------------------------------------------------------"
        );

        for (var item : cartService.getItems()) {

            System.out.println(item);
        }

        System.out.println(
                "------------------------------------------------------------"
        );

        System.out.printf(
                "Cart Total: ₹%.2f%n",
                cartService.getTotal()
        );
    }


    // =========================================================
    // ORDER MANAGEMENT
    // =========================================================

    private static void placeOrder(
            CustomerService customerService,
            CartService cartService,
            OrderService orderService,
            BillingService billingService) {

        System.out.println();
        System.out.println(
                "========== PLACE ORDER =========="
        );

        if (cartService.isEmpty()) {

            System.out.println(
                    "Cannot place order because the cart is empty."
            );

            return;
        }

        System.out.println(
                "Available Customers:"
        );

        viewCustomers(customerService);

        System.out.print(
                "Enter Customer ID: "
        );

        String customerId =
                scanner.nextLine();

        Order order =
                orderService.placeOrder(
                        customerId,
                        cartService
                );

        Invoice invoice =
                billingService.generateInvoice(order);

        System.out.println();
        System.out.println(
                "Order placed successfully!"
        );

        System.out.println(invoice);
    }


    private static void viewOrders(
            OrderService orderService) {

        System.out.println();
        System.out.println(
                "================ ORDERS ================"
        );

        List<Order> orders =
                orderService.getAllOrders();

        if (orders.isEmpty()) {

            System.out.println(
                    "No orders available."
            );

            return;
        }

        for (Order order : orders) {

            System.out.println();

            // Correct method from Order.java
            System.out.println(
                    "Order ID      : " +
                            order.getOrderId()
            );

            System.out.println(
                    "Customer ID   : " +
                            order.getCustomerId()
            );

            System.out.println(
                    "Customer Name : " +
                            order.getCustomerName()
            );

            System.out.println(
                    "Order Date    : " +
                            order.getOrderDate()
            );

            System.out.println(
                    "Status        : " +
                            order.getStatus()
            );

            System.out.println(
                    "Subtotal      : ₹" +
                            String.format(
                                    "%.2f",
                                    order.getSubtotal()
                            )
            );

            System.out.println(
                    "Discount      : ₹" +
                            String.format(
                                    "%.2f",
                                    order.getDiscount()
                            )
            );

            System.out.println(
                    "Tax           : ₹" +
                            String.format(
                                    "%.2f",
                                    order.getTax()
                            )
            );

            System.out.println(
                    "Order Total   : ₹" +
                            String.format(
                                    "%.2f",
                                    order.getTotalAmount()
                            )
            );

            System.out.println("Items:");

            for (var item : order.getItems()) {

                System.out.println(
                        "   " + item
                );
            }

            System.out.println(
                    "------------------------------------------"
            );
        }
    }


    private static void updateOrderStatus(
            OrderService orderService) {

        System.out.println();
        System.out.println(
                "========== UPDATE ORDER STATUS =========="
        );

        List<Order> orders =
                orderService.getAllOrders();

        if (orders.isEmpty()) {

            System.out.println(
                    "No orders available."
            );

            return;
        }

        for (Order order : orders) {

            System.out.println(
                    order.getOrderId() +
                            " -> " +
                            order.getStatus()
            );
        }

        System.out.print(
                "Enter Order ID: "
        );

        String orderId =
                scanner.nextLine();

        System.out.println();
        System.out.println("Available statuses:");

        System.out.println("1. CONFIRMED");
        System.out.println("2. SHIPPED");
        System.out.println("3. DELIVERED");
        System.out.println("4. CANCELLED");

        System.out.print(
                "Select status: "
        );

        int choice =
                Integer.parseInt(scanner.nextLine());

        OrderStatus status;

        switch (choice) {

            case 1:
                status = OrderStatus.CONFIRMED;
                break;

            case 2:
                status = OrderStatus.SHIPPED;
                break;

            case 3:
                status = OrderStatus.DELIVERED;
                break;

            case 4:
                status = OrderStatus.CANCELLED;
                break;

            default:
                throw new IllegalArgumentException(
                        "Invalid status choice."
                );
        }

        orderService.updateOrderStatus(
                orderId,
                status
        );

        System.out.println(
                "Order status updated successfully!"
        );
    }


    // =========================================================
    // SAMPLE DATA
    // =========================================================

    private static void loadSampleData(
            ProductService productService,
            CustomerService customerService) {

        /*
         * Add sample products only if no products exist.
         * This prevents duplicate products every time
         * the application starts.
         */

        if (productService.getAllProducts().isEmpty()) {

            productService.addProduct(
                    "Wireless Headphones",
                    "Electronics",
                    2499,
                    15
            );

            productService.addProduct(
                    "Mechanical Keyboard",
                    "Electronics",
                    3499,
                    10
            );

            productService.addProduct(
                    "USB-C Cable",
                    "Accessories",
                    499,
                    30
            );

            productService.addProduct(
                    "Laptop Stand",
                    "Accessories",
                    1299,
                    8
            );
        }

        /*
         * Add demo customer only if no customers exist.
         */

        if (customerService.getAllCustomers().isEmpty()) {

            customerService.registerCustomer(
                    "Demo Customer",
                    "demo@shopsphere.com",
                    "9876543210",
                    "VIT Bhopal"
            );
        }
    }
}