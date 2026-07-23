# Task 3 – Code Refactoring

## Overview

`TransactionProcessor.java` contains a method `processTransactions()` that does too many things at once: it reads a file, validates and parses each line, applies business logic for several transaction types, tracks running totals, and prints output — all in one long block with duplicated logic.

Your job is to **refactor it** by extracting smaller, focused helper methods, while keeping the overall behaviour exactly the same: given the same input file, the output before and after your refactor must be identical, character for character.

You may use **Claude Code** to assist.

---

## What You Need To Do

Open `TransactionProcessor.java`. Read the existing `processTransactions()` method carefully, then break it apart into well-named helper methods so that `processTransactions()` itself just **coordinates** them and is short and easy to read.

Do not change what the method does or its signature — only how the code is organized.

---

## What a transaction file looks like

Each line is `type,amount`, where type is one of `deposit`, `withdrawal`, `fee`, or `interest`:

```
deposit,1000
withdrawal,250
fee,5.50
interest,12.75
```

A line is invalid — and should be skipped with a `Skipped invalid transaction: <line>` message — if it isn't in `type,amount` form, the amount isn't a valid number, the amount isn't positive, or the type isn't recognized.

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
All tests passed! Task 3 complete.
```

---

## Definition of Done

✅ `processTransactions()` calls helper methods instead of doing everything itself
✅ At least **5** new helper methods exist in the class
✅ All tests in `TransactionProcessorTest.java` pass with `PASS`, including the tests that check the output hasn't changed
✅ The terminal prints **"All tests passed! Task 3 complete."**
