import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private List<String> items = new ArrayList<>();
    private double totalPrice = 0.0;

    /**
     * Adds an item to the cart and increases the total price.
     *
     * @param item  the name of the item
     * @param price the price of the item
     */
    public void addItem(String item, double price) {
        items.add(item)   // Bug 1: missing semicolon
        totalPrice += price;
    }

    /**
     * Removes an item from the cart and decreases the total price.
     * Does nothing if the item is not in the cart.
     *
     * @param item  the name of the item
     * @param price the price of the item
     */
    public void removeItem(String item, double price) {
        if (items.contains(item) {    // Bug 2: missing closing parenthesis
            items.remove(item);
            totalPrice -= price;
        }
    }

    /**
     * Prints a receipt listing all items and the total price.
     */
    public void printReceipt() {
        System.out.println("Items in cart:");
        for (String item : items) {
            System.out.println("- " + item);
        }
        System.out.println("Total: $" + totalPrice)   // Bug 3: missing semicolon
    }

    /**
     * Returns the current total price (used for testing).
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * Returns the list of items (used for testing).
     */
    public List<String> getItems() {
        return items;
    }
}
