#!/bin/bash
# =====================================================================
# Interactive Visualization of Linked Lists - Compilation & Run Script
# =====================================================================

set -e
mkdir -p bin
echo "[1/2] Compiling Java source files..."
javac -encoding UTF-8 -d bin src/com/dsa/linkedlist/*.java

echo "[2/2] Compilation successful! Launching Visualizer..."
echo "==================================================="
java -cp bin com.dsa.linkedlist.Main "$@"
