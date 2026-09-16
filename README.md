# 🛒 ShopSphere – Console-Based E-Commerce Management System

ShopSphere is a **Java-based console e-commerce management system** developed as an evaluated project for Programming in Java.

The project demonstrates how core Java concepts such as **Object-Oriented Programming, Collections, Exception Handling, File I/O, Serialization, Java Streams, Modular Programming, and Maven** can be combined to build a functional e-commerce application.

ShopSphere provides a complete basic shopping workflow through a command-line interface, including product management, customer registration, shopping cart operations, order placement, billing, inventory management, and reporting.

---

## 📌 Project Overview

Traditional e-commerce platforms contain multiple interconnected components such as products, customers, carts, orders, inventory, billing, and reports.

ShopSphere implements these major components in a simplified **console-based environment**.

The application allows users to:

- Manage products
- Search products
- Register customers
- Manage customer information
- Add products to a shopping cart
- View cart contents
- Place orders
- Calculate discounts and taxes
- Generate invoices
- Track order status
- Monitor inventory
- Identify low-stock products
- Generate sales reports
- Persist application data using file serialization

The project follows a modular architecture where different responsibilities are separated into models, services, repositories, utilities, and exception classes.

---

# 🎯 Objectives

The main objectives of ShopSphere are:

1. To develop a functional console-based e-commerce system using Java.
2. To demonstrate Object-Oriented Programming principles.
3. To implement product and inventory management.
4. To provide customer registration and management.
5. To implement shopping cart functionality.
6. To implement order placement and order status management.
7. To calculate discounts, taxes, and final billing amounts.
8. To generate invoices for placed orders.
9. To generate inventory, sales, and low-stock reports.
10. To implement exception handling and input validation.
11. To store application data using Java Serialization.
12. To demonstrate modular and maintainable Java project structure.
13. To use Maven for project build and dependency management.
14. To provide a complete command-line executable workflow.

---

# ✨ Key Features

## 1. 📦 Product Management

ShopSphere provides functionality for managing products.

Features include:

- Add new products
- View all products
- Search products
- Search by product name
- Search by category
- Update product information
- Delete products
- Manage product prices
- Maintain product stock quantities
- Generate unique product IDs

Each product contains information such as:

- Product ID
- Product Name
- Category
- Price
- Stock Quantity

---

## 2. 👤 Customer Management

The system provides customer registration and management.

Features include:

- Register customers
- Generate unique customer IDs
- View registered customers
- Search customers by ID
- Search customers by email
- Update customer information
- Validate customer information
- Prevent duplicate customer email registration

Customer information includes:

- Customer ID
- Name
- Email
- Phone
- Address

---

## 3. 🛍️ Shopping Cart

The shopping cart module allows users to prepare products for purchase.

Features include:

- Add products to cart
- Specify product quantity
- Check product availability
- Prevent adding more quantity than available stock
- Increase quantity of an existing cart item
- Remove products from cart
- View cart
- Calculate cart total
- Clear cart after successful order placement

The system validates product availability before adding an item.

---

## 4. 🧾 Order Management

The order management module handles the complete order workflow.

Features include:

- Create orders from cart items
- Generate unique order IDs
- Associate orders with customers
- Calculate order subtotal
- Apply discount
- Calculate tax
- Calculate final order amount
- Reduce product stock after successful order
- View all orders
- View individual orders
- Update order status

Supported order statuses are:

- `PLACED`
- `CONFIRMED`
- `SHIPPED`
- `DELIVERED`
- `CANCELLED`

---

## 5. 💰 Billing & Invoice Generation

ShopSphere includes a billing module for calculating the final payable amount.

### Discount Rules

| Order Subtotal | Discount |
|---|---:|
| Below ₹5,000 | 0% |
| ₹5,000 – ₹9,999 | 5% |
| ₹10,000 and above | 10% |

After applying the discount, an **18% tax** is calculated on the discounted amount.

### Billing Formula

```text
Discount = Subtotal × Discount Rate

Amount After Discount = Subtotal - Discount

Tax = Amount After Discount × 18%

Final Amount = Amount After Discount + Tax
```

