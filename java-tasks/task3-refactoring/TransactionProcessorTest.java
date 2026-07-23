import java.io.*;
import java.lang.reflect.*;
import java.util.*;

/**
 * Test runner for Task 3 – Code Refactoring
 *
 * Run from the task3-refactoring directory:
 *     javac TransactionProcessor.java TransactionProcessorTest.java
 *     java TransactionProcessorTest
 *
 * The tests verify TWO things:
 *   1. The refactored processTransactions() still produces exactly the
 *      same output as before.
 *   2. The class now has at least 5 additional helper methods, and
 *      processTransactions() itself is no longer one long block (i.e.
 *      you actually broke the logic apart, not just renamed the method).
 *
 * All tests must print PASS for the task to be complete.
 */
public class TransactionProcessorTest {

    static int passed = 0;
    static int failed = 0;

    public static void main(String[] args) throws Exception {
        test_depositIncreasesBalance();
        test_withdrawalDecreasesBalance();
        test_feeDecreasesBalance();
        test_interestIncreasesBalance();
        test_mixedTransactionsFinalBalance();
        test_invalidFormatSkipped();
        test_nonNumericAmountSkipped();
        test_nonPositiveAmountSkipped();
        test_unknownTypeSkipped();
        test_summaryTotalsPerCategory();
        test_emptyFileZeroBalance();
        test_methodCountIncreased();
        test_noSingleMonolithicMethod();

        System.out.println("----------------------------------------");
        System.out.println("Results: " + passed + " passed, " + failed + " failed out of " + (passed + failed) + " tests.");
        if (failed == 0) {
            System.out.println("All tests passed! Task 3 complete.");
        } else {
            System.out.println("Some tests failed. Keep going — you're close!");
            System.exit(1);
        }
    }

    // Helpers

    static File writeTempFile(String... lines) throws IOException {
        File f = File.createTempFile("txn_test", ".csv");
        f.deleteOnExit();
        try (PrintWriter pw = new PrintWriter(f)) {
            for (String line : lines) pw.println(line);
        }
        return f;
    }

    static String captureProcessTransactions(String filePath) throws Exception {
        TransactionProcessor tp = new TransactionProcessor();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream old = System.out;
        System.setOut(new PrintStream(baos));
        try {
            tp.processTransactions(filePath);
        } finally {
            System.setOut(old);
        }
        return baos.toString();
    }

    static void pass(String name) {
        System.out.println("PASS  " + name);
        passed++;
    }

    static void fail(String name, String reason) {
        System.out.println("FAIL  " + name + " — " + reason);
        failed++;
    }

    static void expectContains(String name, String out, String expectedSubstring) {
        if (out.contains(expectedSubstring)) {
            pass(name);
        } else {
            fail(name, "expected output to contain \"" + expectedSubstring + "\":\n" + out.trim());
        }
    }

    // Correctness tests (behaviour must not change after refactoring)

    static void test_depositIncreasesBalance() throws Exception {
        File f = writeTempFile("deposit,100");
        String out = captureProcessTransactions(f.getAbsolutePath());
        expectContains("deposit increases balance", out, "Final Balance: $100.00");
    }

    static void test_withdrawalDecreasesBalance() throws Exception {
        File f = writeTempFile("deposit,200", "withdrawal,50");
        String out = captureProcessTransactions(f.getAbsolutePath());
        expectContains("withdrawal decreases balance", out, "Final Balance: $150.00");
    }

    static void test_feeDecreasesBalance() throws Exception {
        File f = writeTempFile("deposit,100", "fee,10");
        String out = captureProcessTransactions(f.getAbsolutePath());
        expectContains("fee decreases balance", out, "Final Balance: $90.00");
    }

    static void test_interestIncreasesBalance() throws Exception {
        File f = writeTempFile("deposit,100", "interest,5.25");
        String out = captureProcessTransactions(f.getAbsolutePath());
        expectContains("interest increases balance", out, "Final Balance: $105.25");
    }

    static void test_mixedTransactionsFinalBalance() throws Exception {
        File f = writeTempFile("deposit,1000", "withdrawal,250", "fee,5.50", "interest,12.75");
        String out = captureProcessTransactions(f.getAbsolutePath());
        // 1000 - 250 - 5.50 + 12.75 = 757.25
        expectContains("mixed transactions produce correct final balance", out, "Final Balance: $757.25");
    }

