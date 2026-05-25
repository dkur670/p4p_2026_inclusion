# Task 2 – Debugging: Syntax Errors

## Overview

`ShoppingCart.java` models a simple shopping cart — but it **will not compile** because it contains **three syntax bugs**.

Your job is to find and fix all of them so the code compiles and runs correctly.

You may use **Claude Code** to help identify and fix the issues.

---

## What You Need To Do

Open `ShoppingCart.java` and fix every syntax error.  
Do **not** change the overall logic or structure of the class.

The class has three methods:

| Method | What it does |
|--------|-------------|
| `addItem(String, double)` | Adds an item and increases the total price |
| `removeItem(String, double)` | Removes an item (if it exists) and decreases the total price |
| `printReceipt()` | Prints all items and the final total |

---

## Hints

- **Hint 1:** Try compiling first — the error message will point you to a line number.
- **Hint 2:** Look carefully at the end of each statement — is anything missing?
- **Hint 3:** Check that every opening bracket or parenthesis has a matching closing one.

---

## How To Run The Tests

With any file from this folder open in VS Code, press **Ctrl + Shift + B**.

The terminal will open and run the tests automatically. Alternatively, open a terminal inside this folder and run:

```bash
javac ShoppingCart.java ShoppingCartTest.java
java ShoppingCartTest
```

> ⚠️ If `ShoppingCart.java` still has syntax errors, the `javac` step will fail.  
> Fix the compile errors first, then run the tests again.

When all bugs are fixed you will see:

```
All tests passed! Task 2 complete.
```

---

## Definition of Done

✅ `javac ShoppingCart.java ShoppingCartTest.java` produces **no errors**  
✅ All tests in `ShoppingCartTest.java` pass with `PASS`  
✅ The terminal prints **"All tests passed! Task 2 complete."**