An invoice is generated for every successfully placed order.

The invoice contains:

- Invoice ID
- Order ID
- Customer Name
- Subtotal
- Discount
- Tax
- Total Amount
- Invoice Generation Time

---

# 📊 6. Inventory Management

The inventory module manages product stock.

Features include:

- Check current stock
- Add stock
- Remove stock
- Check product availability
- Detect low-stock products
- Automatically reduce stock after an order

The inventory system helps prevent orders from exceeding available stock.

---

# 📈 7. Reporting

ShopSphere provides reporting functionality to understand the current state of the store.

### Inventory Report

Displays information about available products and their stock.

### Sales Report

Provides information about sales and revenue generated from non-cancelled orders.

### Low Stock Report

Identifies products whose stock quantity is below the defined threshold.

These reports help demonstrate data processing and Java Collections/Streams usage.

---

# 💾 8. Data Persistence

ShopSphere uses **Java File I/O and Serialization** to persist application data.

The following information is stored:

- Products
- Customers
- Orders

The data is stored in:

```text
shopsphere.dat
```

When the application starts, previously saved data is loaded automatically.

When important data changes, the application saves the updated information.

The runtime data file is intentionally excluded from Git using `.gitignore`.

---

# ⚠️ 9. Exception Handling & Validation

The project includes input validation and custom exceptions.

Custom exceptions include:

```text
InsufficientStockException
InvalidInputException
ProductNotFoundException
```

Validation is performed for operations such as:

- Empty product names
- Invalid prices
- Invalid stock quantities
- Empty customer information
- Duplicate customer emails
- Invalid product IDs
- Invalid customer IDs
- Invalid order quantities
- Insufficient stock
- Empty shopping carts

This prevents invalid operations from crashing the application unexpectedly.

---

# 🏗️ System Architecture

ShopSphere follows a layered and modular structure.

```text
                    ┌──────────────────────┐
                    │       Main.java      │
                    │   Console Interface  │
                    └──────────┬───────────┘
                               │
                               ▼
                    ┌──────────────────────┐
                    │      Service Layer   │
                    ├──────────────────────┤
                    │ ProductService       │
                    │ CustomerService      │
                    │ CartService          │
                    │ OrderService          │
                    │ BillingService       │
                    │ InventoryService     │
                    │ ReportService        │
                    └──────────┬───────────┘
                               │
                               ▼
                    ┌──────────────────────┐
                    │      Model Layer     │
                    ├──────────────────────┤
                    │ Product              │
                    │ Customer             │
                    │ CartItem             │
                    │ Order                │
                    │ OrderItem            │
                    │ Invoice              │
                    │ OrderStatus          │
                    └──────────┬───────────┘
                               │
                               ▼
                    ┌──────────────────────┐
                    │    Repository Layer  │
                    │      DataStore       │
                    └──────────┬───────────┘
                               │
                               ▼
                    ┌──────────────────────┐
                    │   shopsphere.dat     │
                    │   Persistent Data    │
                    └──────────────────────┘
```

---

# 🔄 Application Workflow

The general workflow of ShopSphere is:

```text
Start Application
       │
       ▼
Load Saved Data
       │
       ▼
Display Main Menu
       │
       ├── Product Management
       │
       ├── Customer Management
       │
       ├── Shopping Cart
       │
       ├── Order Management
       │
       ├── Inventory Reports
       │
       └── Sales Reports
       │
       ▼
Save Updated Data
       │
       ▼
Exit Application
```

---

# 🛒 Order Processing Workflow

```text
Select Customer
       │
       ▼
Select Product
       │
       ▼
Enter Quantity
       │
       ▼
Check Stock
       │
       ├── Insufficient ──► Display Error
       │
       ▼
Add Product to Cart
       │
       ▼
View Cart
       │
       ▼
Place Order
       │
       ▼
Calculate Subtotal
       │
       ▼
Apply Discount
       │
       ▼
Calculate Tax
       │
       ▼
Calculate Final Amount
       │
       ▼
Reduce Inventory
       │
       ▼
Generate Invoice
       │
       ▼
Save Order
       │
       ▼
Clear Cart
```

