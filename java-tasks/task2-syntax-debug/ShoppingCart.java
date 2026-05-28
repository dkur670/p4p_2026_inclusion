import java.util.*;

/**
 * Task 2 – Debugging: Syntax Errors
 *
 * This class has THREE syntax bugs that stop it from compiling.
 * Find and fix all of them.
 *
 * You may use Claude Code to help identify and fix the issues.
 */
public class ShoppingCart {

    private List<String> items = new ArrayList<>();
    private double totalPrice = 0.0;

    public void addItem(String item, double price) {
        items.add(item)          
        totalPrice += price;
    }

    public void removeItem(String item, double price) {
        if (items.contains(item) {   
            items.remove(item);
            totalPrice -= price;
        }
    }

    public void printReceipt() {
        System.out.println("Items in cart:");
        for (String item : items) {
            System.out.println("- " + item);
        }
        System.out.println("Total: $" + totalPrice)   

  
    // Do not modify main() — it is used by the test runner
   
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem("Apple", 1.50);
        cart.addItem("Bread", 2.99);
        cart.addItem("Milk", 1.89);
        cart.removeItem("Apple", 1.50);
        cart.printReceipt();
    }
}
