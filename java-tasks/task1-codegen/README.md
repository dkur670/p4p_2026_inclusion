# Task 1 – Code Generation: Expression Evaluator

## Overview

In this task you will implement a method that evaluates a mathematical expression given as a string and returns its numeric result.

You may use **Claude Code** to help you think through, generate, or complete the solution.

---

## What You Need To Do

Open `ExpressionEvaluator.java` and implement the `evaluate` method.

The method receives a `String expression` and must return a `double` — the result of evaluating that expression.

The evaluator must support:
- Integers and decimals
- The operators `+`, `-`, `*`, `/`
- Unary minus (e.g. `-5`)
- Parentheses
- Standard operator precedence and left-to-right associativity
- Whitespace anywhere in the expression (it should be ignored)

It must also handle invalid input correctly:
- Dividing by zero should throw an `ArithmeticException`
- An expression that isn't valid (empty input, mismatched parentheses, invalid characters, a missing operand, etc.) should throw an `IllegalArgumentException`

### Examples

| Input                 | Output | Explanation                          |
|------------------------|--------|---------------------------------------|
| `"2 + 3 * 4"`           | `14.0` | Multiplication before addition        |
| `"(2 + 3) * 4"`         | `20.0` | Parentheses evaluated first           |
| `"10 / 2 - 3"`          | `2.0`  | Left-to-right after precedence        |
| `"-5 + 3"`              | `-2.0` | Unary minus                           |
| `"2 * (3 + (4 - 1))"`   | `12.0` | Nested parentheses                    |

---

## How To Run The Tests

With any file from this folder open in VS Code, press **Ctrl + Shift + B**.

The terminal will open and run the tests automatically. Alternatively, open a terminal inside this folder and run:

```bash
javac ExpressionEvaluator.java ExpressionEvaluatorTest.java
java ExpressionEvaluatorTest
```

All lines should print `PASS`. The final line will confirm:

```
All tests passed! Task 1 complete.
```

If any tests fail, the output will tell you what input caused the issue and what was expected.

---

## Definition of Done

✅ All tests in `ExpressionEvaluatorTest.java` pass with `PASS`
✅ The terminal prints **"All tests passed! Task 1 complete."**
