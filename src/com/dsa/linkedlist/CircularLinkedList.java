package com.dsa.linkedlist;

import java.util.ArrayList;
import java.util.List;

/**
 * Circular Linked List implementation with terminal-based ASCII visualization.
 * 
 * Features:
 * - Insertions: Beginning, End, Specific Position (1-based)
 * - Deletions: Beginning, End, Specific Position (1-based)
 * - Search with cycle safety and 1-based index reporting
 * - Display Circular Traversal with cycle loop indicator
 * - Node count
 * - High-compatibility ASCII Box diagram showing wrap-around cycle arrow from TAIL to HEAD
 */
public class CircularLinkedList {
    private Node head;
    private Node tail;
    private int size;

    public CircularLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Create / populate list from an array of integers.
     */
    public void create(int[] values) {
        clear();
        for (int val : values) {
            insertEnd(val, false);
        }
        VisualizerUtils.printSuccess("Created Circular Linked List with " + size + " element(s).");
        visualize();
    }

    /**
     * Insert at the beginning of the circular list.
     * Time Complexity: O(1) with tail reference
     */
    public void insertBegin(int data) {
        insertBegin(data, true);
    }

    private void insertBegin(int data, boolean showVisual) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
            newNode.next = head; // self loop
        } else {
            newNode.next = head;
            tail.next = newNode;
            head = newNode;
        }
        size++;

        if (showVisual) {
            VisualizerUtils.printSuccess("Inserted " + data + " at beginning (Position 1).");
            visualize();
        }
    }

    /**
     * Insert at the end of the circular list.
     * Time Complexity: O(1) with tail reference
     */
    public void insertEnd(int data) {
        insertEnd(data, true);
    }

    private void insertEnd(int data, boolean showVisual) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
            newNode.next = head;
        } else {
            tail.next = newNode;
            newNode.next = head;
            tail = newNode;
        }
        size++;

        if (showVisual) {
            VisualizerUtils.printSuccess("Inserted " + data + " at end (Position " + size + ").");
            visualize();
        }
    }

    /**
     * Insert at a specific 1-based position.
     * Valid positions: 1 to (size + 1).
     */
    public boolean insertAtPosition(int data, int position) {
        if (position < 1 || position > size + 1) {
            VisualizerUtils.printError("Invalid position (" + position + "). Valid range is 1 to " + (size + 1) + ".");
            return false;
        }

        if (position == 1) {
            insertBegin(data);
            return true;
        }
        if (position == size + 1) {
            insertEnd(data);
            return true;
        }

        Node newNode = new Node(data);
        Node curr = head;
        for (int i = 1; i < position - 1; i++) {
            curr = curr.next;
        }

        newNode.next = curr.next;
        curr.next = newNode;
        size++;

        VisualizerUtils.printSuccess("Inserted " + data + " at position " + position + ".");
        visualize();
        return true;
    }

    /**
     * Delete from the beginning of the circular list.
     * Time Complexity: O(1)
     */
    public Integer deleteBegin() {
        if (isEmpty()) {
            VisualizerUtils.printError("Underflow! Cannot delete from an empty list.");
            return null;
        }

        int removedVal = head.data;
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            tail.next = head;
        }
        size--;

        VisualizerUtils.printSuccess("Deleted " + removedVal + " from beginning.");
        visualize();
        return removedVal;
    }

    /**
     * Delete from the end of the circular list.
     * Time Complexity: O(n) to find the node before tail
     */
    public Integer deleteEnd() {
        if (isEmpty()) {
            VisualizerUtils.printError("Underflow! Cannot delete from an empty list.");
            return null;
        }

        int removedVal = tail.data;
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            Node curr = head;
            while (curr.next != tail) {
                curr = curr.next;
            }
            curr.next = head;
            tail = curr;
        }
        size--;

        VisualizerUtils.printSuccess("Deleted " + removedVal + " from end.");
        visualize();
        return removedVal;
    }

    /**
     * Delete from a specific 1-based position.
     * Valid positions: 1 to size.
     */
    public Integer deleteAtPosition(int position) {
        if (isEmpty()) {
            VisualizerUtils.printError("Underflow! Cannot delete from an empty list.");
            return null;
        }

        if (position < 1 || position > size) {
            VisualizerUtils.printError("Invalid position (" + position + "). Valid range is 1 to " + size + ".");
            return null;
        }

        if (position == 1) {
            return deleteBegin();
        }
        if (position == size) {
            return deleteEnd();
        }

        Node curr = head;
        for (int i = 1; i < position - 1; i++) {
            curr = curr.next;
        }

        int removedVal = curr.next.data;
        curr.next = curr.next.next;
        size--;

        VisualizerUtils.printSuccess("Deleted " + removedVal + " from position " + position + ".");
        visualize();
        return removedVal;
    }

    /**
     * Search for an element with cycle termination.
     */
    public List<Integer> search(int key) {
        List<Integer> positions = new ArrayList<>();
        if (isEmpty()) {
            VisualizerUtils.printWarning("List is empty. Element " + key + " not found.");
            return positions;
        }

        Node curr = head;
        int pos = 1;
        do {
            if (curr.data == key) {
                positions.add(pos);
            }
            curr = curr.next;
            pos++;
        } while (curr != head);

        if (!positions.isEmpty()) {
            VisualizerUtils.printSuccess("Element " + key + " FOUND at 1-based position(s): " + positions);
        } else {
            VisualizerUtils.printWarning("Element " + key + " NOT found in the circular list.");
        }
        visualizeWithHighlight(key);
        return positions;
    }

    public int count() {
        VisualizerUtils.printInfo("Total nodes in Circular Linked List: " + size);
        return size;
    }

    public void display() {
        visualize();
    }

    public void visualize() {
        visualizeWithHighlight(null);
    }

    /**
     * ASCII Terminal Visualization of Circular Linked List with wrap-around cycle arrow.
     */
    public void visualizeWithHighlight(Integer highlightKey) {
        VisualizerUtils.printSectionHeader("CIRCULAR LINKED LIST VISUALIZATION");

        if (isEmpty()) {
            System.out.println("  HEAD -> NULL (Empty Circular List)");
            VisualizerUtils.printDivider();
            return;
        }

        List<String> nodeBoxes = new ArrayList<>();
        List<Integer> nodeValues = new ArrayList<>();
        Node curr = head;
        do {
            nodeValues.add(curr.data);
            nodeBoxes.add("[" + curr.data + "]");
            curr = curr.next;
        } while (curr != head);

        String prefix = "  HEAD -> ";
        String arrow = " -> ";
        List<Integer> boxStartCols = new ArrayList<>();
        List<Integer> boxCenterCols = new ArrayList<>();

        int currentCol = prefix.length();
        for (int i = 0; i < nodeBoxes.size(); i++) {
            boxStartCols.add(currentCol);
            int boxLen = nodeBoxes.get(i).length();
            boxCenterCols.add(currentCol + boxLen / 2);
            currentCol += boxLen;
            if (i < nodeBoxes.size() - 1) {
                currentCol += arrow.length();
            }
        }

        // Build middle line with loop anchor at the right
        StringBuilder middleLine = new StringBuilder(prefix);
        for (int i = 0; i < nodeBoxes.size(); i++) {
            middleLine.append(nodeBoxes.get(i));
            if (i < nodeBoxes.size() - 1) {
                middleLine.append(arrow);
            }
        }
        middleLine.append(" -----+");

        int arrowCol = boxCenterCols.get(0);
        int returnCol = middleLine.length() - 1; // Position of '+'

        // Construct Top Bar (Line 1): Spaces ... +----...----+
        StringBuilder topLine = new StringBuilder();
        topLine.append(" ".repeat(Math.max(0, arrowCol)));
        topLine.append("+");
        int dashCount = Math.max(1, returnCol - arrowCol - 1);
        topLine.append("-".repeat(dashCount));
        topLine.append("+");

        // Construct Connector Bar (Line 2): Spaces ... v      ...      |
        StringBuilder connectorLine = new StringBuilder();
        connectorLine.append(" ".repeat(Math.max(0, arrowCol)));
        connectorLine.append("v");
        int innerSpaces = Math.max(1, returnCol - arrowCol - 1);
        connectorLine.append(" ".repeat(innerSpaces));
        connectorLine.append("|");

        // Print Top Bar and Down Connector
        System.out.println(topLine.toString());
        System.out.println(connectorLine.toString());

        // Print Nodes Line
        System.out.println(middleLine.toString());

        // Print Position and Pointer Indicators
        StringBuilder posLine = new StringBuilder("  ");
        for (int i = 0; i < nodeBoxes.size(); i++) {
            int pad = boxStartCols.get(i) - posLine.length();
            if (pad > 0) {
                posLine.append(" ".repeat(pad));
            }
            String pStr = "(Pos " + (i + 1) + (i == nodeBoxes.size() - 1 ? " TAIL" : "") + ")";
            posLine.append(pStr);
        }
        System.out.println(posLine.toString());

        // Step-by-step circular traversal simulation
        System.out.print("  Circular Traversal: ");
        curr = head;
        do {
            if (highlightKey != null && curr.data == highlightKey) {
                System.out.print("*" + curr.data + "* -> ");
            } else {
                System.out.print(curr.data + " -> ");
            }
            curr = curr.next;
        } while (curr != head);
        System.out.println("(loops back to " + head.data + ")");

        System.out.println("  Metadata: Size = " + size + " | Head = " + head.data + " | Tail = " + tail.data + " | Tail.next = " + tail.next.data + " (HEAD)");
        VisualizerUtils.printDivider();
    }
}
