import java.util.*;

/**
 * Task 2 - Debugging: Logic Errors
 *
 * Every method below compiles and runs, but at least one of them produces
 * the wrong result for some inputs. There are FIVE logic bugs total, one
 * hidden in each method. None of them are syntax errors — the file
 * compiles fine as-is.
 *
 * Find and fix all five so every test passes.
 */
public class LibrarySystem {

    /**
     * Calculates the late fee for a returned book.
     * The first 3 days late are a grace period with no fee.
     * After that, the fee is $0.75 per day late, capped at a maximum of $15.00.
     */
    public static double calculateLateFee(int daysLate) {
        if (daysLate <= 3) {
            return 0.0;
        }
        double fee = (daysLate - 3) * 0.75;
        return Math.max(fee, 15.0);
    }

    /**
     * A member can borrow a new book only if they currently have fewer
     * than 5 books borrowed AND have no unpaid fines (fines <= 0).
     */
    public static boolean isEligibleToBorrow(int booksCurrentlyBorrowed, double unpaidFines) {
        return booksCurrentlyBorrowed < 5 || unpaidFines <= 0;
    }

    /**
     * Ratings are on a 1-5 scale. A rating of 0 means "not yet rated"
     * and must be excluded from the average. Returns the average of the
     * actual (non-zero) ratings, or 0.0 if none exist.
     */
    public static double calculateAverageRating(List<Integer> ratings) {
        int sum = 0;
        int count = 0;
        for (int r : ratings) {
            if (r > 0) {
                sum += r;
                count++;
            }
        }
        if (ratings.size() == 0) {
            return 0.0;
        }
        return (double) sum / ratings.size();
    }

    /**
     * Given parallel lists of book titles and how many days late each one
     * currently is, returns the title of the most overdue book. If several
     * books are tied for most overdue, returns the first one encountered
     * in the list. Returns null if the lists are empty.
     */
    public static String findMostOverdueBook(List<String> titles, List<Integer> daysLateList) {
        if (titles.isEmpty()) {
            return null;
        }
        int maxIndex = 0;
        for (int i = 1; i < titles.size(); i++) {
            if (daysLateList.get(i) >= daysLateList.get(maxIndex)) {
                maxIndex = i;
            }
        }
        return titles.get(maxIndex);
    }

    /**
     * Counts how many times targetAuthor appears in the authors list,
     * ignoring case (e.g. "j.k. rowling" matches "J.K. Rowling").
     */
    public static int countBooksByAuthor(List<String> authors, String targetAuthor) {
        int count = 0;
        for (String author : authors) {
            if (author.equals(targetAuthor)) {
                count++;
            }
        }
        return count;
    }

    // Do not modify main() -- run it to manually check your fixes
    public static void main(String[] args) {
        System.out.println(calculateLateFee(2));    // Expected: 0.0
        System.out.println(calculateLateFee(5));     // Expected: 1.5
        System.out.println(calculateLateFee(30));    // Expected: 15.0
        System.out.println(isEligibleToBorrow(2, 0.0));  // Expected: true
        System.out.println(isEligibleToBorrow(6, 3.0));  // Expected: false
        System.out.println(calculateAverageRating(Arrays.asList(4, 0, 5, 0)));  // Expected: 4.5
        System.out.println(findMostOverdueBook(
            Arrays.asList("A", "B", "C"), Arrays.asList(5, 9, 9)));  // Expected: B
        System.out.println(countBooksByAuthor(
            Arrays.asList("J.K. Rowling", "j.k. rowling"), "J.K. Rowling"));  // Expected: 2
    }
}
