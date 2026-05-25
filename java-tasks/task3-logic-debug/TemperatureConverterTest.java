/**
 * Test runner for Task 3 – Debugging: Logic Errors
 *
 * Run from the task3-logic-debug directory:
 *     javac TemperatureConverter.java TemperatureConverterTest.java
 *     java TemperatureConverterTest
 *
 * All tests must print PASS for the task to be complete.
 */
public class TemperatureConverterTest {

    static int passed = 0;
    static int failed = 0;
    static final double DELTA = 0.01;   // tolerance for floating-point comparisons

    public static void main(String[] args) {
        // celsiusToFahrenheit — already correct, just confirming it stays intact
        testCtoF("Boiling point (100°C → 212°F)", 100,  212.0);
        testCtoF("Freezing point (0°C → 32°F)",     0,   32.0);
        testCtoF("Body temp (37°C → 98.6°F)",      37,   98.6);
        testCtoF("Negative (-40°C → -40°F)",       -40,  -40.0);

        // fahrenheitToCelsius — Bug 1 is here
        testFtoC("Boiling point (212°F → 100°C)",  212, 100.0);
        testFtoC("Freezing point (32°F → 0°C)",     32,   0.0);
        testFtoC("Body temp (98.6°F → 37°C)",      98.6,  37.0);
        testFtoC("Negative (-40°F → -40°C)",        -40,  -40.0);

        // classifyTemperature — Bug 2 is here
        testClassify("Hot (30°C)",       30, "Hot");
        testClassify("Warm (20°C)",      20, "Warm");
        testClassify("Warm (15°C)",      15, "Warm");
        testClassify("Cold (5°C)",        5, "Cold");
        testClassify("Cold (0°C)",        0, "Cold");
        testClassify("Freezing (-1°C)",  -1, "Freezing");
        testClassify("Freezing (-20°C)", -20, "Freezing");

        System.out.println("----------------------------------------");
        System.out.println("Results: " + passed + " passed, " + failed + " failed out of " + (passed + failed) + " tests.");
        if (failed == 0) {
            System.out.println("All tests passed! Task 3 complete.");
        } else {
            System.out.println("Some tests failed. Keep going — you're close!");
            System.exit(1);
        }
    }

    static void testCtoF(String label, double celsius, double expected) {
        double actual = TemperatureConverter.celsiusToFahrenheit(celsius);
        if (Math.abs(actual - expected) <= DELTA) {
            System.out.println("PASS  celsiusToFahrenheit: " + label);
            passed++;
        } else {
            System.out.println("FAIL  celsiusToFahrenheit: " + label + " → expected " + expected + ", got " + actual);
            failed++;
        }
    }

    static void testFtoC(String label, double fahrenheit, double expected) {
        double actual = TemperatureConverter.fahrenheitToCelsius(fahrenheit);
        if (Math.abs(actual - expected) <= DELTA) {
            System.out.println("PASS  fahrenheitToCelsius: " + label);
            passed++;
        } else {
            System.out.println("FAIL  fahrenheitToCelsius: " + label + " → expected " + expected + ", got " + actual);
            failed++;
        }
    }

    static void testClassify(String label, double celsius, String expected) {
        String actual = TemperatureConverter.classifyTemperature(celsius);
        if (expected.equals(actual)) {
            System.out.println("PASS  classifyTemperature: " + label);
            passed++;
        } else {
            System.out.println("FAIL  classifyTemperature: " + label + " → expected \"" + expected + "\", got \"" + actual + "\"");
            failed++;
        }
    }
}
