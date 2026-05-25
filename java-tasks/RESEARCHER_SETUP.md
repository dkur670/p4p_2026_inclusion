# Researcher Setup Guide

This guide is for the **researcher** setting up the repository and VS Code environment before the study session.

---

## Part 1 – Create the GitHub Repository

### Option A: Using GitHub.com (easiest)

1. Go to [github.com](https://github.com) and sign in
2. Click **+** → **New repository**
3. Fill in:
   - **Repository name:** `java-tasks`
   - **Visibility:** Private (recommended) or Public
4. Click **Create repository**

### Option B: From the terminal

```bash
gh repo create java-tasks --private --clone
cd java-tasks
```

---

## Part 2 – Push the Task Files

```bash
git clone https://github.com/YOUR-USERNAME/java-tasks.git
cd java-tasks

# copy all task files into this folder, then:
git add .
git commit -m "Add all task files"
git push origin main
```

> **No external libraries needed.** Tests use plain `javac` + `java` — no JUnit JAR required.

---

## Part 3 – Set Up VS Code for Participants

### Required Extensions

Have participants install from the Extensions panel (`Ctrl+Shift+X`):

1. **Extension Pack for Java** by Microsoft
2. **Claude Code** by Anthropic — signed in before the session

### Verify Java is working

```bash
java -version
javac -version
```

Both should print a version number (Java 11 or later). If not, install [Eclipse Temurin JDK 17](https://adoptium.net/).

---

## Part 4 – Clone the Repo on the Participant's Machine

```bash
git clone https://github.com/YOUR-USERNAME/java-tasks.git
cd java-tasks
code .
```

VS Code will open. The Java extension indexes the project automatically (~30 seconds on first open).

---

## Part 5 – How Participants Run Tests

Participants press **Ctrl + Shift + B** with any task file open. The terminal compiles and runs the tests for whichever task folder the open file belongs to. PASS / FAIL lines appear immediately.

No extra scripts, no JAR files, no configuration needed.

---

## Part 6 – Session Checklist

Before each session, confirm:

- [ ] Java 17+ installed (`java -version`)
- [ ] VS Code with Extension Pack for Java installed
- [ ] Claude Code extension installed and participant is signed in
- [ ] Repo cloned successfully
- [ ] Test smoke-check: open `task1-substring/SubstringTask.java`, press Ctrl+Shift+B → terminal shows FAIL lines (correct — participant hasn't implemented it yet)
- [ ] Screen and audio recording set up per your consent protocol

---

## Directory Structure

```
java-tasks/
├── README.md                          ← Participant overview
├── RESEARCHER_SETUP.md                ← This file
├── .vscode/
│   ├── settings.json                  ← Java extension config
│   └── tasks.json                     ← "Run Tests" build task (Ctrl+Shift+B)
├── task1-substring/
│   ├── README.md
│   ├── SubstringTask.java             ← Participant edits this
│   └── SubstringTaskTest.java         ← Test runner (do not edit)
├── task2-syntax-debug/
│   ├── README.md
│   ├── ShoppingCart.java              ← Participant edits this
│   └── ShoppingCartTest.java          ← Test runner (do not edit)
├── task3-logic-debug/
│   ├── README.md
│   ├── TemperatureConverter.java      ← Participant edits this
│   └── TemperatureConverterTest.java  ← Test runner (do not edit)
└── task4-refactoring/
    ├── README.md
    ├── TransactionProcessor.java      ← Participant edits this
    └── TransactionProcessorTest.java  ← Test runner (do not edit)
```

---

## Troubleshooting

**"java: command not found" / "javac: command not found"**
Install JDK from [adoptium.net](https://adoptium.net/) and restart VS Code.

**Ctrl+Shift+B shows a task picker instead of running immediately**
Click "Run Tests" in the picker. This only happens the first time; after that it runs directly.

**Task 2 compilation fails immediately**
Correct — the file contains intentional syntax bugs. The participant must fix them first.

**Java extension shows errors in the editor on first open**
Wait ~30 seconds for the language server to finish indexing, then the errors will resolve.
