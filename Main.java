import java.util.*;

public class Main {
    private static InventoryManager inventoryManager = new InventoryManager();
    private static OrderService orderService = new OrderService(inventoryManager);
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        List<Product> loaded = FileManager.loadInventory();
        for (Product p : loaded) {
            inventoryManager.addProduct(p);
        }

        if (loaded.isEmpty()) {
            seedSampleData();
        }

        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1": addProduct(); break;
                case "2": viewAllProducts(); break;
                case "3": placeOrder(); break;
                case "4": viewOrderHistory(); break;
                case "5": viewLowStock(); break;
                case "6": viewByCategory(); break;
                case "7":
                    FileManager.saveInventory(inventoryManager.getAllProducts());
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
        System.out.println("Goodbye!");
    }

    private static void printMenu() {
        System.out.println("\n===== INVENTORY & ORDER MANAGEMENT =====");
        System.out.println("1. Add Product");
        System.out.println("2. View All Products");
        System.out.println("3. Place Order");
        System.out.println("4. View Order History");
        System.out.println("5. View Low Stock Products");
        System.out.println("6. View Products by Category");
        System.out.println("7. Save & Exit");
        System.out.print("Choose an option: ");
    }

    private static void addProduct() {
        System.out.print("Product ID: ");
        String id = scanner.nextLine().trim();
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Category: ");
        String category = scanner.nextLine().trim();
        System.out.print("Price: ");
        double price = Double.parseDouble(scanner.nextLine().trim());
        System.out.print("Quantity: ");
        int qty = Integer.parseInt(scanner.nextLine().trim());

        inventoryManager.addProduct(new Product(id, name, category, price, qty));
        System.out.println("Product added successfully.");
    }

    private static void viewAllProducts() {
        List<Product> products = inventoryManager.getAllProducts();
        if (products.isEmpty()) {
            System.out.println("No products in inventory.");
            return;
        }
        for (Product p : products) {
            System.out.println(p);
        }
    }

    private static void placeOrder() {
        System.out.print("Product ID: ");
        String id = scanner.nextLine().trim();
        System.out.print("Quantity: ");
        int qty;
        try {
            qty = Integer.parseInt(scanner.nextLine().trim());
            Order order = orderService.placeOrder(id, qty);
            System.out.println("Order placed: " + order);
        } catch (NumberFormatException e) {
            System.out.println("Invalid quantity entered.");
        } catch (ProductNotFoundException | InsufficientStockException | InvalidQuantityException e) {
            System.out.println("Order failed: " + e.getMessage());
        }
    }

    private static void viewOrderHistory() {
        List<Order> history = orderService.getOrderHistory();
        if (history.isEmpty()) {
            System.out.println("No orders placed yet.");
            return;
        }
        for (int i = history.size() - 1; i >= 0; i--) {
            System.out.println(history.get(i));
        }
    }

    private static void viewLowStock() {
        System.out.print("Enter stock threshold: ");
        int threshold = Integer.parseInt(scanner.nextLine().trim());
        List<Product> lowStock = inventoryManager.getLowStockProducts(threshold);
        if (lowStock.isEmpty()) {
            System.out.println("No products below threshold.");
            return;
        }
        for (Product p : lowStock) {
            System.out.println(p);
        }
    }

    private static void viewByCategory() {
        System.out.print("Enter category: ");
        String category = scanner.nextLine().trim();
        List<Product> products = inventoryManager.getProductsByCategory(category);
        if (products.isEmpty()) {
            System.out.println("No products found in category: " + category);
            return;
        }
        for (Product p : products) {
            System.out.println(p);
        }
    }

    private static void seedSampleData() {
        inventoryManager.addProduct(new Product("P001", "Laptop", "Electronics", 55000.00, 10));
        inventoryManager.addProduct(new Product("P002", "Mouse", "Electronics", 500.00, 50));
        inventoryManager.addProduct(new Product("P003", "Notebook", "Stationery", 40.00, 100));
        System.out.println("Sample data loaded.");
    }
}