# Claude Code – Java Tasks

This repository contains four Java programming tasks for use with **Visual Studio Code + Claude Code**.

Each task lives in its own folder and includes:
- A **starter Java file** for you to work in
- A **README** with instructions, hints, and examples
- A **test file** you run in the terminal to check your work

---

## Task Overview

| Folder | Task | Skill focus |
|--------|------|-------------|
| `task1-substring/` | Longest Substring Without Repeating Characters | Code generation / algorithms |
| `task2-syntax-debug/` | Debugging – Syntax Errors | Fixing compile errors |
| `task3-logic-debug/` | Debugging – Logic Errors | Fixing runtime/logical bugs |
| `task4-refactoring/` | Code Refactoring | Clean code / method extraction |

---

## How To Run Tests (all tasks)

Each task follows the same pattern. From a terminal inside the task folder:

```bash
javac *.java
java <TestClassName>
```

For example, for Task 1:

```bash
cd task1-substring
javac SubstringTask.java SubstringTaskTest.java
java SubstringTaskTest
```

A passing run looks like:

```
PASS  lengthOfLongestSubstring("abcabcbb") = 3
PASS  lengthOfLongestSubstring("bbbbb") = 1
...
All tests passed! Task 1 complete.
```

---

## Tips

- Read the `README.md` inside each task folder before you start.
- Use **Claude Code** to help generate, debug, or refactor code — that's the point!
- Think aloud as you work — share what you're noticing and why you're making decisions.
- If you're stuck, check the hints in the README first, then ask Claude Code.

---

## Requirements

- Java 11 or later
- Visual Studio Code with Claude Code enabled
