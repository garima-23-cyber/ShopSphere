# ShopSphere 🛒

## Console-Based E-Commerce Management System

ShopSphere is a Java-based console e-commerce application developed as an evaluated Java programming project.

The application provides basic e-commerce management features including product management, customer registration, shopping cart operations, order placement, billing, inventory management, and reporting.

---

## 🚀 Features

- Add and manage products
- View all available products
- Search products by name or category
- Register customers
- View registered customers
- Add products to shopping cart
- View cart and calculate total
- Place customer orders
- Automatic discount calculation
- Automatic 18% tax calculation
- Generate invoices
- Update order status
- Inventory report
- Sales report
- Low-stock product report
- Persistent local data storage
- Input validation and exception handling

---

## 🛠️ Technologies Used

- **Java 17**
- **Maven**
- **Object-Oriented Programming**
- **Java Collections**
- **Java Streams**
- **Exception Handling**
- **File Serialization**
- **IntelliJ IDEA**

---

## 📁 Project Structure

```text
ShopSphere
│
├── src
│   └── main
│       └── java
│           └── com
│               └── shopsphere
│                   ├── Main.java
│                   │
│                   ├── exception
│                   │   ├── InsufficientStockException.java
│                   │   ├── InvalidInputException.java
│                   │   └── ProductNotFoundException.java
│                   │
│                   ├── model
│                   │   ├── CartItem.java
│                   │   ├── Customer.java
│                   │   ├── Invoice.java
│                   │   ├── Order.java
│                   │   ├── OrderItem.java
│                   │   ├── OrderStatus.java
│                   │   └── Product.java
│                   │
│                   ├── repository
│                   │   └── DataStore.java
│                   │
│                   ├── service
│                   │   ├── BillingService.java
│                   │   ├── CartService.java
│                   │   ├── CustomerService.java
│                   │   ├── InventoryService.java
│                   │   ├── OrderService.java
│                   │   ├── ProductService.java
│                   │   └── ReportService.java
│                   │
│                   └── util
│                       ├── IdGenerator.java
│                       └── InputValidator.java
│
├── pom.xml
├── README.md
├── statement.md
└── .gitignore