import java.io.*;

/**
 * Test runner for Task 2 – Debugging: Syntax Errors
 *
 * Run from the task2-syntax-debug directory:
 *     javac ShoppingCart.java ShoppingCartTest.java
 *     java ShoppingCartTest
 *
 * NOTE: If ShoppingCart.java still has syntax errors it won't compile —
 * fix those first so both files can be compiled together.
 *
 * All tests must print PASS for the task to be complete.
 */
public class ShoppingCartTest {

    static int passed = 0;
    static int failed = 0;

    public static void main(String[] args) {
        test_addItemUpdatesTotal();
        test_removeItemUpdatesTotal();
        test_removeNonExistentItemNoChange();
        test_multipleItemsTotal();
        test_printReceiptContainsItems();
        test_printReceiptShowsTotal();
        test_emptyCartTotal();

        System.out.println("----------------------------------------");
        System.out.println("Results: " + passed + " passed, " + failed + " failed out of " + (passed + failed) + " tests.");
        if (failed == 0) {
            System.out.println("All tests passed! Task 2 complete.");
        } else {
            System.out.println("Some tests failed. Keep going — you're close!");
            System.exit(1);
        }
    }

    // ---------------------------------------------------------------
    // Helper
    // ---------------------------------------------------------------

    /** Capture System.out output from a Runnable. */
    static String captureOutput(Runnable r) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream old = System.out;
        System.setOut(new PrintStream(baos));
        try { r.run(); } finally { System.setOut(old); }
        return baos.toString();
    }

    static void pass(String name) {
        System.out.println("PASS  " + name);
        passed++;
    }

    static void fail(String name, String reason) {
        System.out.println("FAIL  " + name + " — " + reason);
        failed++;
    }

    // ---------------------------------------------------------------
    // Tests
    // ---------------------------------------------------------------

    static void test_addItemUpdatesTotal() {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem("Apple", 1.50);
        String out = captureOutput(() -> cart.printReceipt());
        if (out.contains("1.5")) {
            pass("addItem updates total price");
        } else {
            fail("addItem updates total price", "total did not reflect added item price");
        }
    }

    static void test_removeItemUpdatesTotal() {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem("Apple", 1.50);
        cart.addItem("Bread", 2.00);
        cart.removeItem("Apple", 1.50);
        String out = captureOutput(() -> cart.printReceipt());
        // After removing Apple ($1.50), total should be $2.00
        if (out.contains("2.0")) {
            pass("removeItem updates total price");
        } else {
            fail("removeItem updates total price", "total after removal looks wrong: " + out.trim());
        }
    }

    static void test_removeNonExistentItemNoChange() {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem("Milk", 1.89);
        cart.removeItem("Ghost", 99.99);   // not in cart
        String out = captureOutput(() -> cart.printReceipt());
        if (out.contains("1.89")) {
            pass("removeItem on missing item leaves total unchanged");
        } else {
            fail("removeItem on missing item leaves total unchanged", "total changed unexpectedly");
        }
    }

    static void test_multipleItemsTotal() {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem("A", 1.00);
        cart.addItem("B", 2.00);
        cart.addItem("C", 3.00);
        String out = captureOutput(() -> cart.printReceipt());
        if (out.contains("6.0")) {
            pass("multiple addItems accumulate total correctly");
        } else {
            fail("multiple addItems accumulate total correctly", "expected total 6.0 in output: " + out.trim());
        }
    }

    static void test_printReceiptContainsItems() {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem("Banana", 0.75);
        cart.addItem("Juice", 3.20);
        String out = captureOutput(() -> cart.printReceipt());
        boolean hasBanana = out.contains("Banana");
        boolean hasJuice  = out.contains("Juice");
        if (hasBanana && hasJuice) {
            pass("printReceipt lists item names");
        } else {
            fail("printReceipt lists item names", "missing items in output: " + out.trim());
        }
    }

    static void test_printReceiptShowsTotal() {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem("Widget", 5.00);
        String out = captureOutput(() -> cart.printReceipt());
        if (out.toLowerCase().contains("total")) {
            pass("printReceipt prints a line containing 'Total'");
        } else {
            fail("printReceipt prints a line containing 'Total'", "no 'Total' found in output");
        }
    }

    static void test_emptyCartTotal() {
        ShoppingCart cart = new ShoppingCart();
        String out = captureOutput(() -> cart.printReceipt());
        if (out.contains("0.0")) {
            pass("empty cart prints total of 0.0");
        } else {
            fail("empty cart prints total of 0.0", "unexpected output: " + out.trim());
        }
    }
}
