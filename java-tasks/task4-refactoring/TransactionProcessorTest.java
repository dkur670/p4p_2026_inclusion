import java.io.*;
import java.lang.reflect.*;
import java.util.*;

/**
 * Test runner for Task 4 – Code Refactoring
 *
 * Run from the task4-refactoring directory:
 *     javac TransactionProcessor.java TransactionProcessorTest.java
 *     java TransactionProcessorTest
 *
 * The tests verify TWO things:
 *   1. The refactored processTransactions() still produces correct output.
 *   2. The class now has at least 3 additional helper methods (i.e. you
 *      actually broke the logic apart, not just renamed the one method).
 *
 * All tests must print PASS for the task to be complete.
 */
public class TransactionProcessorTest {

    static int passed = 0;
    static int failed = 0;

    public static void main(String[] args) throws Exception {
        test_basicDepositAndWithdrawal();
        test_depositsOnly();
        test_withdrawalsOnly();
        test_emptyFile();
        test_largeTransactions();
        test_methodCountIncreased();
        test_noSingleMonolithicMethod();

        System.out.println("----------------------------------------");
        System.out.println("Results: " + passed + " passed, " + failed + " failed out of " + (passed + failed) + " tests.");
        if (failed == 0) {
            System.out.println("All tests passed! Task 4 complete.");
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

    
    // Correctness tests (behaviour must not change after refactoring)
    

    static void test_basicDepositAndWithdrawal() throws Exception {
        File f = writeTempFile("deposit,100", "withdrawal,30", "deposit,50");
        String out = captureProcessTransactions(f.getAbsolutePath());
        // Final balance: 100 - 30 + 50 = 120
        if (out.contains("120")) {
            pass("basic deposit + withdrawal → final balance 120");
        } else {
            fail("basic deposit + withdrawal → final balance 120",
                 "did not find '120' in output:\n" + out.trim());
        }
    }

    static void test_depositsOnly() throws Exception {
        File f = writeTempFile("deposit,200", "deposit,300");
        String out = captureProcessTransactions(f.getAbsolutePath());
        if (out.contains("500")) {
            pass("deposits only → final balance 500");
        } else {
            fail("deposits only → final balance 500",
                 "did not find '500' in output:\n" + out.trim());
        }
    }

    static void test_withdrawalsOnly() throws Exception {
        File f = writeTempFile("withdrawal,40", "withdrawal,60");
        String out = captureProcessTransactions(f.getAbsolutePath());
        if (out.contains("-100")) {
            pass("withdrawals only → final balance -100");
        } else {
            fail("withdrawals only → final balance -100",
                 "did not find '-100' in output:\n" + out.trim());
        }
    }

    static void test_emptyFile() throws Exception {
        File f = writeTempFile();  // no lines
        String out = captureProcessTransactions(f.getAbsolutePath());
        if (out.contains("0")) {
            pass("empty file → final balance 0");
        } else {
            fail("empty file → final balance 0",
                 "did not find '0' in output:\n" + out.trim());
        }
    }

    static void test_largeTransactions() throws Exception {
        File f = writeTempFile("deposit,1000000", "withdrawal,999999");
        String out = captureProcessTransactions(f.getAbsolutePath());
        if (out.contains("1")) {
            pass("large values → final balance 1");
        } else {
            fail("large values → final balance 1",
                 "did not find '1' in output:\n" + out.trim());
        }
    }

    
    // Structure tests — checking you actually refactored

    /**
     * Counts public/private/protected methods on TransactionProcessor
     * (excluding main and processTransactions themselves).
     * Expects at least 3 helper methods to have been extracted.
     */
    static void test_methodCountIncreased() {
        Method[] methods = TransactionProcessor.class.getDeclaredMethods();
        long helperCount = Arrays.stream(methods)
            .filter(m -> !m.getName().equals("main")
                      && !m.getName().equals("processTransactions"))
            .count();
        if (helperCount >= 3) {
            pass("at least 3 helper methods extracted (found " + helperCount + ")");
        } else {
            fail("at least 3 helper methods extracted",
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
                if (!inMethod && line.contains("processTransactions") && line.contains("(")) {
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

            if (methodLines <= 20) {
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
