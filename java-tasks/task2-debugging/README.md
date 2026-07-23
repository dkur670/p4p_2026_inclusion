# Task 2 – Debugging: Logic Errors

## Overview

`LibrarySystem.java` compiles and runs without errors — but **produces wrong results** for certain inputs. There are **five logic bugs**, one hidden in each of the five methods.

Your job is to find and fix all five so every method behaves exactly as its documentation comment describes.

You may use **Claude Code** to help identify where the logic goes wrong.

---

## What You Need To Do

Open `LibrarySystem.java`. Each method has a comment describing exactly what it is supposed to do — that comment is the specification. The class has five methods:

| Method | What it does |
|--------|---------------|
| `calculateLateFee(int)` | Calculates the late fee for a returned book |
| `isEligibleToBorrow(int, double)` | Decides if a member can borrow another book |
| `calculateAverageRating(List<Integer>)` | Averages a book's ratings |
| `findMostOverdueBook(List<String>, List<Integer>)` | Finds the most overdue book in a list |
| `countBooksByAuthor(List<String>, String)` | Counts books written by a given author |

Do not change the method signatures, and do not change `main()`.

---

## How To Run The Tests

With any file from this folder open in VS Code, press **Ctrl + Shift + B**.

The terminal will open and run the tests automatically. Alternatively, open a terminal inside this folder and run:

```bash
javac LibrarySystem.java LibrarySystemTest.java
java LibrarySystemTest
```

When all five bugs are fixed you will see:

```
All tests passed! Task 2 complete.
```

---

## Definition of Done

✅ All tests in `LibrarySystemTest.java` pass with `PASS`
✅ The terminal prints **"All tests passed! Task 2 complete."**
