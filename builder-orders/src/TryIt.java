import com.example.orders.*;
import java.util.List;

public class TryIt {
    public static void main(String[] args) {
        OrderLine l1 = new OrderLine("A", 1, 200);
        OrderLine l2 = new OrderLine("B", 3, 100);
        
        // Using Builder pattern
        Order o = new Order.Builder("o2", "a@b.com")
                .addLine(l1)
                .addLine(l2)
                .discountPercent(10)
                .build();
        
        System.out.println("Order total after discount: " + o.totalAfterDiscount());
        System.out.println("Order has " + o.getLines().size() + " lines");
        
        // Test validation
        try {
            Order invalid = new Order.Builder("", "invalid-email").build();
        } catch (IllegalArgumentException e) {
            System.out.println("Validation caught: " + e.getMessage());
        }
        
        // Test using OrderService
        OrderService service = new OrderService();
        Order serviceOrder = service.createOrder("o3", "test@example.com", 
                List.of(new OrderLine("SKU1", 2, 500)), 
                15, true, "Rush order");
        System.out.println("Service order total: " + serviceOrder.totalAfterDiscount());
        System.out.println("=> Order is now immutable with centralized validation!");
    }
}
