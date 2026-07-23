# Claude Code – Java Tasks

This repository contains three Java programming tasks for use with **Visual Studio Code + Claude Code**.

Each task lives in its own folder and includes:
- A **starter Java file** for you to work in
- A **README** explaining the task
- A **test file** that runs automatically to check your work

Each task is expected to take roughly **40–45 minutes** working with Claude Code.

---

## Task Overview

| Folder | Task | Skill focus |
|--------|------|-------------|
| `task1-codegen/` | Expression Evaluator | Code generation |
| `task2-debugging/` | Library System | Debugging (logic errors) |
| `task3-refactoring/` | Transaction Processor | Refactoring |

---

## How To Run Tests

### Option 1 — Keyboard shortcut (quickest)

With any file from a task folder open in VS Code, press:

```
Ctrl + Shift + B
```

The terminal will open and show PASS / FAIL for every test.

### Option 2 — Terminal commands

Open the VS Code terminal (`Ctrl + `` ` ``), navigate into the task folder, then:

```bash
# Task 1
cd task1-codegen
javac ExpressionEvaluator.java ExpressionEvaluatorTest.java
java ExpressionEvaluatorTest

# Task 2
cd task2-debugging
javac LibrarySystem.java LibrarySystemTest.java
java LibrarySystemTest

# Task 3
cd task3-refactoring
javac TransactionProcessor.java TransactionProcessorTest.java
java TransactionProcessorTest
```

### What passing output looks like

```
PASS  evaluate("2 + 3 * 4") = 14.0
PASS  evaluate("(2 + 3) * 4") = 20.0
...
----------------------------------------
Results: 33 passed, 0 failed out of 33 tests.
All tests passed! Task 1 complete.
```

---

## Tips

- Read the `README.md` inside each task folder **before you start** — it's written to be pasted directly into Claude Code.
- Use **Claude Code** to help generate, debug, or refactor code — that's the point!
- Think aloud as you work — share what you're noticing and why you're making decisions.

---

## Requirements

- Java 11 or later (`java -version` to check)
- Visual Studio Code with the **Extension Pack for Java** installed
- **Claude Code** extension installed and signed in
