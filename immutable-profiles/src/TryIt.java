import com.example.profiles.*;

public class TryIt {
    public static void main(String[] args) {
        ProfileService svc = new ProfileService();
        UserProfile p = svc.createMinimal("u1", "a@b.com");
        System.out.println("Original: " + p.getEmail());
        
        // Create a new profile with updated display name (immutable approach)
        UserProfile p2 = svc.withDisplayName(p, "John Doe");
        System.out.println("Updated profile display name: " + p2.getDisplayName());
        
        // Demonstrate Builder usage
        UserProfile p3 = new UserProfile.Builder("u2", "test@example.com")
                .displayName("Test User")
                .phone("555-1234")
                .marketingOptIn(true)
                .build();
        System.out.println("Built profile: " + p3.getId() + ", " + p3.getEmail() + ", " + p3.getDisplayName());
        System.out.println("=> UserProfile is now immutable - no setters available!");
    }
}
