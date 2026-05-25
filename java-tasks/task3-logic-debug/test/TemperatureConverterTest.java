import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Task 3 – TemperatureConverter Logic Debug")
public class TemperatureConverterTest {

    // ── celsiusToFahrenheit (already correct – these should pass from the start) ──

    @Test
    @DisplayName("celsiusToFahrenheit: 0°C → 32°F")
    void testCtoF_freezing() {
        assertEquals(32.0, TemperatureConverter.celsiusToFahrenheit(0), 0.01,
            "0°C should be 32°F");
    }

    @Test
    @DisplayName("celsiusToFahrenheit: 100°C → 212°F")
    void testCtoF_boiling() {
        assertEquals(212.0, TemperatureConverter.celsiusToFahrenheit(100), 0.01,
            "100°C (boiling) should be 212°F");
    }

    @Test
    @DisplayName("celsiusToFahrenheit: 37°C → 98.6°F (body temperature)")
    void testCtoF_bodyTemp() {
        assertEquals(98.6, TemperatureConverter.celsiusToFahrenheit(37), 0.01,
            "37°C should be 98.6°F (normal body temperature)");
    }

    // ── fahrenheitToCelsius (Bug 1 – formula uses wrong operator) ──

    @Test
    @DisplayName("fahrenheitToCelsius: 212°F → 100°C (boiling)")
    void testFtoC_boiling() {
        assertEquals(100.0, TemperatureConverter.fahrenheitToCelsius(212), 0.01,
            "212°F should be 100°C (boiling point of water)");
    }

    @Test
    @DisplayName("fahrenheitToCelsius: 32°F → 0°C (freezing)")
    void testFtoC_freezing() {
        assertEquals(0.0, TemperatureConverter.fahrenheitToCelsius(32), 0.01,
            "32°F should be 0°C (freezing point of water)");
    }

    @Test
    @DisplayName("fahrenheitToCelsius: 98.6°F → 37°C (body temperature)")
    void testFtoC_bodyTemp() {
        assertEquals(37.0, TemperatureConverter.fahrenheitToCelsius(98.6), 0.01,
            "98.6°F should be 37°C (normal body temperature)");
    }

    @Test
    @DisplayName("fahrenheitToCelsius: -40°F → -40°C (they meet here)")
    void testFtoC_minus40() {
        assertEquals(-40.0, TemperatureConverter.fahrenheitToCelsius(-40), 0.01,
            "-40°F equals -40°C — the two scales meet at this point");
    }

    // ── classifyTemperature (Bug 2 – first condition uses wrong comparison operator) ──

    @Test
    @DisplayName("classifyTemperature: -5°C → 'Freezing'")
    void testClassify_freezing() {
        assertEquals("Freezing", TemperatureConverter.classifyTemperature(-5),
            "-5°C is below 0, so should be classified as Freezing");
    }

    @Test
    @DisplayName("classifyTemperature: 0°C → 'Cold' (boundary)")
    void testClassify_zero() {
        assertEquals("Cold", TemperatureConverter.classifyTemperature(0),
            "0°C is the boundary — not below 0, so it should be Cold");
    }

    @Test
    @DisplayName("classifyTemperature: 10°C → 'Cold'")
    void testClassify_cold() {
        assertEquals("Cold", TemperatureConverter.classifyTemperature(10),
            "10°C is between 0 and 15, so should be Cold");
    }

    @Test
    @DisplayName("classifyTemperature: 20°C → 'Warm'")
    void testClassify_warm() {
        assertEquals("Warm", TemperatureConverter.classifyTemperature(20),
            "20°C is between 15 and 25, so should be Warm");
    }

    @Test
    @DisplayName("classifyTemperature: 30°C → 'Hot'")
    void testClassify_hot() {
        assertEquals("Hot", TemperatureConverter.classifyTemperature(30),
            "30°C is 25 or above, so should be Hot");
    }

    @Test
    @DisplayName("classifyTemperature: -20°C → 'Freezing'")
    void testClassify_veryColde() {
        assertEquals("Freezing", TemperatureConverter.classifyTemperature(-20),
            "-20°C is well below 0, should be Freezing");
    }
}
