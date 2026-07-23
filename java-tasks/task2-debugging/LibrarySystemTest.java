import java.util.*;

/**
 * Test runner for Task 2 – Debugging: Logic Errors
 *
 * Run from the task2-debugging directory:
 *     javac LibrarySystem.java LibrarySystemTest.java
 *     java LibrarySystemTest
 *
 * All tests must print PASS for the task to be complete.
 */
public class LibrarySystemTest {

    static int passed = 0;
    static int failed = 0;
    static final double DELTA = 0.01;

    public static void main(String[] args) {
        // calculateLateFee
        testFee("no fee within grace period (0 days)", 0, 0.0);
        testFee("no fee within grace period (3 days, boundary)", 3, 0.0);
        testFee("fee starts on day 4", 4, 0.75);
        testFee("fee accumulates (10 days late)", 10, 5.25);
        testFee("fee is capped at $15 (30 days late)", 30, 15.0);
        testFee("fee reaches the cap exactly (23 days late)", 23, 15.0);

        // isEligibleToBorrow
        testEligible("few books, no fines -> eligible", 2, 0.0, true);
        testEligible("at the 5-book limit -> not eligible", 5, 0.0, false);
        testEligible("few books, but has unpaid fines -> not eligible", 1, 4.50, false);
        testEligible("many books, no fines -> not eligible", 8, 0.0, false);
        testEligible("many books AND unpaid fines -> not eligible", 6, 2.0, false);
        testEligible("zero books, zero fines -> eligible", 0, 0.0, true);

        // calculateAverageRating
        testRating("all ratings present", Arrays.asList(5, 4, 3), 4.0);
        testRating("some unrated (0) entries excluded", Arrays.asList(4, 0, 5, 0), 4.5);
        testRating("single rating", Arrays.asList(3), 3.0);
        testRating("empty list -> 0.0", new ArrayList<>(), 0.0);

        // findMostOverdueBook
        testOverdue("clear single maximum", Arrays.asList("A", "B", "C"), Arrays.asList(2, 9, 4), "B");
        testOverdue("tie goes to the first one encountered", Arrays.asList("A", "B", "C"), Arrays.asList(5, 9, 9), "B");
        testOverdue("all tied -> first book", Arrays.asList("X", "Y", "Z"), Arrays.asList(7, 7, 7), "X");
        testOverdue("empty lists -> null", new ArrayList<>(), new ArrayList<>(), null);
        testOverdue("single book", Arrays.asList("Solo"), Arrays.asList(1), "Solo");

        // countBooksByAuthor
        testAuthorCount("exact case match", Arrays.asList("J.K. Rowling", "George Orwell"), "J.K. Rowling", 1);
        testAuthorCount("case-insensitive match", Arrays.asList("j.k. rowling", "J.K. ROWLING", "George Orwell"), "J.K. Rowling", 2);
        testAuthorCount("no matches", Arrays.asList("George Orwell"), "J.K. Rowling", 0);
        testAuthorCount("empty list", new ArrayList<>(), "J.K. Rowling", 0);

        System.out.println("----------------------------------------");
        System.out.println("Results: " + passed + " passed, " + failed + " failed out of " + (passed + failed) + " tests.");
        if (failed == 0) {
            System.out.println("All tests passed! Task 2 complete.");
        } else {
            System.out.println("Some tests failed. Keep going — you're close!");
            System.exit(1);
        }
    }

    static void testFee(String label, int daysLate, double expected) {
        double actual = LibrarySystem.calculateLateFee(daysLate);
        if (Math.abs(actual - expected) <= DELTA) {
            pass("calculateLateFee: " + label);
        } else {
            fail("calculateLateFee: " + label, "expected " + expected + ", got " + actual);
        }
    }

    static void testEligible(String label, int booksBorrowed, double fines, boolean expected) {
        boolean actual = LibrarySystem.isEligibleToBorrow(booksBorrowed, fines);
        if (actual == expected) {
            pass("isEligibleToBorrow: " + label);
        } else {
            fail("isEligibleToBorrow: " + label, "expected " + expected + ", got " + actual);
        }
    }

    static void testRating(String label, List<Integer> ratings, double expected) {
        double actual = LibrarySystem.calculateAverageRating(ratings);
        if (Math.abs(actual - expected) <= DELTA) {
            pass("calculateAverageRating: " + label);
        } else {
            fail("calculateAverageRating: " + label, "expected " + expected + ", got " + actual);
        }
    }

    static void testOverdue(String label, List<String> titles, List<Integer> daysLate, String expected) {
        String actual = LibrarySystem.findMostOverdueBook(titles, daysLate);
        if (Objects.equals(actual, expected)) {
            pass("findMostOverdueBook: " + label);
        } else {
            fail("findMostOverdueBook: " + label, "expected " + expected + ", got " + actual);
        }
    }

    static void testAuthorCount(String label, List<String> authors, String target, int expected) {
        int actual = LibrarySystem.countBooksByAuthor(authors, target);
        if (actual == expected) {
            pass("countBooksByAuthor: " + label);
        } else {
            fail("countBooksByAuthor: " + label, "expected " + expected + ", got " + actual);
        }
    }

    static void pass(String name) {
        System.out.println("PASS  " + name);
        passed++;
    }

    static void fail(String name, String reason) {
        System.out.println("FAIL  " + name + " — " + reason);
        failed++;
    }
}
