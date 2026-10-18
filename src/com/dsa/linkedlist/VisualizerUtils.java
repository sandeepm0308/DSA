package com.dsa.linkedlist;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * Utility class providing styled headers, operation status messages,
 * and ASCII formatting aids for the terminal visualizer.
 */
public class VisualizerUtils {

    // ANSI Color Escape Codes
    public static final String RESET = "\u001B[0m";
    public static final String BOLD = "\u001B[1m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String CYAN = "\u001B[36m";
    public static final String MAGENTA = "\u001B[35m";

    private static boolean useColor = true;

    static {
        // Try enabling UTF-8 output stream for modern terminals
        try {
            System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8.name()));
        } catch (Exception ignored) {
        }
    }

    public static void setUseColor(boolean enabled) {
        useColor = enabled;
    }

    public static boolean isUseColor() {
        return useColor;
    }

    public static String colorize(String text, String colorCode) {
        return useColor ? colorCode + text + RESET : text;
    }

    public static void printBanner(String title) {
        int width = 64;
        String line = "=".repeat(width);
        System.out.println();
        System.out.println(colorize(line, CYAN));
        int pad = (width - title.length() - 2) / 2;
        String padding = " ".repeat(Math.max(0, pad));
        System.out.println(colorize("*" + padding + title + padding + (title.length() % 2 != 0 ? " " : "") + "*", BOLD + CYAN));
        System.out.println(colorize(line, CYAN));
        System.out.println();
    }

    public static void printSectionHeader(String section) {
        System.out.println();
        System.out.println(colorize("--- " + section + " ---", YELLOW + BOLD));
    }

    public static void printSuccess(String message) {
        System.out.println(colorize("[SUCCESS] ", GREEN + BOLD) + colorize(message, GREEN));
    }

    public static void printError(String message) {
        System.out.println(colorize("[ERROR] ", RED + BOLD) + colorize(message, RED));
    }

    public static void printInfo(String message) {
        System.out.println(colorize("[INFO] ", BLUE + BOLD) + colorize(message, BLUE));
    }

    public static void printWarning(String message) {
        System.out.println(colorize("[WARN] ", YELLOW + BOLD) + colorize(message, YELLOW));
    }

    public static void printDivider() {
        System.out.println(colorize("-".repeat(64), CYAN));
    }
}
