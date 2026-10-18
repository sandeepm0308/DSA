@echo off
REM =====================================================================
REM Interactive Visualization of Linked Lists - Compilation & Run Script
REM =====================================================================

echo [1/2] Compiling Java source files...
if not exist "bin" mkdir bin
javac -encoding UTF-8 -d bin src\com\dsa\linkedlist\*.java

if %ERRORLEVEL% NEQ 0 (
    echo [ERROR] Compilation failed!
    pause
    exit /b %ERRORLEVEL%
)

echo [2/2] Compilation successful! Launching Visualizer...
echo ===================================================
java -cp bin com.dsa.linkedlist.Main %*
pause
