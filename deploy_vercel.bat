@echo off
setlocal
title Deploy Linked List Visualizer to Vercel

echo =====================================================================
echo          Deploy Linked List Visualizer to Vercel (Production)
echo =====================================================================
echo.
echo Deploying static web visualizer from 'web/' directory...
echo.

npx vercel deploy web --prod

echo.
echo =====================================================================
echo Deployment completed! Copy your production .vercel.app link above.
echo =====================================================================
pause
