import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Task 1 – Longest Substring Without Repeating Characters")
public class SubstringTasksTest {

    private SubstringTasks solution;

    @BeforeEach
    void setUp() {
        solution = new SubstringTasks();
    }

    @Test
    @DisplayName("Classic example: 'abcabcbb' → 3")
    void testAbcabcbb() {
        assertEquals(3, solution.lengthOfLongestSubstring("abcabcbb"),
            "The longest substring without repeating chars in 'abcabcbb' is 'abc' (length 3)");
    }

    @Test
    @DisplayName("All same character: 'bbbbb' → 1")
    void testAllSameChar() {
        assertEquals(1, solution.lengthOfLongestSubstring("bbbbb"),
            "When all characters are the same, the longest non-repeating substring has length 1");
    }

    @Test
    @DisplayName("Mixed: 'pwwkew' → 3")
    void testPwwkew() {
        assertEquals(3, solution.lengthOfLongestSubstring("pwwkew"),
            "The longest substring without repeating chars in 'pwwkew' is 'wke' (length 3)");
    }

    @Test
    @DisplayName("Empty string → 0")
    void testEmptyString() {
        assertEquals(0, solution.lengthOfLongestSubstring(""),
            "An empty string has no substring, so result should be 0");
    }

    @Test
    @DisplayName("Single character → 1")
    void testSingleChar() {
        assertEquals(1, solution.lengthOfLongestSubstring("a"),
            "A single character string has a longest substring of length 1");
    }

    @Test
    @DisplayName("All unique characters: 'abcde' → 5")
    void testAllUnique() {
        assertEquals(5, solution.lengthOfLongestSubstring("abcde"),
            "When all characters are unique, the whole string is the answer");
    }

    @Test
    @DisplayName("Two character repeat: 'au' → 2")
    void testTwoUniqueChars() {
        assertEquals(2, solution.lengthOfLongestSubstring("au"),
            "Two different characters → length 2");
    }

    @Test
    @DisplayName("Repeating pattern: 'dvdf' → 3")
    void testDvdf() {
        assertEquals(3, solution.lengthOfLongestSubstring("dvdf"),
            "In 'dvdf', the longest is 'vdf' (length 3)");
    }

    @Test
    @DisplayName("Numbers and letters: 'a1b2c3' → 6")
    void testNumbersAndLetters() {
        assertEquals(6, solution.lengthOfLongestSubstring("a1b2c3"),
            "All characters unique → whole string is the answer");
    }

    @Test
    @DisplayName("Spaces count as characters: 'hello world' → 6")
    void testWithSpaces() {
        // "lo wor" or "o world" – longest is 6
        assertEquals(6, solution.lengthOfLongestSubstring("hello world"),
            "Spaces are valid characters too; ' world' has length 6 but 'o worl' = 6 chars is longest");
    }
}
