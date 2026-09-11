import java.util.*;

public class InventoryManager {
    private HashMap<String, Product> inventory;
    private TreeMap<String, List<Product>> categoryMap;

    public InventoryManager() {
        inventory = new HashMap<>();
        categoryMap = new TreeMap<>();
    }

    public void addProduct(Product product) {
        inventory.put(product.getId(), product);
        categoryMap.computeIfAbsent(product.getCategory(), k -> new ArrayList<>()).add(product);
    }

    public Product getProduct(String id) throws ProductNotFoundException {
        Product product = inventory.get(id);
        if (product == null) {
            throw new ProductNotFoundException("No product found with ID: " + id);
        }
        return product;
    }

    public void reduceStock(String id, int quantity) throws ProductNotFoundException, InsufficientStockException, InvalidQuantityException {
        if (quantity <= 0) {
            throw new InvalidQuantityException("Quantity must be greater than zero.");
        }
        Product product = getProduct(id);
        if (product.getQuantity() < quantity) {
            throw new InsufficientStockException("Not enough stock for " + product.getName() + ". Available: " + product.getQuantity());
        }
        product.setQuantity(product.getQuantity() - quantity);
    }

    public List<Product> getProductsByCategory(String category) {
        return categoryMap.getOrDefault(category, new ArrayList<>());
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(inventory.values());
    }

    public List<Product> getLowStockProducts(int threshold) {
        List<Product> lowStock = new ArrayList<>();
        for (Product p : inventory.values()) {
            if (p.getQuantity() < threshold) {
                lowStock.add(p);
            }
        }
        lowStock.sort(Comparator.comparingInt(Product::getQuantity));
        return lowStock;
    }
}