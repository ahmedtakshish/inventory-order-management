public class Product implements Comparable<Product> {
    private String id;
    private String name;
    private String category;
    private double price;
    private int quantity;

    public Product(String id, String name, String category, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }

    // getters and setters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    @Override
    public int compareTo(Product other) {
        return this.name.compareToIgnoreCase(other.name);
    }

    @Override
    public String toString() {
        return String.format("%-8s %-15s %-12s ₹%-10.2f Qty:%d", id, name, category, price, quantity);
    }
}