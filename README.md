# Inventory & Order Management System

A command-line Java application to manage a store's product inventory and customer orders. Built as a course project for **Programming in Java** to demonstrate the Collections Framework, exception handling, file I/O (NIO.2), and the Java Date/Time API.

## Features

- Add products to inventory
- View all products, or filter by category
- Place orders against stock (with validation)
- View order history
- View low-stock products against a configurable threshold
- Save inventory to disk on exit, and automatically reload it on the next run

## Technologies / Concepts Used

- **Collections Framework** — `HashMap` for fast product lookup by ID, `TreeMap` for category grouping, `Stack` for order history
- **Custom Exceptions** — `ProductNotFoundException`, `InsufficientStockException`, `InvalidQuantityException`
- **File I/O (NIO.2)** — `Path`, `Files.write`, `Files.readAllLines` for persisting inventory to a CSV file
- **Java Date/Time API** — `LocalDateTime` and `DateTimeFormatter` to timestamp every order

## Project Structure

```
├── Product.java                     # Product model class
├── Order.java                       # Order model class
├── InventoryManager.java            # Core inventory logic (HashMap, TreeMap)
├── OrderService.java                # Order placement and history (Stack)
├── FileManager.java                 # Save/load inventory using NIO.2
├── ProductNotFoundException.java    # Custom exception
├── InsufficientStockException.java  # Custom exception
├── InvalidQuantityException.java    # Custom exception
├── Main.java                        # Entry point with the CLI menu
└── README.md
```

## Prerequisites

- **Java Development Kit (JDK) 8 or higher** installed on your machine.
- Verify your installation by running:
  ```
  java -version
  javac -version
  ```
  If these commands aren't recognized, download and install a JDK from [https://www.oracle.com/java/technologies/downloads/](https://www.oracle.com/java/technologies/downloads/) (or use OpenJDK) and ensure it's added to your system `PATH`.

No external libraries or dependencies are required — the project only uses the standard Java library.

## Setup & Installation

1. Clone the repository:
   ```
   git clone https://github.com/ahmedtakshish/inventory-order-management.git
   cd inventory-order-management
   ```
2. There is no additional configuration needed — the app runs directly from the source files.

## How to Run

1. Compile all Java files:
   ```
   javac *.java
   ```
2. Run the application:
   ```
   java Main
   ```
3. You'll see a menu like this:
   ```
   ===== INVENTORY & ORDER MANAGEMENT =====
   1. Add Product
   2. View All Products
   3. Place Order
   4. View Order History
   5. View Low Stock Products
   6. View Products by Category
   7. Save & Exit
   Choose an option:
   ```
4. Enter the number corresponding to the action you want, and follow the prompts.

### On first run

If no inventory file exists yet, the app automatically loads three sample products (Laptop, Mouse, Notebook) so you can explore the menu right away.

### Saving your data

Choosing option **7 (Save & Exit)** writes your current inventory to `inventory_data.csv` in the project folder. The next time you run `java Main`, this file is automatically loaded, so your inventory persists between sessions.

## Usage Example

```
Choose an option: 3
Product ID: P001
Quantity: 2
Order placed: Order[ORD0001] Laptop x2 = Rs.110000.00 on 11-09-2026 14:29:59
```

If you try to order more than what's in stock, the app catches this and shows a clear error instead of crashing:

```
Choose an option: 3
Product ID: P001
Quantity: 999
Order failed: Not enough stock for Laptop. Available: 8
```

## Notes

- Product IDs are case-sensitive (e.g. `P001` and `p001` are treated as different values).
- The low-stock threshold and category filters are entered at runtime, so you can check different thresholds/categories without restarting the app.

## Author

Takshish Ahmed Zafar
B.Tech Artificial Intelligence, VIT Bhopalgit add README.md
