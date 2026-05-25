@echo off
REM Run JUnit 5 tests for this task (Windows)
set TASK_DIR=%~dp0
set REPO_ROOT=%TASK_DIR%..
set LIB=%REPO_ROOT%\lib
set SRC=%TASK_DIR%src
set TEST=%TASK_DIR%test
set OUT=%TASK_DIR%out

if not exist "%OUT%" mkdir "%OUT%"

echo === Compiling source and tests... ===
javac -cp "%LIB%\*" -d "%OUT%" "%SRC%\*.java" "%TEST%\*.java"

if %ERRORLEVEL% neq 0 (
    echo.
    echo Compilation failed. Fix the errors above and try again.
    exit /b 1
)

echo.
echo === Running tests... ===
java -cp "%OUT%;%LIB%\*" org.junit.platform.console.standalone.ConsoleLauncher ^
  --scan-class-path ^
  --classpath "%OUT%" ^
  --disable-banner

