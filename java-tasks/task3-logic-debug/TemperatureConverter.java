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
     */
    public static double celsiusToFahrenheit(double celsius) {
        return celsius * 9 / 5 + 32;   
    }

    /**
     * Converts Fahrenheit to Celsius.
     */
    public static double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 9 / 5;  
    }

    /**
     * Classifies a Celsius temperature into a category:
     *   
     */
    public static String classifyTemperature(double celsius) {
        if (celsius > 0) {         
            return "Freezing";
        } else if (celsius > 15) {
            return "Cold";
        } else if (celsius > 25) {
            return "Warm";
        } else {
            return "Hot";
        }
    }

    
    // Do not modify main() — run it to manually check your fixes
    public static void main(String[] args) {
        System.out.println(celsiusToFahrenheit(100));   // Expected: 212.0
        System.out.println(fahrenheitToCelsius(212));   // Expected: 100.0
        System.out.println(classifyTemperature(30));    // Expected: Hot
        System.out.println(classifyTemperature(20));    // Expected: Warm
        System.out.println(classifyTemperature(5));     // Expected: Cold
        System.out.println(classifyTemperature(-5));    // Expected: Freezing
    }
}
