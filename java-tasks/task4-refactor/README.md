# Task 4 – Refactor: Break Up a Large Method

## 🎯 Goal

Refactor the method `processTransactions()` in `TransactionProcessor.java` so that it is **easier to read, test, and maintain** by breaking it into smaller, focused helper methods.

---

## 📖 Background

**Refactoring** means changing the structure of code without changing what it does. The end result should work exactly the same — but be cleaner and more organised.

The current `processTransactions()` method does too many things at once:
- Opens and reads a file
- Parses each line of text
- Applies deposit/withdrawal logic
- Prints output to the console

Good code separates these responsibilities. Each method should have **one clear job**.

---

## 📂 File to Edit

```
task4-refactor/src/TransactionProcessor.java
```

---

## 📋 Current Code

The current method:

```java
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
    System.out.println("Final Balance: $" + total);
}
```

---

## ✅ What to Extract

Refactor this by creating at least these helper methods:

| Method to Extract | Responsibility |
|---|---|
| `readLines(String filePath)` | Open the file and return all lines as a `List<String>` |
| `parseTransaction(String line)` | Parse one CSV line, return a `String[]` with `[type, amount]` |
| `applyTransaction(String type, int amount, int balance)` | Apply the logic and return the updated balance |
| `printTransaction(String type, int amount)` | Print the "Processed: ..." message |

You may name these differently — the key thing is that `processTransactions()` becomes short and delegates to helpers.

---

## 🧪 The Tests Verify

- Parsing a line like `"deposit,100"` returns the correct type and amount
- `applyTransaction` correctly adds deposits and subtracts withdrawals
- `applyTransaction` ignores unknown transaction types
- The overall `processTransactions` produces the correct final balance

---

## ✅ Running the Tests

```bash
cd task4-refactor
./run_tests.sh      # Mac/Linux
run_tests.bat       # Windows
```

---

## 💡 Hints (try on your own first!)

<details>
<summary>Hint 1</summary>
Can you identify the separate responsibilities in the code? Try underlining or highlighting each different "type of work" the method does.
</details>

<details>
<summary>Hint 2</summary>
Start with the simplest extraction — the print statement. Pull it into its own `printTransaction()` method and call it from the original method.
</details>

<details>
<summary>Hint 3</summary>
Ask Claude Code to help you extract a method for parsing a single line. Describe what input it should take and what it should return.
</details>

---

## 🤖 Using Claude Code

You can ask Claude Code to:
- Explain what "extracting a method" means with an example
- Help you split a specific block of code into a new method
- Review your refactored code and suggest further improvements
