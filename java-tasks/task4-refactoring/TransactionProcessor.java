import java.io.*;
import java.util.*;

/**
 * Task 4 – Code Refactoring
 *
 * The method processTransactions() below does too much at once:
 * it reads a file, parses each line, applies business logic,
 * and prints output — all in one big block.
 *
 * Your job is to refactor it by extracting smaller, focused helper
 * methods, making the code easier to read, test, and maintain.
 *
 * You may use Claude Code to assist.
 */
public class TransactionProcessor {

    
    // Original method — refactor this


    /**
     * Reads a CSV file of transactions, calculates a running balance,
     * and prints a summary.
     *
     * Each line in the file has the format:  type,amount
     * where type is "deposit" or "withdrawal" and amount is an integer.
     *
     * Example file contents:
     *   deposit,100
     *   withdrawal,30
     *   deposit,50
     *
     * @param filePath path to the transactions CSV file
     */
    public void processTransactions(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        int total = 0;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            int amount = Integer.parseInt(parts[1]);
            String type = parts[0];

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

    
    // TODO: Add your extracted helper methods below this line
   

    // Suggested helpers (you can name them differently):
    //
    //   List<String> readLines(String filePath) throws IOException
    //       → reads all lines from the file and returns them as a list
    //
    //   String[] parseTransaction(String line)
    //       → splits a CSV line and returns a String array [type, amount]
    //
    //   int applyTransaction(int balance, String type, int amount)
    //       → applies a deposit or withdrawal to the balance and returns the new balance
    //
    //   void printTransaction(String type, int amount)
    //       → prints the "Processed: ..." line for a single transaction


    
    // Do not modify main()
    public static void main(String[] args) throws IOException {
        // Creates a small test file and runs the processor
        File temp = File.createTempFile("transactions", ".csv");
        temp.deleteOnExit();
        try (PrintWriter pw = new PrintWriter(temp)) {
            pw.println("deposit,100");
            pw.println("withdrawal,30");
            pw.println("deposit,50");
        }

        TransactionProcessor processor = new TransactionProcessor();
        processor.processTransactions(temp.getAbsolutePath());
    }
}