---

# 📁 Project Structure

```text
ShopSphere/
│
├── pom.xml
├── README.md
├── statement.md
├── .gitignore
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── shopsphere/
│   │               │
│   │               ├── Main.java
│   │               │
│   │               ├── exception/
│   │               │   ├── InsufficientStockException.java
│   │               │   ├── InvalidInputException.java
│   │               │   └── ProductNotFoundException.java
│   │               │
│   │               ├── model/
│   │               │   ├── CartItem.java
│   │               │   ├── Customer.java
│   │               │   ├── Invoice.java
│   │               │   ├── Order.java
│   │               │   ├── OrderItem.java
│   │               │   ├── OrderStatus.java
│   │               │   └── Product.java
│   │               │
│   │               ├── repository/
│   │               │   └── DataStore.java
│   │               │
│   │               ├── service/
│   │               │   ├── BillingService.java
│   │               │   ├── CartService.java
│   │               │   ├── CustomerService.java
│   │               │   ├── InventoryService.java
│   │               │   ├── OrderService.java
│   │               │   ├── ProductService.java
│   │               │   └── ReportService.java
│   │               │
│   │               └── util/
│   │                   ├── IdGenerator.java
│   │                   └── InputValidator.java
│   │
│   └── test/
│       └── java/
│           └── com/
│               └── shopsphere/
│                   └── service/
│                       └── ProductServiceTest.java
│
└── shopsphere.dat
```

> `shopsphere.dat` is generated automatically at runtime and is ignored by Git.

---

# 🧩 Major Modules

The project is divided into multiple meaningful modules/classes:

| Module | Responsibility |
|---|---|
| `ProductService` | Product CRUD and searching |
| `CustomerService` | Customer registration and management |
| `CartService` | Shopping cart operations |
| `OrderService` | Order creation and status management |
| `BillingService` | Discount, tax and invoice calculation |
| `InventoryService` | Stock management |
| `ReportService` | Inventory, sales and low-stock reports |
| `DataStore` | Persistent data storage |
| `InputValidator` | Input validation |
| `IdGenerator` | Unique ID generation |
| Model Classes | Represent application entities |
| Exception Classes | Handle application errors |

---

# 🛠️ Technologies Used

## Programming Language

- Java 17

## Build Tool

- Apache Maven

## Java Concepts

- Object-Oriented Programming
- Classes and Objects
- Encapsulation
- Inheritance
- Exception Handling
- Interfaces/Abstraction where applicable
- Java Collections Framework
- ArrayList
- HashMap
- Java Streams
- Lambda Expressions
- File I/O
- Serialization
- Date and Time API
- Modular Programming

## Development Environment

- IntelliJ IDEA
- Command Line / PowerShell
- Git
- GitHub

---

# 💻 System Requirements

Before running the project, make sure the system has:

### Required

- Java Development Kit (JDK) 17 or later
- Apache Maven
- Git

### Recommended

- IntelliJ IDEA
- Windows PowerShell / Command Prompt / Linux Terminal / macOS Terminal

---

# ⚙️ Installation & Setup

## Step 1 – Clone the Repository

Clone the project from GitHub:

```bash
git clone https://github.com/YOUR-USERNAME/ShopSphere.git
```

Move into the project directory:

```bash
cd ShopSphere
```

Replace:

```text
YOUR-USERNAME
```

with your actual GitHub username.

---

# 🔍 Step 2 – Verify Java

Run:

```bash
java -version
```

The project is developed for Java 17.

Example:

```text
java version "17.x.x"
```

Also verify the Java compiler:

```bash
javac -version
```

---

# 🔍 Step 3 – Verify Maven

Run:

```bash
mvn -version
```

Maven should display its version and Java runtime information.

---

# 🏗️ Step 4 – Compile the Project

Run:

```bash
mvn clean compile
```

A successful compilation should end with:

```text
BUILD SUCCESS
```

---

# ▶️ Step 5 – Run the Application

After compilation, run:

```bash
java -cp target/classes com.shopsphere.Main
```

The ShopSphere console menu will appear.

---

