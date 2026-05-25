import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.nio.file.*;
import java.util.List;

@DisplayName("Task 4 – TransactionProcessor Refactor")
public class TransactionProcessorTest {

    private TransactionProcessor processor;

    @BeforeEach
    void setUp() {
        processor = new TransactionProcessor();
    }

    // ── Tests for parseTransaction (must exist after refactoring) ──

    @Test
    @DisplayName("parseTransaction: 'deposit,100' returns type='deposit' and amount='100'")
    void testParseTransactionDeposit() {
        String[] result = processor.parseTransaction("deposit,100");
        assertNotNull(result, "parseTransaction should not return null");
        assertEquals(2, result.length, "Result should have 2 elements: [type, amount]");
        assertEquals("deposit", result[0].trim(), "First element should be the transaction type");
        assertEquals("100", result[1].trim(), "Second element should be the amount");
    }

    @Test
    @DisplayName("parseTransaction: 'withdrawal,40' returns type='withdrawal' and amount='40'")
    void testParseTransactionWithdrawal() {
        String[] result = processor.parseTransaction("withdrawal,40");
        assertNotNull(result, "parseTransaction should not return null");
        assertEquals("withdrawal", result[0].trim());
        assertEquals("40", result[1].trim());
    }

    @Test
    @DisplayName("parseTransaction handles whitespace around values")
    void testParseTransactionWithSpaces() {
        String[] result = processor.parseTransaction(" deposit , 250 ");
        assertNotNull(result);
        assertEquals("deposit", result[0].trim());
        assertEquals("250", result[1].trim());
    }

    // ── Tests for applyTransaction (must exist after refactoring) ──

    @Test
    @DisplayName("applyTransaction: deposit adds to balance")
    void testApplyDeposit() {
        int newBalance = processor.applyTransaction("deposit", 100, 0);
        assertEquals(100, newBalance,
            "Depositing $100 to a balance of $0 should give $100");
    }

    @Test
    @DisplayName("applyTransaction: withdrawal subtracts from balance")
    void testApplyWithdrawal() {
        int newBalance = processor.applyTransaction("withdrawal", 40, 100);
        assertEquals(60, newBalance,
            "Withdrawing $40 from a balance of $100 should give $60");
    }

    @Test
    @DisplayName("applyTransaction: unknown type leaves balance unchanged")
    void testApplyUnknownType() {
        int newBalance = processor.applyTransaction("refund", 50, 200);
        assertEquals(200, newBalance,
            "An unknown transaction type should not change the balance");
    }

    @Test
    @DisplayName("applyTransaction: multiple deposits accumulate correctly")
    void testApplyMultipleDeposits() {
        int balance = 0;
        balance = processor.applyTransaction("deposit", 100, balance);
        balance = processor.applyTransaction("deposit", 200, balance);
        balance = processor.applyTransaction("deposit", 50, balance);
        assertEquals(350, balance,
            "Three deposits of 100, 200, and 50 should total 350");
    }

    @Test
    @DisplayName("applyTransaction: mixed deposits and withdrawals")
    void testApplyMixedTransactions() {
        int balance = 0;
        balance = processor.applyTransaction("deposit", 500, balance);
        balance = processor.applyTransaction("withdrawal", 200, balance);
        balance = processor.applyTransaction("deposit", 100, balance);
        balance = processor.applyTransaction("withdrawal", 50, balance);
        assertEquals(350, balance,
            "500 - 200 + 100 - 50 = 350");
    }

    // ── Integration test for processTransactions ──

    @Test
    @DisplayName("processTransactions: correct final balance from a temp file")
    void testProcessTransactionsIntegration(@TempDir Path tempDir) throws IOException {
        // Write a temp CSV file
        Path transactionsFile = tempDir.resolve("transactions.csv");
        Files.write(transactionsFile, List.of(
            "deposit,100",
            "deposit,200",
            "withdrawal,50",
            "deposit,25",
            "withdrawal,75"
        ));

        // Capture stdout so we can check the final balance line
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            processor.processTransactions(transactionsFile.toString());
        } finally {
            System.setOut(originalOut);
        }

        String output = outContent.toString();
        // 100 + 200 - 50 + 25 - 75 = 200
        assertTrue(output.contains("200"),
            "The final balance line should contain '200'. Full output was:\n" + output);
    }

    @Test
    @DisplayName("processTransactions: empty file results in Final Balance of $0")
    void testProcessTransactionsEmptyFile(@TempDir Path tempDir) throws IOException {
        Path transactionsFile = tempDir.resolve("empty.csv");
        Files.write(transactionsFile, List.of());

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            processor.processTransactions(transactionsFile.toString());
        } finally {
            System.setOut(originalOut);
        }

        String output = outContent.toString();
        assertTrue(output.contains("0"),
            "An empty file should result in a final balance of $0");
    }
}
