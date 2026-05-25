# Task 1 – Longest Substring Without Repeating Characters

## Overview

In this task you will implement a method that finds the **length of the longest substring** in a string that contains **no repeating characters**.

You may use **Claude Code** to help you think through, generate, or complete the solution.

---

## What You Need To Do

Open `SubstringTask.java` and implement the `lengthOfLongestSubstring` method.

The method receives a `String s` and must return an `int` — the length of the longest substring where every character appears only once.

### Examples

| Input        | Output | Explanation                         |
|--------------|--------|-------------------------------------|
| `"abcabcbb"` | `3`    | Longest unique substring is `"abc"` |
| `"bbbbb"`    | `1`    | Only `"b"` has no repeats           |
| `"pwwkew"`   | `3`    | Longest unique substring is `"wke"` |
| `""`         | `0`    | Empty string has no characters      |

---

## Hints

- **Hint 1:** Think about how you might track which characters you've already seen in your current substring.
- **Hint 2:** Consider using two pointers — one marking the start of your current window, one scanning forward. This is called the *sliding window* technique.
- **Hint 3:** A `HashSet` is useful for quickly checking whether a character already exists in your window.

---

## How To Run The Tests

With any file from this folder open in VS Code, press **Ctrl + Shift + B**.

The terminal will open and run the tests automatically. Alternatively, open a terminal inside this folder and run:

```bash
javac SubstringTask.java SubstringTaskTest.java
java SubstringTaskTest
```

All lines should print `PASS`. The final line will confirm:

```
All tests passed! Task 1 complete.
```

If any tests fail, the output will tell you what input caused the issue and what was expected.

---

## Definition of Done

✅ All tests in `SubstringTaskTest.java` pass with `PASS`  
✅ The terminal prints **"All tests passed! Task 1 complete."**
