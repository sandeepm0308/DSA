@echo off
REM =====================================================================
REM Share Linked List Web Visualizer Publicly to Anyone on the Internet
REM =====================================================================

echo [1/2] Starting local web server in background...
start /b python -m http.server 8080 --directory web

echo [2/2] Generating secure public HTTPS tunnel link...
echo =================================================================
echo Look below for your public HTTPS link (e.g., https://....lhr.life)
echo Anyone on any phone, tablet, or PC can open and use this link!
echo Press Ctrl+C anytime to stop sharing.
echo =================================================================
ssh -o StrictHostKeyChecking=no -R 80:localhost:8080 nokey@localhost.run
pause
