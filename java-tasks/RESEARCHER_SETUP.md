# 🛠️ Researcher Setup Guide

This guide is for the **researcher/instructor** setting up the GitHub repository and VS Code environment before the study session.

---

## Part 1 – Create the GitHub Repository

### Option A: Using GitHub.com (easiest)

1. Go to [github.com](https://github.com) and sign in (or create an account)
2. Click the **+** button (top right) → **New repository**
3. Fill in:
   - **Repository name:** `java-tasks` (or your preferred name)
   - **Description:** Java programming tasks for Claude Code study session
   - **Visibility:** Private (recommended for research) or Public
   - ✅ Check **Add a README file**
4. Click **Create repository**

### Option B: From the terminal (if you prefer)

```bash
# Install GitHub CLI first: https://cli.github.com
gh repo create java-tasks --private --clone
cd java-tasks
```

---

## Part 2 – Upload the Task Files

### Using Git in the terminal

```bash
# Clone your new repo (replace with your actual URL)
git clone https://github.com/YOUR-USERNAME/java-tasks.git
cd java-tasks

# Copy all the task files into the repo folder
# (the files you received from this setup package)

# Commit and push everything
git add .
git commit -m "Add all task files, tests, and READMEs"
git push origin main
```

### Download the JUnit 5 JAR

The test runner scripts require a JUnit 5 standalone JAR in the `lib/` folder. Download it once and commit it to the repo:

```bash
# Create the lib folder
mkdir lib
cd lib

# Download JUnit 5 Console Standalone (check https://mvnrepository.com for latest version)
curl -L -o junit-platform-console-standalone.jar \
  https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.10.2/junit-platform-console-standalone-1.10.2.jar

cd ..
git add lib/
git commit -m "Add JUnit 5 standalone JAR"
git push
```

---

## Part 3 – Set Up VS Code for Participants

### Required Extensions

Have participants install these from the VS Code Extensions panel (`Ctrl+Shift+X` / `Cmd+Shift+X`):

1. **Extension Pack for Java** by Microsoft  
   _(includes Language Support for Java, Debugger, Test Runner, Maven)_

2. **Claude Code** by Anthropic  
   _(the AI assistant for the study)_

### Verify Java is working

Ask the participant to open a terminal in VS Code (`Ctrl+`` ` ``) and run:

```bash
java -version
javac -version
```

Both should print a version number (17 or higher ideally). If not, direct them to install [Eclipse Temurin JDK 17](https://adoptium.net/).

---

## Part 4 – Clone the Repo on the Participant's Machine

```bash
git clone https://github.com/YOUR-USERNAME/java-tasks.git
cd java-tasks
code .
```

VS Code will open the project. The Java extension will index it automatically (takes ~30 seconds on first open).

---

## Part 5 – Give Participants Access to the Repo

### If the repo is Private:

1. Go to your repo on GitHub → **Settings** → **Collaborators**
2. Click **Add people** and enter each participant's GitHub username or email
3. They'll receive an email invite — they must accept it before cloning

### If the repo is Public:

No access management needed — just share the URL.

---

## Part 6 – Session Checklist

Before each session, confirm:

- [ ] Participant's laptop has Java 17+ installed (`java -version`)
- [ ] VS Code is installed with the Java extension pack
- [ ] Claude Code extension is installed and the participant is signed in
- [ ] The repo has been cloned successfully (`ls java-tasks/`)
- [ ] Running `./run_tests.sh` from any task folder produces output (even with failing tests — that means the toolchain works)
- [ ] Screen and audio recording is set up per your consent protocol

---

## Directory Structure Reference

```
java-tasks/
├── README.md                          ← Participant instructions
├── RESEARCHER_SETUP.md                ← This file
├── lib/
│   └── junit-platform-console-standalone.jar
├── task1-substring/
│   ├── README.md
│   ├── src/SubstringTasks.java
│   ├── test/SubstringTasksTest.java
│   ├── run_tests.sh
│   └── run_tests.bat
├── task2-syntax-debug/
│   ├── README.md
│   ├── src/ShoppingCart.java
│   ├── test/ShoppingCartTest.java
│   ├── run_tests.sh
│   └── run_tests.bat
├── task3-logic-debug/
│   ├── README.md
│   ├── src/TemperatureConverter.java
│   ├── test/TemperatureConverterTest.java
│   ├── run_tests.sh
│   └── run_tests.bat
└── task4-refactor/
    ├── README.md
    ├── src/TransactionProcessor.java
    ├── test/TransactionProcessorTest.java
    ├── run_tests.sh
    └── run_tests.bat
```

---

## Troubleshooting

**"Permission denied" on run_tests.sh (Mac/Linux)**
```bash
chmod +x task1-substring/run_tests.sh
# Repeat for each task, or:
find . -name "run_tests.sh" -exec chmod +x {} \;
```

**"java: command not found"**  
Install JDK from [adoptium.net](https://adoptium.net/) and restart VS Code.

**Tests won't compile — "package org.junit does not exist"**  
The `lib/` folder is missing the JUnit JAR. Follow Part 2 above.

**VS Code doesn't show the Testing panel**  
Make sure the **Extension Pack for Java** (not just Language Support for Java) is installed.
