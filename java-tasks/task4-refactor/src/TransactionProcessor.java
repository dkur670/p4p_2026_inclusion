import java.io.*;
import java.util.*;

public class TransactionProcessor {

    /**
     * Reads a CSV file of transactions and prints a running log plus the final balance.
     *
     * Each line in the file has the format:  type,amount
     * For example:
     *   deposit,100
     *   withdrawal,40
     *   deposit,200
     *
     * This method currently does everything in one place.
     * Your task: refactor it by extracting smaller helper methods.
     *
     * @param filePath path to the transactions CSV file
     * @throws IOException if the file cannot be read
     */
    public void processTransactions(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        int total = 0;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            int amount = Integer.parseInt(parts[1].trim());
            String type = parts[0].trim();

            if (type.equals("deposit")) {
                total += amount;
            } else if (type.equals("withdrawal")) {
                total -= amount;
            }
            System.out.println("Processed: " + type + " of $" + amount);
        }
        reader.close();
        System.out.println("Final Balance: $" + total);
    }

    // ──────────────────────────────────────────────
    // TODO: Extract helper methods here.
    // Suggested methods to create:
    //
    //   List<String> readLines(String filePath) throws IOException
    //   String[] parseTransaction(String line)
    //   int applyTransaction(String type, int amount, int balance)
    //   void printTransaction(String type, int amount)
    //
    // Then update processTransactions() to call them.
    // ──────────────────────────────────────────────
}
