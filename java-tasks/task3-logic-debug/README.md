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
| `celsiusToFahrenheit(double)` | Converts Celsius → Fahrenheit 
| `fahrenheitToCelsius(double)` | Converts Fahrenheit → Celsius 
| `classifyTemperature(double)` | Returns a category label for a Celsius value 

### Expected classification

| Celsius range | Category   |
|---------------|------------|
| Below 0       | `Freezing` |
| 0 – 14        | `Cold`     |
| 15 – 24       | `Warm`     |
| 25 and above  | `Hot`      |

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
