/**
 * Test runner for Task 1 – Code Generation: Expression Evaluator.
 *
 * Run from the task1-codegen directory:
 *     javac ExpressionEvaluator.java ExpressionEvaluatorTest.java
 *     java ExpressionEvaluatorTest
 *
 * All tests must print PASS for the task to be complete.
 */
public class ExpressionEvaluatorTest {

    static int passed = 0;
    static int failed = 0;
    static final double DELTA = 0.0001;

    public static void main(String[] args) {
        ExpressionEvaluator solution = new ExpressionEvaluator();

        // Basic arithmetic
        test("2 + 3", 5.0, solution);
        test("10 - 4", 6.0, solution);
        test("6 * 7", 42.0, solution);
        test("20 / 4", 5.0, solution);

        // Precedence
        test("2 + 3 * 4", 14.0, solution);
        test("2 * 3 + 4", 10.0, solution);
        test("2 + 3 * 4 - 1", 13.0, solution);
        test("100 / 10 / 2", 5.0, solution);   // left-to-right associativity
        test("20 - 5 - 5", 10.0, solution);    // left-to-right associativity

        // Parentheses
        test("(2 + 3) * 4", 20.0, solution);
        test("2 * (3 + 4)", 14.0, solution);
        test("(2 + 3) * (4 - 1)", 15.0, solution);
        test("2 * (3 + (4 - 1))", 12.0, solution);
        test("((2))", 2.0, solution);

        // Unary minus
        test("-5 + 3", -2.0, solution);
        test("3 - -2", 5.0, solution);
        test("-(2 + 3)", -5.0, solution);
        test("-2 * -3", 6.0, solution);
        test("-2 * 3", -6.0, solution);

        // Decimals
        test("2.5 + 1.5", 4.0, solution);
        test("10 / 4", 2.5, solution);
        test("3.14 * 2", 6.28, solution);

        // Whitespace
        test("   2   +   3   ", 5.0, solution);
        test("2+3*4", 14.0, solution);

        // Division by zero
        testThrows("5 / 0", ArithmeticException.class, solution);
        testThrows("(3 - 3) / (1 - 1)", ArithmeticException.class, solution);

        // Malformed input
        testThrows("", IllegalArgumentException.class, solution);
        testThrows("   ", IllegalArgumentException.class, solution);
        testThrows("(2 + 3", IllegalArgumentException.class, solution);
        testThrows("2 + 3)", IllegalArgumentException.class, solution);
        testThrows("2 + a", IllegalArgumentException.class, solution);
        testThrows("2 +", IllegalArgumentException.class, solution);
        testThrows("()", IllegalArgumentException.class, solution);

        System.out.println("----------------------------------------");
        System.out.println("Results: " + passed + " passed, " + failed + " failed out of " + (passed + failed) + " tests.");
        if (failed == 0) {
            System.out.println("All tests passed! Task 1 complete.");
        } else {
            System.out.println("Some tests failed. Keep going — you're close!");
            System.exit(1);
        }
    }

    static void test(String input, double expected, ExpressionEvaluator solution) {
        try {
            double actual = solution.evaluate(input);
            if (Math.abs(actual - expected) <= DELTA) {
                System.out.println("PASS  evaluate(\"" + input + "\") = " + actual);
                passed++;
            } else {
                System.out.println("FAIL  evaluate(\"" + input + "\") -> expected " + expected + ", got " + actual);
                failed++;
            }
        } catch (Exception e) {
            System.out.println("FAIL  evaluate(\"" + input + "\") -> threw " + e.getClass().getSimpleName() + " (expected " + expected + ")");
            failed++;
        }
    }

    static void testThrows(String input, Class<? extends Exception> expectedType, ExpressionEvaluator solution) {
        try {
            double actual = solution.evaluate(input);
            System.out.println("FAIL  evaluate(\"" + input + "\") -> expected " + expectedType.getSimpleName() + " to be thrown, but returned " + actual);
            failed++;
        } catch (Exception e) {
            if (expectedType.isInstance(e)) {
                System.out.println("PASS  evaluate(\"" + input + "\") correctly threw " + expectedType.getSimpleName());
                passed++;
            } else {
                System.out.println("FAIL  evaluate(\"" + input + "\") -> expected " + expectedType.getSimpleName() + ", threw " + e.getClass().getSimpleName());
                failed++;
            }
        }
    }
}
