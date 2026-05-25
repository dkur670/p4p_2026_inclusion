public class TemperatureConverter {

    /**
     * Converts a temperature from Celsius to Fahrenheit.
     * Formula: (celsius × 9/5) + 32
     *
     * @param celsius temperature in Celsius
     * @return temperature in Fahrenheit
     */
    public static double celsiusToFahrenheit(double celsius) {
        return celsius * 9.0 / 5.0 + 32;
    }

    /**
     * Converts a temperature from Fahrenheit to Celsius.
     * Formula: (fahrenheit − 32) × 5/9
     *
     * @param fahrenheit temperature in Fahrenheit
     * @return temperature in Celsius
     */
    public static double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 9.0 / 5.0;  // Bug 1: multiplies by 9/5 instead of dividing (should be * 5/9)
    }

    /**
     * Classifies a temperature in Celsius into a category:
     *   Below 0°C   → "Freezing"
     *   0–14°C      → "Cold"
     *   15–24°C     → "Warm"
     *   25°C and up → "Hot"
     *
     * @param celsius temperature in Celsius
     * @return a string classification
     */
    public static String classifyTemperature(double celsius) {
        if (celsius > 0) {          // Bug 2: should be celsius < 0 (wrong operator)
            return "Freezing";
        } else if (celsius > 15) {
            return "Cold";
        } else if (celsius > 25) {
            return "Warm";
        } else {
            return "Hot";
        }
    }

    public static void main(String[] args) {
        System.out.println(celsiusToFahrenheit(100));  // Expected: 212.0
        System.out.println(fahrenheitToCelsius(212));  // Expected: 100.0  — currently wrong
        System.out.println(classifyTemperature(30));   // Expected: "Hot"  — currently wrong
    }
}
