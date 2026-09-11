import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class FileManager {
    private static final String FILE_PATH = "inventory_data.csv";

    public static void saveInventory(List<Product> products) {
        List<String> lines = new ArrayList<>();
        for (Product p : products) {
            lines.add(p.getId() + "," + p.getName() + "," + p.getCategory() + "," + p.getPrice() + "," + p.getQuantity());
        }
        try {
            Path path = Paths.get(FILE_PATH);
            Files.write(path, lines);
            System.out.println("Inventory saved to " + FILE_PATH);
        } catch (IOException e) {
            System.out.println("Error saving inventory: " + e.getMessage());
        }
    }

    public static List<Product> loadInventory() {
        List<Product> products = new ArrayList<>();
        Path path = Paths.get(FILE_PATH);

        if (!Files.exists(path)) {
            System.out.println("No existing inventory file found. Starting fresh.");
            return products;
        }

        try {
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String id = parts[0];
                    String name = parts[1];
                    String category = parts[2];
                    double price = Double.parseDouble(parts[3]);
                    int quantity = Integer.parseInt(parts[4]);
                    products.add(new Product(id, name, category, price, quantity));
                }
            }
            System.out.println("Inventory loaded from " + FILE_PATH);
        } catch (IOException e) {
            System.out.println("Error loading inventory: " + e.getMessage());
        }
        return products;
    }
}