# 🖥️ Running Through IntelliJ IDEA

The project can also be opened using IntelliJ IDEA.

### Steps

1. Open IntelliJ IDEA.
2. Select **Open**.
3. Select the ShopSphere project folder.
4. Allow IntelliJ to import the Maven project.
5. Make sure JDK 17 is selected.
6. Open:

```text
src/main/java/com/shopsphere/Main.java
```

7. Run `Main.java`.

The application will start in the IntelliJ terminal.

---

# 📋 Main Menu

When ShopSphere starts, the following menu is available:

```text
===============================
       SHOPSPHERE
===============================

1. Add Product
2. View Products
3. Search Products
4. Register Customer
5. View Customers
6. Add Product To Cart
7. View Cart
8. Place Order
9. View Orders
10. Update Order Status
11. Inventory Report
12. Sales Report
13. Low Stock Report
14. Exit
```

---

# 🧪 Example Usage

## Add Product

Select:

```text
1
```

Enter:

```text
Product Name
Category
Price
Stock Quantity
```

The system generates a unique Product ID automatically.

Example:

```text
Product ID: P1001
Product Name: Wireless Mouse
Category: Electronics
Price: 799
Stock: 20
```

---

## Register Customer

Select:

```text
4
```

Enter:

```text
Name
Email
Phone
Address
```

Example:

```text
Customer ID: C1001
Name: Demo Customer
Email: demo@example.com
Phone: 9876543210
Address: Bhopal
```

---

## Add Product to Cart

Select:

```text
6
```

Enter the product ID and required quantity.

The system checks whether enough stock is available before adding the product.

---

## View Cart

Select:

```text
7
```

The system displays:

```text
Product
Quantity
Price
Subtotal
Cart Total
```

---

## Place Order

Select:

```text
8
```

The system:

1. Validates the customer.
2. Checks the cart.
3. Checks product stock.
4. Creates order items.
5. Calculates subtotal.
6. Applies discount.
7. Calculates tax.
8. Calculates final amount.
9. Reduces inventory.
10. Creates the order.
11. Generates invoice information.
12. Saves updated data.
13. Clears the cart.

---

# 💰 Example Billing

Suppose:

```text
Subtotal = ₹6,000
```

Discount:

```text
5% of ₹6,000 = ₹300
```

Amount after discount:

```text
₹6,000 - ₹300 = ₹5,700
```

Tax:

```text
18% of ₹5,700 = ₹1,026
```

Final amount:

```text
₹5,700 + ₹1,026 = ₹6,726
```

---

# 📊 Reports

ShopSphere provides three primary report options.

## Inventory Report

Displays available products and stock information.

## Sales Report

Displays sales-related information and revenue from non-cancelled orders.

## Low Stock Report

Displays products with low available stock.

---

# 🧪 Testing

The project contains unit testing for service functionality.

Test source:

```text
src/test/java/com/shopsphere/service/ProductServiceTest.java
```

Run the test suite using:

```bash
mvn test
```

For a complete Maven verification:

```bash
mvn clean verify
```

A successful test/build process should finish with:

```text
BUILD SUCCESS
```

---

# 🔐 Error Handling

The application handles invalid operations using validation and exceptions.

Examples include:

```text
Invalid product ID
Invalid customer ID
Invalid quantity
Negative price
Negative stock
Empty customer details
Duplicate customer email
Product not found
Insufficient stock
Empty cart
```

Custom exceptions used by the application include:

```text
InsufficientStockException
InvalidInputException
ProductNotFoundException
```

---

# 🧠 Object-Oriented Programming Concepts

ShopSphere demonstrates several important Java OOP concepts.

## Encapsulation

Entity classes contain private fields and expose controlled access through getters and setters.

Examples:

```text
Product
Customer
Order
Invoice
CartItem
OrderItem
```

---

## Abstraction

Application responsibilities are separated into different service classes.

For example:

```text
ProductService
CustomerService
OrderService
BillingService
InventoryService
ReportService
```

Each service focuses on a specific responsibility.

---

## Composition

Objects are composed to create larger application entities.

For example:

