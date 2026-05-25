import java.util.*;

/**
 * Test runner for Task 1 – Longest Substring Without Repeating Characters.
 *
 * Run from the task1-substring directory:
 *     javac SubstringTask.java SubstringTaskTest.java
 *     java SubstringTaskTest
 *
 * All tests must print PASS for the task to be complete.
 */
public class SubstringTaskTest {

    static int passed = 0;
    static int failed = 0;

    public static void main(String[] args) {
        SubstringTask solution = new SubstringTask();

        // Basic cases
        test("abcabcbb", 3, solution);
        test("bbbbb",    1, solution);
        test("pwwkew",   3, solution);

        // Edge cases
        test("",         0, solution);
        test("a",        1, solution);
        test(" ",        1, solution);    // single space

        // All unique
        test("abcdef",   6, solution);

        // All same character
        test("aaaa",     1, solution);

        // Numbers and symbols
        test("abc123",   6, solution);
        test("aab",      2, solution);

        // Longer mixed input
        test("dvdf",     3, solution);
        test("anviaj",   5, solution);

        System.out.println("----------------------------------------");
        System.out.println("Results: " + passed + " passed, " + failed + " failed out of " + (passed + failed) + " tests.");
        if (failed == 0) {
            System.out.println("All tests passed! Task 1 complete.");
        } else {
            System.out.println("Some tests failed. Keep going — you're close!");
            System.exit(1);
        }
    }

    static void test(String input, int expected, SubstringTask solution) {
        int actual = solution.lengthOfLongestSubstring(input);
        if (actual == expected) {
            System.out.println("PASS  lengthOfLongestSubstring(\"" + input + "\") = " + actual);
            passed++;
        } else {
            System.out.println("FAIL  lengthOfLongestSubstring(\"" + input + "\") → expected " + expected + ", got " + actual);
            failed++;
        }
    }
}
