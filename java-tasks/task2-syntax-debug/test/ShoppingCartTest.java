import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Task 2 – ShoppingCart Syntax Debug")
public class ShoppingCartTest {

    private ShoppingCart cart;

    @BeforeEach
    void setUp() {
        cart = new ShoppingCart();
    }

    @Test
    @DisplayName("Add one item increases total price")
    void testAddItemUpdatesTotal() {
        cart.addItem("Apple", 1.50);
        assertEquals(1.50, cart.getTotalPrice(), 0.001,
            "After adding Apple ($1.50), total should be $1.50");
    }

    @Test
    @DisplayName("Add multiple items accumulates total correctly")
    void testAddMultipleItems() {
        cart.addItem("Apple", 1.50);
        cart.addItem("Bread", 3.00);
        cart.addItem("Milk", 2.50);
        assertEquals(7.00, cart.getTotalPrice(), 0.001,
            "1.50 + 3.00 + 2.50 = 7.00");
    }

    @Test
    @DisplayName("Add item adds it to the items list")
    void testAddItemAppearsInList() {
        cart.addItem("Cheese", 4.00);
        assertTrue(cart.getItems().contains("Cheese"),
            "After adding 'Cheese', it should appear in the items list");
    }

    @Test
    @DisplayName("Remove item decreases total price")
    void testRemoveItemUpdatesTotal() {
        cart.addItem("Apple", 1.50);
        cart.addItem("Bread", 3.00);
        cart.removeItem("Apple", 1.50);
        assertEquals(3.00, cart.getTotalPrice(), 0.001,
            "After removing Apple ($1.50), total should be $3.00");
    }

    @Test
    @DisplayName("Remove item removes it from items list")
    void testRemoveItemDisappearsFromList() {
        cart.addItem("Apple", 1.50);
        cart.removeItem("Apple", 1.50);
        assertFalse(cart.getItems().contains("Apple"),
            "After removing 'Apple', it should no longer be in the items list");
    }

    @Test
    @DisplayName("Removing an item not in cart changes nothing")
    void testRemoveNonExistentItemNoEffect() {
        cart.addItem("Bread", 3.00);
        cart.removeItem("Banana", 0.99);  // Banana was never added
        assertEquals(3.00, cart.getTotalPrice(), 0.001,
            "Removing an item not in the cart should not change the total");
        assertEquals(1, cart.getItems().size(),
            "Cart should still have 1 item");
    }

    @Test
    @DisplayName("Empty cart has total of 0.0")
    void testEmptyCartTotal() {
        assertEquals(0.0, cart.getTotalPrice(), 0.001,
            "A new empty cart should have a total of $0.00");
    }

    @Test
    @DisplayName("Empty cart has no items")
    void testEmptyCartItems() {
        assertTrue(cart.getItems().isEmpty(),
            "A new empty cart should have no items");
    }

    @Test
    @DisplayName("Cart total handles decimal precision")
    void testDecimalPrecision() {
        cart.addItem("Item A", 0.10);
        cart.addItem("Item B", 0.20);
        assertEquals(0.30, cart.getTotalPrice(), 0.001,
            "0.10 + 0.20 should equal approximately 0.30");
    }
}
