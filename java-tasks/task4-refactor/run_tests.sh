#!/bin/bash
# Run JUnit 5 tests for this task
TASK_DIR="$(cd "$(dirname "$0")" && pwd)"
REPO_ROOT="$(cd "$TASK_DIR/.." && pwd)"
LIB="$REPO_ROOT/lib"
SRC="$TASK_DIR/src"
TEST="$TASK_DIR/test"
OUT="$TASK_DIR/out"

mkdir -p "$OUT"

echo "=== Compiling source and tests... ==="
javac -cp "$LIB/*" -d "$OUT" "$SRC"/*.java "$TEST"/*.java

if [ $? -ne 0 ]; then
  echo ""
  echo "❌ Compilation failed. Fix the errors above and try again."
  exit 1
fi

echo ""
echo "=== Running tests... ==="
java -cp "$OUT:$LIB/*" org.junit.platform.console.standalone.ConsoleLauncher \
  --scan-class-path \
  --classpath "$OUT" \
  --disable-banner

