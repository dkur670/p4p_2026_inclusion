# Task 4 – Code Refactoring

## Overview

`TransactionProcessor.java` contains a method `processTransactions()` that does too many things at once: it reads a file, parses each line, runs business logic, and prints output — all in one big block.

Your job is to **refactor it** by extracting smaller, focused helper methods, while keeping the overall behaviour exactly the same.

You may use **Claude Code** to assist.

---

## What You Need To Do

Open `TransactionProcessor.java`. Read the existing `processTransactions()` method carefully, then extract at least **three** helper methods from it.

### Suggested helper methods

You can name these whatever you like — what matters is that the responsibilities are separated:

| Suggested name | Responsibility |
|----------------|---------------|
| `readLines(filePath)` | Read all lines from the file and return them as a list |
| `parseTransaction(line)` | Split a CSV line and return the type and amount |
| `applyTransaction(balance, type, amount)` | Apply a deposit or withdrawal to the balance |
| `printTransaction(type, amount)` | Print the "Processed: ..." line |

After refactoring, `processTransactions()` should just **coordinate** these helpers — it should be short and easy to read.

---

## What a transaction file looks like

Each line is: `type,amount` where type is `deposit` or `withdrawal`:

```
deposit,100
withdrawal,30
deposit,50
```

Expected output for the above:
```
Processed: deposit of $100
Processed: withdrawal of $30
Processed: deposit of $50
Final Balance: $120
```

---

## Hints

- **Hint 1:** Identify the separate responsibilities — reading, parsing, calculating, printing. Each becomes its own method.
- **Hint 2:** You can ask Claude Code to extract a method for you — highlight the code you want to move and ask it to create a helper.
- **Hint 3:** After refactoring, run the tests — they check both that the output is still correct *and* that you've actually added helper methods.

---

## How To Run The Tests

With any file from this folder open in VS Code, press **Ctrl + Shift + B**.

The terminal will open and run the tests automatically. Alternatively, open a terminal inside this folder and run:

```bash
javac TransactionProcessor.java TransactionProcessorTest.java
java TransactionProcessorTest
```

When your refactoring is complete you will see:

```
All tests passed! Task 4 complete.
```

---

## Definition of Done

✅ `processTransactions()` calls helper methods instead of doing everything itself  
✅ At least **3** new helper methods exist in the class  
✅ All tests in `TransactionProcessorTest.java` pass with `PASS`  
✅ The terminal prints **"All tests passed! Task 4 complete."**