```text
Order
 └── OrderItem
      └── Product information
```

Similarly:

```text
Cart
 └── CartItem
      └── Product
```

---

# 📚 Java Collections Used

The application makes use of Java Collections for managing dynamic application data.

Examples include:

```java
ArrayList
List
Map
HashMap
```

Collections are used to manage:

- Products
- Customers
- Orders
- Cart items
- Report data

---

# ⚡ Java Stream API

Java Streams are used in areas such as:

- Searching products
- Calculating totals
- Filtering data
- Generating reports
- Processing order information

This demonstrates modern Java collection-processing techniques.

---

# 💾 File Serialization

The project uses Java Serialization to store application data locally.

The persistence flow is:

```text
Java Objects
     │
     ▼
Serialization
     │
     ▼
shopsphere.dat
     │
     ▼
Deserialization
     │
     ▼
Java Objects
```

This allows the application to retain products, customers, and orders between executions.

---

# 🔄 Data Flow

```text
User Input
    │
    ▼
Main.java
    │
    ▼
Service Layer
    │
    ├──────────────► Validation
    │
    ├──────────────► Business Logic
    │
    ├──────────────► Model Objects
    │
    ▼
DataStore
    │
    ▼
shopsphere.dat
```

---

# 🗃️ Sample Data

When the application is started with no existing product/customer data, sample records are available for demonstration.

### Sample Products

| Product | Category | Price | Stock |
|---|---|---:|---:|
| Wireless Headphones | Electronics | ₹2499 | 15 |
| Mechanical Keyboard | Electronics | ₹3499 | 10 |
| USB-C Cable | Accessories | ₹499 | 30 |
| Laptop Stand | Accessories | ₹1299 | 8 |

### Sample Customer

```text
Name: Demo Customer
Email: demo@shopsphere.com
Phone: 9876543210
Address: VIT Bhopal
```

---

# 🧱 Design Principles

The project follows several software design practices:

- Modular structure
- Separation of concerns
- Service-based business logic
- Model-based data representation
- Repository-based persistence
- Input validation
- Exception handling
- Reusable utility classes
- Meaningful class responsibilities
- Maintainable package structure

---

# 🔒 Security Considerations

This project is an educational console application and is not intended for production e-commerce deployment.

The application focuses primarily on:

- Input validation
- Error handling
- Controlled data processing
- Local data persistence

A production system would additionally require:

- Password hashing
- Authentication and authorization
- Secure database access
- HTTPS
- Payment gateway integration
- Secure session management
- Encryption of sensitive information
- Audit logging
- Access control

---

# 📈 Future Enhancements

The following features can be added in future versions:

- Database integration using MySQL/PostgreSQL
- User authentication and login
- Admin and customer roles
- Product image support
- Payment gateway integration
- Order cancellation and refund workflow
- Persistent ID counters
- Advanced search and filtering
- Product reviews and ratings
- Wishlist functionality
- REST API
- Web-based frontend
- Email notifications
- Advanced analytics dashboard
- Automated report export
- Cloud deployment

---

# 📌 Current Scope

The current version focuses on the core e-commerce workflow through a Java command-line interface.

It includes:

```text
Product Management
        ↓
Customer Management
        ↓
Shopping Cart
        ↓
Order Management
        ↓
Billing
        ↓
Inventory
        ↓
Reports
        ↓
Data Persistence
```

---

# 🧾 Project Deliverables

The repository contains:

```text
README.md
statement.md
pom.xml
Source Code
Test Code
.gitignore
```

The detailed project report is submitted separately according to the project evaluation requirements.

---

# 🎓 Academic Purpose

ShopSphere was developed as an academic project to demonstrate practical implementation of Java programming concepts in a complete application.

The project focuses on applying classroom concepts to a real-world inspired problem instead of implementing isolated code examples.

---

# 👩‍💻 Author

**Garima Kumari**

Programming in Java – Evaluated Project

---

# ⚠️ Disclaimer

ShopSphere is an educational project created for academic demonstration.

It is not intended to be used as a production-ready commercial e-commerce platform.

---

# 📄 License

This project is intended for educational and academic use.