    static void test_invalidFormatSkipped() throws Exception {
        File f = writeTempFile("deposit,100", "garbage");
        String out = captureProcessTransactions(f.getAbsolutePath());
        expectContains("malformed line is skipped", out, "Skipped invalid transaction: garbage");
        expectContains("malformed line does not affect balance", out, "Final Balance: $100.00");
    }

    static void test_nonNumericAmountSkipped() throws Exception {
        File f = writeTempFile("deposit,100", "deposit,abc");
        String out = captureProcessTransactions(f.getAbsolutePath());
        expectContains("non-numeric amount is skipped", out, "Skipped invalid transaction: deposit,abc");
        expectContains("non-numeric amount does not affect balance", out, "Final Balance: $100.00");
    }

    static void test_nonPositiveAmountSkipped() throws Exception {
        File f = writeTempFile("deposit,100", "withdrawal,-40", "deposit,0");
        String out = captureProcessTransactions(f.getAbsolutePath());
        expectContains("negative amount is skipped", out, "Skipped invalid transaction: withdrawal,-40");
        expectContains("zero amount is skipped", out, "Skipped invalid transaction: deposit,0");
        expectContains("non-positive amounts do not affect balance", out, "Final Balance: $100.00");
    }

    static void test_unknownTypeSkipped() throws Exception {
        File f = writeTempFile("deposit,100", "transfer,50");
        String out = captureProcessTransactions(f.getAbsolutePath());
        expectContains("unknown transaction type is skipped", out, "Skipped invalid transaction: transfer,50");
        expectContains("unknown type does not affect balance", out, "Final Balance: $100.00");
    }

    static void test_summaryTotalsPerCategory() throws Exception {
        File f = writeTempFile("deposit,100", "deposit,50", "withdrawal,30");
        String out = captureProcessTransactions(f.getAbsolutePath());
        expectContains("summary totals deposits correctly", out, "Deposits:    $150.00");
        expectContains("summary totals withdrawals correctly", out, "Withdrawals: $30.00");
        expectContains("summary shows zero fees", out, "Fees:        $0.00");
        expectContains("summary shows zero interest", out, "Interest:    $0.00");
    }

    static void test_emptyFileZeroBalance() throws Exception {
        File f = writeTempFile();  // no lines
        String out = captureProcessTransactions(f.getAbsolutePath());
        expectContains("empty file produces zero balance", out, "Final Balance: $0.00");
    }

    // Structure tests — checking you actually refactored

    /**
     * Counts public/private/protected methods on TransactionProcessor
     * (excluding main and processTransactions themselves).
     * Expects at least 5 helper methods to have been extracted.
     */
    static void test_methodCountIncreased() {
        Method[] methods = TransactionProcessor.class.getDeclaredMethods();
        long helperCount = Arrays.stream(methods)
            .filter(m -> !m.getName().equals("main")
                      && !m.getName().equals("processTransactions"))
            .count();
        if (helperCount >= 5) {
            pass("at least 5 helper methods extracted (found " + helperCount + ")");
        } else {
            fail("at least 5 helper methods extracted",
                 "found only " + helperCount + " helper method(s). "
                 + "Break processTransactions() into smaller methods.");
        }
    }

    /**
     * Reads the source file and checks that processTransactions() is
     * not longer than 15 lines — a simple heuristic that the big block
     * of code has been broken up.
     */
    static void test_noSingleMonolithicMethod() {
        try {
            File src = new File("TransactionProcessor.java");
            List<String> lines = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(src))) {
                String line;
                while ((line = br.readLine()) != null) lines.add(line);
            }

            // Find the method body of processTransactions and count lines
            boolean inMethod = false;
            int braceDepth = 0;
            int methodLines = 0;
            for (String line : lines) {
                if (!inMethod && line.contains("void processTransactions(")) {
                    inMethod = true;
                }
                if (inMethod) {
                    methodLines++;
                    for (char c : line.toCharArray()) {
                        if (c == '{') braceDepth++;
                        if (c == '}') braceDepth--;
                    }
                    if (braceDepth == 0 && methodLines > 1) break;
                }
            }

            if (methodLines <= 15) {
                pass("processTransactions() body is concise after refactoring (" + methodLines + " lines)");
            } else {
                fail("processTransactions() body is concise after refactoring",
                     "method is still " + methodLines + " lines — extract more helpers");
            }
        } catch (IOException e) {
            fail("could not read TransactionProcessor.java", e.getMessage());
        }
    }
}
