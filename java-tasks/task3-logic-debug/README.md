# Task 3 – Debugging: Logic Errors

## Overview

`TemperatureConverter.java` compiles and runs without errors — but **produces wrong results** for certain inputs. There are **two logic bugs** hidden in two of the methods.

Your job is to find and fix both bugs so the outputs match the expected values.

You may use **Claude Code** to help identify where the logic goes wrong.

---

## What You Need To Do

Open `TemperatureConverter.java`. The class has three methods:

| Method | What it does |
|--------|-------------|
| `celsiusToFahrenheit(double)` | Converts Celsius → Fahrenheit *(correct — don't change this)* |
| `fahrenheitToCelsius(double)` | Converts Fahrenheit → Celsius — **Bug 1 is here** |
| `classifyTemperature(double)` | Returns a category label for a Celsius value — **Bug 2 is here** |

### Expected classification

| Celsius range | Category   |
|---------------|------------|
| Below 0       | `Freezing` |
| 0 – 14        | `Cold`     |
| 15 – 24       | `Warm`     |
| 25 and above  | `Hot`      |

---

## Hints

- **Hint 1:** Run `main()` first — note which outputs look wrong compared to the comments.
- **Hint 2:** For `fahrenheitToCelsius()`, work through the formula by hand with a value you know, like 212°F. What should the answer be?
- **Hint 3:** For `classifyTemperature()`, trace through the `if` conditions one at a time with a value like 30 — which branch does it enter first, and is that correct?

---

## How To Run The Tests

With any file from this folder open in VS Code, press **Ctrl + Shift + B**.

The terminal will open and run the tests automatically. Alternatively, open a terminal inside this folder and run:

```bash
javac TemperatureConverter.java TemperatureConverterTest.java
java TemperatureConverterTest
```

When both bugs are fixed you will see:

```
All tests passed! Task 3 complete.
```

---

## Definition of Done

✅ All tests in `TemperatureConverterTest.java` pass with `PASS`  
✅ The terminal prints **"All tests passed! Task 3 complete."**
