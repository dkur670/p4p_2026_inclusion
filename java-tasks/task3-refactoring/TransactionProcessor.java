import java.io.*;
import java.util.*;

/**
 * Task 3 - Code Refactoring
 *
 * processTransactions() below reads a transaction file, validates each
 * line, applies deposits/withdrawals/fees/interest to a running balance,
 * tracks per-category totals, and prints a final summary — all in one
 * long method with duplicated logic.
 *
 * Your job is to refactor it by extracting smaller, focused helper
 * methods, while keeping its behaviour exactly the same: given the same
 * input file, the output must be identical before and after.
 *
 * You may use Claude Code to assist.
 */
public class TransactionProcessor {

    // Original method — refactor this
    public void processTransactions(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        double balance = 0.0;
        double totalDeposits = 0.0;
        double totalWithdrawals = 0.0;
        double totalFees = 0.0;
        double totalInterest = 0.0;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length != 2) {
                System.out.println("Skipped invalid transaction: " + line);
                continue;
            }
            String type = parts[0].trim();
            double amount;
            try {
                amount = Double.parseDouble(parts[1].trim());
            } catch (NumberFormatException e) {
                System.out.println("Skipped invalid transaction: " + line);
                continue;
            }
            if (amount <= 0) {
                System.out.println("Skipped invalid transaction: " + line);
                continue;
            }

            if (type.equals("deposit")) {
                balance += amount;
                totalDeposits += amount;
                System.out.printf("Processed: deposit of $%.2f%n", amount);
            } else if (type.equals("withdrawal")) {
                balance -= amount;
                totalWithdrawals += amount;
                System.out.printf("Processed: withdrawal of $%.2f%n", amount);
            } else if (type.equals("fee")) {
                balance -= amount;
                totalFees += amount;
                System.out.printf("Processed: fee of $%.2f%n", amount);
            } else if (type.equals("interest")) {
                balance += amount;
                totalInterest += amount;
                System.out.printf("Processed: interest of $%.2f%n", amount);
            } else {
                System.out.println("Skipped invalid transaction: " + line);
                continue;
            }
        }
        reader.close();

        System.out.println("----------------------------------------");
        System.out.println("Summary:");
        System.out.printf("  Deposits:    $%.2f%n", totalDeposits);
        System.out.printf("  Withdrawals: $%.2f%n", totalWithdrawals);
        System.out.printf("  Fees:        $%.2f%n", totalFees);
        System.out.printf("  Interest:    $%.2f%n", totalInterest);
        System.out.printf("Final Balance: $%.2f%n", balance);
    }

    // TODO: Add your extracted helper methods below this line

    // Do not modify main()
    public static void main(String[] args) throws IOException {
        // Creates a small test file and runs the processor
        File temp = File.createTempFile("transactions", ".csv");
        temp.deleteOnExit();
        try (PrintWriter pw = new PrintWriter(temp)) {
            pw.println("deposit,1000");
            pw.println("withdrawal,250");
            pw.println("fee,5.50");
            pw.println("interest,12.75");
            pw.println("nonsense,line");
            pw.println("withdrawal,-40");
        }

        TransactionProcessor processor = new TransactionProcessor();
        processor.processTransactions(temp.getAbsolutePath());
    }
}
