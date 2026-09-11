import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Order {
    private String orderId;
    private String productId;
    private String productName;
    private int quantity;
    private double totalPrice;
    private LocalDateTime timestamp;

    public Order(String orderId, String productId, String productName, int quantity, double totalPrice) {
        this.orderId = orderId;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.timestamp = LocalDateTime.now();
    }

    public String getOrderId() { return orderId; }
    public String getProductId() { return productId; }
    public int getQuantity() { return quantity; }
    public double getTotalPrice() { return totalPrice; }
    public LocalDateTime getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return String.format("Order[%s] %s x%d = ₹%.2f on %s",
                orderId, productName, quantity, totalPrice, timestamp.format(fmt));
    }
}