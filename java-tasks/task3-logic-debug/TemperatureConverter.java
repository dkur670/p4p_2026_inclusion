/**
 * Task 3 – Debugging: Logic Errors
 *
 * This class compiles and runs — but produces WRONG results.
 * There are TWO logic bugs hidden in the methods below.
 *
 * Find and fix both bugs so all outputs match the expected values
 * shown in main().
 *
 * You may use Claude Code to help identify where the logic goes wrong.
 */
public class TemperatureConverter {

    /**
     * Converts Celsius to Fahrenheit.
     * Formula: (celsius × 9/5) + 32
     */
    public static double celsiusToFahrenheit(double celsius) {
        return celsius * 9 / 5 + 32;   // This one is correct — don't change it
    }

    /**
     * Converts Fahrenheit to Celsius.
     * Formula: (fahrenheit − 32) × 5 / 9
     *
     * Bug 1 is in this method.
     */
    public static double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 9 / 5;  // Bug: wrong formula
    }

    /**
     * Classifies a Celsius temperature into a category:
     *   Below 0   → "Freezing"
     *   0–14      → "Cold"
     *   15–24     → "Warm"
     *   25+       → "Hot"
     *
     * Bug 2 is in this method.
     */
    public static String classifyTemperature(double celsius) {
        if (celsius > 0) {          // Bug: wrong comparison direction
            return "Freezing";
        } else if (celsius > 15) {
            return "Cold";
        } else if (celsius > 25) {
            return "Warm";
        } else {
            return "Hot";
        }
    }

    // ---------------------------------------------------------------
    // Do not modify main() — run it to manually check your fixes
    // ---------------------------------------------------------------
    public static void main(String[] args) {
        System.out.println(celsiusToFahrenheit(100));   // Expected: 212.0
        System.out.println(fahrenheitToCelsius(212));   // Expected: 100.0
        System.out.println(classifyTemperature(30));    // Expected: Hot
        System.out.println(classifyTemperature(20));    // Expected: Warm
        System.out.println(classifyTemperature(5));     // Expected: Cold
        System.out.println(classifyTemperature(-5));    // Expected: Freezing
    }
}
