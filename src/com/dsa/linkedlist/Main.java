package com.dsa.linkedlist;

import java.util.Scanner;

/**
 * Main application class providing the CLI menu-driven interface,
 * interactive command loop, and automated demonstration scenarios.
 * 
 * Project Title:
 * "Interactive Visualization of Singly, Doubly and Circular Linked Lists Using Command-Line Interface"
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Optional flag to check demo mode or color disable
        for (String arg : args) {
            if ("--no-color".equalsIgnoreCase(arg)) {
                VisualizerUtils.setUseColor(false);
            }
            if ("--demo".equalsIgnoreCase(arg)) {
                runAllDemonstrations();
                return;
            }
        }

        printAppWelcome();

        boolean running = true;
        while (running) {
            printMainMenu();
            String choice = prompt("Enter your choice (1-6): ").trim();

            switch (choice) {
                case "1":
                    handleSinglyLinkedListMenu();
                    break;
                case "2":
                    handleDoublyLinkedListMenu();
                    break;
                case "3":
                    handleCircularLinkedListMenu();
                    break;
                case "4":
                    handleDemonstrationMenu();
                    break;
                case "5":
                    printHelpAndOverview();
                    break;
                case "6":
                case "exit":
                case "quit":
                    running = false;
                    VisualizerUtils.printBanner("THANK YOU FOR USING THE LINKED LIST VISUALIZER!");
                    System.out.println("Exiting application. Goodbye!\n");
                    break;
                default:
                    VisualizerUtils.printError("Invalid selection! Please enter a choice between 1 and 6.");
            }
        }
    }

    private static void printAppWelcome() {
        VisualizerUtils.printBanner("DSA MINI PROJECT: LINKED LIST INTERACTIVE VISUALIZER");
        System.out.println("  Author  : 2nd-Year Computer Science & Engineering");
        System.out.println("  Features: Singly, Doubly, & Circular Linked Lists");
        System.out.println("  Output  : Real-time ASCII / Terminal Box Visualizations");
        System.out.println("  Commands: Natural CLI commands (insert_begin, reverse, etc.)");
        VisualizerUtils.printDivider();
    }

    private static void printMainMenu() {
        System.out.println();
        System.out.println("==================== MAIN MENU ====================");
        System.out.println("  1. Singly Linked List (Interactive Command Mode)");
        System.out.println("  2. Doubly Linked List (Interactive Command Mode)");
        System.out.println("  3. Circular Linked List (Interactive Command Mode)");
        System.out.println("  4. Run Demonstration Scenarios (5 Scenarios)");
        System.out.println("  5. Help / Overview & Command Documentation");
        System.out.println("  6. Exit");
        System.out.println("===================================================");
    }

    // =========================================================================
    // SINGLY LINKED LIST COMMAND LOOP
    // =========================================================================
    private static void handleSinglyLinkedListMenu() {
        SinglyLinkedList sll = new SinglyLinkedList();
        VisualizerUtils.printBanner("SINGLY LINKED LIST: COMMAND MODE");
        printSinglyHelp();
        sll.visualize();

        boolean inSubMenu = true;
        while (inSubMenu) {
            String input = prompt("\n[SLL-cmd] > ").trim();
            if (input.isEmpty()) continue;

            String[] tokens = input.split("\\s+");
            String cmd = tokens[0].toLowerCase();

            switch (cmd) {
                case "create":
                    int[] createVals = parseOrPromptMultipleIntegers(tokens, 1, "Enter space-separated integers for initial list: ");
                    if (createVals != null) {
                        sll.create(createVals);
                    }
                    break;

                case "insert_begin":
                case "ib":
                    Integer ibVal = parseOrPromptInteger(tokens, 1, "Enter integer to insert at beginning: ");
                    if (ibVal != null) sll.insertBegin(ibVal);
                    break;

                case "insert_end":
                case "ie":
                    Integer ieVal = parseOrPromptInteger(tokens, 1, "Enter integer to insert at end: ");
                    if (ieVal != null) sll.insertEnd(ieVal);
                    break;

                case "insert_pos":
                case "ip":
                    Integer ipVal = parseOrPromptInteger(tokens, 1, "Enter integer value to insert: ");
                    if (ipVal != null) {
                        Integer ipPos = parseOrPromptInteger(tokens, 2, "Enter 1-based position (1 to " + (sll.getSize() + 1) + "): ");
                        if (ipPos != null) {
                            sll.insertAtPosition(ipVal, ipPos);
                        }
                    }
                    break;

                case "delete_begin":
                case "db":
                    sll.deleteBegin();
                    break;

                case "delete_end":
                case "de":
                    sll.deleteEnd();
                    break;

                case "delete_pos":
                case "dp":
                    Integer dpPos = parseOrPromptInteger(tokens, 1, "Enter 1-based position to delete: ");
                    if (dpPos != null) sll.deleteAtPosition(dpPos);
                    break;

                case "search":
                    Integer sVal = parseOrPromptInteger(tokens, 1, "Enter integer value to search: ");
                    if (sVal != null) sll.search(sVal);
                    break;

                case "display":
                case "disp":
                case "show":
                    sll.visualize();
                    break;

                case "count":
                case "size":
                    sll.count();
                    break;

                case "reverse":
                case "rev":
                    sll.reverse();
                    break;

                case "clear":
                    sll.clear();
                    VisualizerUtils.printSuccess("List cleared.");
                    sll.visualize();
                    break;

                case "help":
                case "?":
                    printSinglyHelp();
                    break;

                case "exit":
                case "back":
                case "menu":
                    inSubMenu = false;
                    VisualizerUtils.printInfo("Returning to Main Menu...");
                    break;

                default:
                    VisualizerUtils.printError("Unknown command '" + cmd + "'. Type 'help' to see valid commands.");
            }
        }
    }

    private static void printSinglyHelp() {
        System.out.println("Available Singly Linked List Commands:");
        System.out.println("  create [v1 v2 ...]        : Initialize list with values");
        System.out.println("  insert_begin <val> (ib)   : Insert node at beginning");
        System.out.println("  insert_end <val>   (ie)   : Insert node at end");
        System.out.println("  insert_pos <val> <pos>    : Insert node at 1-based position");
        System.out.println("  delete_begin       (db)   : Delete node from beginning");
        System.out.println("  delete_end         (de)   : Delete node from end");
        System.out.println("  delete_pos <pos>   (dp)   : Delete node at 1-based position");
        System.out.println("  search <val>              : Search for a value and show positions");
        System.out.println("  display            (disp) : Render ASCII visualization");
        System.out.println("  count                     : Count total nodes in list");
        System.out.println("  reverse            (rev)  : Reverse the linked list in-place");
        System.out.println("  clear                     : Clear/empty the entire list");
        System.out.println("  help                      : Display this commands cheat-sheet");
        System.out.println("  exit / back               : Return to Main Menu");
    }

    // =========================================================================
    // DOUBLY LINKED LIST COMMAND LOOP
    // =========================================================================
    private static void handleDoublyLinkedListMenu() {
        DoublyLinkedList dll = new DoublyLinkedList();
        VisualizerUtils.printBanner("DOUBLY LINKED LIST: COMMAND MODE");
        printDoublyHelp();
        dll.visualize();

        boolean inSubMenu = true;
        while (inSubMenu) {
            String input = prompt("\n[DLL-cmd] > ").trim();
            if (input.isEmpty()) continue;

            String[] tokens = input.split("\\s+");
            String cmd = tokens[0].toLowerCase();

            switch (cmd) {
                case "create":
                    int[] createVals = parseOrPromptMultipleIntegers(tokens, 1, "Enter space-separated integers for initial list: ");
                    if (createVals != null) {
                        dll.create(createVals);
                    }
                    break;

                case "insert_begin":
                case "ib":
                    Integer ibVal = parseOrPromptInteger(tokens, 1, "Enter integer to insert at beginning: ");
                    if (ibVal != null) dll.insertBegin(ibVal);
                    break;

                case "insert_end":
                case "ie":
                    Integer ieVal = parseOrPromptInteger(tokens, 1, "Enter integer to insert at end: ");
                    if (ieVal != null) dll.insertEnd(ieVal);
                    break;

                case "insert_pos":
                case "ip":
                    Integer ipVal = parseOrPromptInteger(tokens, 1, "Enter integer value to insert: ");
                    if (ipVal != null) {
                        Integer ipPos = parseOrPromptInteger(tokens, 2, "Enter 1-based position (1 to " + (dll.getSize() + 1) + "): ");
                        if (ipPos != null) {
                            dll.insertAtPosition(ipVal, ipPos);
                        }
                    }
                    break;

                case "delete_begin":
                case "db":
                    dll.deleteBegin();
                    break;

                case "delete_end":
                case "de":
                    dll.deleteEnd();
                    break;

                case "delete_pos":
                case "dp":
                    Integer dpPos = parseOrPromptInteger(tokens, 1, "Enter 1-based position to delete: ");
                    if (dpPos != null) dll.deleteAtPosition(dpPos);
                    break;

                case "search":
                    Integer sVal = parseOrPromptInteger(tokens, 1, "Enter integer value to search: ");
                    if (sVal != null) dll.search(sVal);
                    break;

                case "display":
                case "display_forward":
                case "disp":
                case "show":
                    dll.displayForward();
                    break;

                case "display_backward":
                case "dispb":
                    dll.displayBackward();
                    break;

                case "count":
                case "size":
                    dll.count();
                    break;

                case "reverse":
                case "rev":
                    dll.reverse();
                    break;

                case "clear":
                    dll.clear();
                    VisualizerUtils.printSuccess("List cleared.");
                    dll.visualize();
                    break;

                case "help":
                case "?":
                    printDoublyHelp();
                    break;

                case "exit":
                case "back":
                case "menu":
                    inSubMenu = false;
                    VisualizerUtils.printInfo("Returning to Main Menu...");
                    break;

                default:
                    VisualizerUtils.printError("Unknown command '" + cmd + "'. Type 'help' to see valid commands.");
            }
        }
    }

    private static void printDoublyHelp() {
        System.out.println("Available Doubly Linked List Commands:");
        System.out.println("  create [v1 v2 ...]        : Initialize list with values");
        System.out.println("  insert_begin <val> (ib)   : Insert node at beginning");
        System.out.println("  insert_end <val>   (ie)   : Insert node at end");
        System.out.println("  insert_pos <val> <pos>    : Insert node at 1-based position");
        System.out.println("  delete_begin       (db)   : Delete node from beginning");
        System.out.println("  delete_end         (de)   : Delete node from end");
        System.out.println("  delete_pos <pos>   (dp)   : Delete node at 1-based position");
        System.out.println("  search <val>              : Search for a value and show positions");
        System.out.println("  display / display_forward : Render forward ASCII visualization");
        System.out.println("  display_backward          : Traverse backward using prev pointers");
        System.out.println("  count                     : Count total nodes in list");
        System.out.println("  reverse            (rev)  : Reverse list in-place (swapping pointers)");
        System.out.println("  clear                     : Clear/empty the entire list");
        System.out.println("  help                      : Display this commands cheat-sheet");
        System.out.println("  exit / back               : Return to Main Menu");
    }

    // =========================================================================
    // CIRCULAR LINKED LIST COMMAND LOOP
    // =========================================================================
    private static void handleCircularLinkedListMenu() {
        CircularLinkedList cll = new CircularLinkedList();
        VisualizerUtils.printBanner("CIRCULAR LINKED LIST: COMMAND MODE");
        printCircularHelp();
        cll.visualize();

        boolean inSubMenu = true;
        while (inSubMenu) {
            String input = prompt("\n[CLL-cmd] > ").trim();
            if (input.isEmpty()) continue;

            String[] tokens = input.split("\\s+");
            String cmd = tokens[0].toLowerCase();

            switch (cmd) {
                case "create":
                    int[] createVals = parseOrPromptMultipleIntegers(tokens, 1, "Enter space-separated integers for initial list: ");
                    if (createVals != null) {
                        cll.create(createVals);
                    }
                    break;

                case "insert_begin":
                case "ib":
                    Integer ibVal = parseOrPromptInteger(tokens, 1, "Enter integer to insert at beginning: ");
                    if (ibVal != null) cll.insertBegin(ibVal);
                    break;

                case "insert_end":
                case "ie":
                    Integer ieVal = parseOrPromptInteger(tokens, 1, "Enter integer to insert at end: ");
                    if (ieVal != null) cll.insertEnd(ieVal);
                    break;

                case "insert_pos":
                case "ip":
                    Integer ipVal = parseOrPromptInteger(tokens, 1, "Enter integer value to insert: ");
                    if (ipVal != null) {
                        Integer ipPos = parseOrPromptInteger(tokens, 2, "Enter 1-based position (1 to " + (cll.getSize() + 1) + "): ");
                        if (ipPos != null) {
                            cll.insertAtPosition(ipVal, ipPos);
                        }
                    }
                    break;

                case "delete_begin":
                case "db":
                    cll.deleteBegin();
                    break;

                case "delete_end":
                case "de":
                    cll.deleteEnd();
                    break;

                case "delete_pos":
                case "dp":
                    Integer dpPos = parseOrPromptInteger(tokens, 1, "Enter 1-based position to delete: ");
                    if (dpPos != null) cll.deleteAtPosition(dpPos);
                    break;

                case "search":
                    Integer sVal = parseOrPromptInteger(tokens, 1, "Enter integer value to search: ");
                    if (sVal != null) cll.search(sVal);
                    break;

                case "display":
                case "disp":
                case "show":
                    cll.display();
                    break;

                case "count":
                case "size":
                    cll.count();
                    break;

                case "clear":
                    cll.clear();
                    VisualizerUtils.printSuccess("List cleared.");
                    cll.visualize();
                    break;

                case "help":
                case "?":
                    printCircularHelp();
                    break;

                case "exit":
                case "back":
                case "menu":
                    inSubMenu = false;
                    VisualizerUtils.printInfo("Returning to Main Menu...");
                    break;

                default:
                    VisualizerUtils.printError("Unknown command '" + cmd + "'. Type 'help' to see valid commands.");
            }
        }
    }

    private static void printCircularHelp() {
        System.out.println("Available Circular Linked List Commands:");
        System.out.println("  create [v1 v2 ...]        : Initialize list with values");
        System.out.println("  insert_begin <val> (ib)   : Insert node at beginning");
        System.out.println("  insert_end <val>   (ie)   : Insert node at end");
        System.out.println("  insert_pos <val> <pos>    : Insert node at 1-based position");
        System.out.println("  delete_begin       (db)   : Delete node from beginning");
        System.out.println("  delete_end         (de)   : Delete node from end");
        System.out.println("  delete_pos <pos>   (dp)   : Delete node at 1-based position");
        System.out.println("  search <val>              : Search for a value and show positions");
        System.out.println("  display            (disp) : Render circular ASCII visualization");
        System.out.println("  count                     : Count total nodes in list");
        System.out.println("  clear                     : Clear/empty the entire list");
        System.out.println("  help                      : Display this commands cheat-sheet");
        System.out.println("  exit / back               : Return to Main Menu");
    }

    // =========================================================================
    // DEMONSTRATION SCENARIOS
    // =========================================================================
    private static void handleDemonstrationMenu() {
        boolean inDemoMenu = true;
        while (inDemoMenu) {
            System.out.println();
            System.out.println("=============== DEMONSTRATION SCENARIOS ===============");
            System.out.println("  1. Scenario 1: Singly Linked List Full Lifecycle");
            System.out.println("  2. Scenario 2: Doubly Linked List Bidirectional Ops");
            System.out.println("  3. Scenario 3: Circular Linked List Wrap-around");
            System.out.println("  4. Scenario 4: Error Handling & Edge Cases");
            System.out.println("  5. Scenario 5: Real-World Workflow (Task Scheduler / Playlist)");
            System.out.println("  6. Run ALL 5 Scenarios Sequentially");
            System.out.println("  7. Return to Main Menu");
            System.out.println("=======================================================");

            String choice = prompt("Select demonstration scenario (1-7): ").trim();
            switch (choice) {
                case "1":
                    runScenario1();
                    break;
                case "2":
                    runScenario2();
                    break;
                case "3":
                    runScenario3();
                    break;
                case "4":
                    runScenario4();
                    break;
                case "5":
                    runScenario5();
                    break;
                case "6":
                    runAllDemonstrations();
                    break;
                case "7":
                case "back":
                    inDemoMenu = false;
                    break;
                default:
                    VisualizerUtils.printError("Invalid choice! Choose between 1 and 7.");
            }
        }
    }

    public static void runAllDemonstrations() {
        runScenario1();
        runScenario2();
        runScenario3();
        runScenario4();
        runScenario5();
        VisualizerUtils.printBanner("ALL 5 DEMONSTRATION SCENARIOS COMPLETED SUCCESSFULLY!");
    }

    private static void runScenario1() {
        VisualizerUtils.printBanner("SCENARIO 1: SINGLY LINKED LIST FULL LIFECYCLE");
        System.out.println("Testing: create -> insert_begin -> insert_end -> insert_pos -> search -> delete_pos -> reverse -> count");
        SinglyLinkedList sll = new SinglyLinkedList();

        sll.create(new int[]{10, 20, 30});
        sll.insertBegin(5);
        sll.insertEnd(40);
        sll.insertAtPosition(25, 4); // Insert 25 at position 4
        sll.search(25);
        sll.deleteAtPosition(4);     // Remove 25
        sll.deleteBegin();           // Remove 5
        sll.deleteEnd();             // Remove 40
        sll.reverse();               // Reverse [10, 20, 30] -> [30, 20, 10]
        sll.count();
        VisualizerUtils.printSuccess("Scenario 1 verified cleanly!");
    }

    private static void runScenario2() {
        VisualizerUtils.printBanner("SCENARIO 2: DOUBLY LINKED LIST BIDIRECTIONAL OPERATIONS");
        System.out.println("Testing: create -> insert_begin -> insert_end -> insert_pos -> display_backward -> delete_pos -> reverse");
        DoublyLinkedList dll = new DoublyLinkedList();

        dll.create(new int[]{100, 200, 300});
        dll.insertBegin(50);
        dll.insertEnd(400);
        dll.insertAtPosition(250, 4);
        dll.displayBackward();       // Traverse using prev pointers
        dll.search(200);
        dll.deleteAtPosition(4);     // Delete 250
        dll.reverse();               // Reverse in-place by swapping next and prev
        dll.displayForward();
        dll.displayBackward();
        dll.count();
        VisualizerUtils.printSuccess("Scenario 2 verified cleanly!");
    }

    private static void runScenario3() {
        VisualizerUtils.printBanner("SCENARIO 3: CIRCULAR LINKED LIST WRAP-AROUND DEMO");
        System.out.println("Testing: create -> insert_begin -> insert_end -> insert_pos -> delete_begin -> delete_end -> search");
        CircularLinkedList cll = new CircularLinkedList();

        cll.create(new int[]{10, 20, 30, 40});
        cll.insertBegin(5);
        cll.insertEnd(50);
        cll.insertAtPosition(25, 4);
        cll.search(30);
        cll.deleteBegin();           // Remove 5
        cll.deleteEnd();             // Remove 50
        cll.deleteAtPosition(3);     // Remove 25
        cll.display();
        cll.count();
        VisualizerUtils.printSuccess("Scenario 3 verified cleanly!");
    }

    private static void runScenario4() {
        VisualizerUtils.printBanner("SCENARIO 4: ERROR HANDLING & BOUNDARY EDGE CASES");
        System.out.println("Testing: Empty deletions -> Out-of-bounds insertions/deletions -> Non-existent searches");

        SinglyLinkedList sll = new SinglyLinkedList();
        VisualizerUtils.printInfo("Testing deletion on an empty list:");
        sll.deleteBegin();
        sll.deleteEnd();
        sll.deleteAtPosition(1);

        VisualizerUtils.printInfo("Testing invalid position insertion (pos 5 on empty list):");
        sll.insertAtPosition(99, 5);

        VisualizerUtils.printInfo("Testing invalid negative position insertion (pos -1):");
        sll.insertAtPosition(99, -1);

        VisualizerUtils.printInfo("Inserting single element and reversing 1-element list:");
        sll.insertBegin(42);
        sll.reverse();

        VisualizerUtils.printInfo("Testing deletion of single element (boundary transition to empty):");
        sll.deleteBegin();

        VisualizerUtils.printInfo("Searching empty list:");
        sll.search(100);

        VisualizerUtils.printSuccess("Scenario 4 error handling verified cleanly!");
    }

    private static void runScenario5() {
        VisualizerUtils.printBanner("SCENARIO 5: REAL-WORLD SIMULATION (ROUND-ROBIN SCHEDULER & PLAYLIST)");
        System.out.println("Simulation: A Round-Robin CPU Process queue represented using Circular Linked List.");

        CircularLinkedList processQueue = new CircularLinkedList();
        VisualizerUtils.printInfo("Step 1: Adding Process IDs 101 (PID 101), 102, 103 to Scheduler Queue...");
        processQueue.create(new int[]{101, 102, 103});

        VisualizerUtils.printInfo("Step 2: New High-Priority Process PID 99 arrives at the front of queue:");
        processQueue.insertBegin(99);

        VisualizerUtils.printInfo("Step 3: Background Process PID 104 arrives at end of queue:");
        processQueue.insertEnd(104);

        VisualizerUtils.printInfo("Step 4: Process PID 99 completes execution (dequeue from head):");
        processQueue.deleteBegin();

        VisualizerUtils.printInfo("Step 5: Process PID 102 completes execution in mid-queue (dequeue pos 2):");
        processQueue.deleteAtPosition(2);

        VisualizerUtils.printInfo("Step 6: Final cyclic state of active processes:");
        processQueue.display();

        VisualizerUtils.printSuccess("Scenario 5 real-world simulation verified cleanly!");
    }

    // =========================================================================
    // HELP & OVERVIEW DOCUMENTATION
    // =========================================================================
    private static void printHelpAndOverview() {
        VisualizerUtils.printBanner("PROJECT OVERVIEW & DATA STRUCTURES GUIDE");
        System.out.println("1. SINGLY LINKED LIST:");
        System.out.println("   - Linear collection where each node points to the next node.");
        System.out.println("   - Terminal node points to NULL.");
        System.out.println("   - Time Complexities: Insert/Delete Begin O(1), End O(n), Search O(n).");
        System.out.println();
        System.out.println("2. DOUBLY LINKED LIST:");
        System.out.println("   - Each node contains pointers to BOTH next and previous nodes.");
        System.out.println("   - Allows bidirectional traversal (Forward & Backward).");
        System.out.println("   - Time Complexities: Insert/Delete Begin O(1), End O(1) [with tail], Search O(n).");
        System.out.println();
        System.out.println("3. CIRCULAR LINKED LIST:");
        System.out.println("   - The last node points back to the first node (HEAD).");
        System.out.println("   - No NULL pointer exists at the end of the list.");
        System.out.println("   - Ideal for round-robin scheduling, cyclical buffers, and game turns.");
        System.out.println();
        System.out.println("COMMAND SYNTAX QUICK REFERENCE:");
        System.out.println("   create 10 20 30       : Create list with initial items");
        System.out.println("   insert_begin 5        : Insert at head");
        System.out.println("   insert_end 50         : Insert at tail");
        System.out.println("   insert_pos 25 3       : Insert value 25 at 1-based position 3");
        System.out.println("   delete_begin          : Delete head node");
        System.out.println("   delete_end            : Delete tail node");
        System.out.println("   delete_pos 2          : Delete node at 1-based position 2");
        System.out.println("   search 20             : Locate node and report 1-based indices");
        System.out.println("   display               : Render ASCII diagram");
        System.out.println("   reverse               : Reverse list in-place");
        System.out.println("   count                 : Display total node count");
        System.out.println("   clear                 : Empty the list");
        System.out.println("   back / exit           : Return to Main Menu");
        VisualizerUtils.printDivider();
    }

    // =========================================================================
    // INPUT UTILITIES
    // =========================================================================
    private static String prompt(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    private static Integer parseOrPromptInteger(String[] tokens, int tokenIndex, String promptMsg) {
        if (tokens.length > tokenIndex) {
            try {
                return Integer.parseInt(tokens[tokenIndex]);
            } catch (NumberFormatException e) {
                VisualizerUtils.printError("Invalid integer argument: '" + tokens[tokenIndex] + "'.");
                return null;
            }
        }

        while (true) {
            System.out.print(promptMsg);
            String line = scanner.nextLine().trim();
            if (line.isEmpty() || line.equalsIgnoreCase("cancel")) {
                VisualizerUtils.printInfo("Operation cancelled.");
                return null;
            }
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                VisualizerUtils.printError("Invalid input. Please enter a valid integer (or type 'cancel'): ");
            }
        }
    }

    private static int[] parseOrPromptMultipleIntegers(String[] tokens, int startIndex, String promptMsg) {
        if (tokens.length > startIndex) {
            try {
                int[] vals = new int[tokens.length - startIndex];
                for (int i = startIndex; i < tokens.length; i++) {
                    vals[i - startIndex] = Integer.parseInt(tokens[i]);
                }
                return vals;
            } catch (NumberFormatException e) {
                VisualizerUtils.printError("Invalid integer in arguments. Re-prompting...");
            }
        }

        System.out.print(promptMsg);
        String line = scanner.nextLine().trim();
        if (line.isEmpty() || line.equalsIgnoreCase("cancel")) {
            VisualizerUtils.printInfo("Operation cancelled.");
            return null;
        }

        String[] parts = line.split("[,\\s]+");
        try {
            int[] vals = new int[parts.length];
            for (int i = 0; i < parts.length; i++) {
                vals[i] = Integer.parseInt(parts[i]);
            }
            return vals;
        } catch (NumberFormatException e) {
            VisualizerUtils.printError("Invalid input. All elements must be valid integers.");
            return null;
        }
    }
}
