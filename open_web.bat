@echo off
setlocal EnableDelayedExpansion
title Linked List Web Visualizer (Local & Public Sharing)

echo =====================================================================
echo       Linked List Visualizer - Web Server ^& Public Sharing
echo =====================================================================
echo  Permanent Vercel Live Link:
echo  https://web-rho-umber-pqjmwme1zj.vercel.app
echo =====================================================================
echo.

REM Verify Python installation
where python >nul 2>&1
if %errorlevel% neq 0 (
    echo [ERROR] Python is not found on your system PATH.
    echo Opening permanent Vercel deployment instead...
    start "" "https://web-rho-umber-pqjmwme1zj.vercel.app"
    pause
    exit /b 1
)

REM Check if local web server is already running on port 8080
netstat -ano | findstr :8080 | findstr LISTENING >nul
if %errorlevel% neq 0 (
    echo [1/3] Starting local web server on port 8080...
    start /b python -m http.server 8080 --directory web >nul 2>&1
    timeout /t 2 /nobreak >nul
) else (
    echo [1/3] Local web server is already active on port 8080.
)

echo.
echo Select Launch Mode:
echo   [1] Local Browser Only (http://localhost:8080)
echo   [2] Permanent Cloud Link (https://web-rho-umber-pqjmwme1zj.vercel.app)
echo   [3] Live Local Browser + Temporary Public Tunnel
echo.
choice /c 123 /t 5 /d 3 /m "Select option (defaults to 3 in 5s): "
set USER_CHOICE=%errorlevel%

if "%USER_CHOICE%"=="1" (
    echo.
    echo Opening local visualizer in default browser...
    start "" "http://localhost:8080"
    echo.
    echo Visualizer opened at http://localhost:8080
    pause
    exit /b 0
)

if "%USER_CHOICE%"=="2" (
    echo.
    echo Opening permanent Vercel live app in default browser...
    start "" "https://web-rho-umber-pqjmwme1zj.vercel.app"
    pause
    exit /b 0
)

if "%USER_CHOICE%"=="3" (
    echo.
    echo [2/3] Opening local visualizer in default browser...
    start "" "http://localhost:8080"
)

echo.
echo =====================================================================
echo  PUBLIC ACCESSIBLE LINK FOR EVERYONE:
echo =====================================================================
echo  - Cloud URL (24/7 Permanent): https://web-rho-umber-pqjmwme1zj.vercel.app
echo  - Temporary Tunnel URL: Look below for your https://...lhr.life link
echo  Keep this window open while sharing via tunnel (Press Ctrl+C to stop).
echo =====================================================================
echo.
ssh -o StrictHostKeyChecking=no -R 80:localhost:8080 nokey@localhost.run

pause
