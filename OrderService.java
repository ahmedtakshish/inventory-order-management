import java.util.*;

public class OrderService {
    private InventoryManager inventoryManager;
    private Stack<Order> orderHistory;
    private int orderCounter;

    public OrderService(InventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;
        this.orderHistory = new Stack<>();
        this.orderCounter = 1;
    }

    public Order placeOrder(String productId, int quantity)
            throws ProductNotFoundException, InsufficientStockException, InvalidQuantityException {
        Product product = inventoryManager.getProduct(productId);
        inventoryManager.reduceStock(productId, quantity);

        String orderId = "ORD" + String.format("%04d", orderCounter++);
        double total = product.getPrice() * quantity;
        Order order = new Order(orderId, productId, product.getName(), quantity, total);

        orderHistory.push(order);
        return order;
    }

    public Order getLastOrder() {
        if (orderHistory.isEmpty()) {
            return null;
        }
        return orderHistory.peek();
    }

    public List<Order> getOrderHistory() {
        return new ArrayList<>(orderHistory);
    }
}