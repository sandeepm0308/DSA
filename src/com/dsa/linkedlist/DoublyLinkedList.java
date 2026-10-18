package com.dsa.linkedlist;

import java.util.ArrayList;
import java.util.List;

/**
 * Doubly Linked List implementation with terminal-based ASCII visualization.
 * 
 * Features:
 * - Insertions: Beginning, End, Specific Position (1-based)
 * - Deletions: Beginning, End, Specific Position (1-based)
 * - Search with 1-based index reporting
 * - Display Forward and Display Backward
 * - Reverse list in-place by swapping prev & next pointers
 * - Node count
 * - High-compatibility ASCII diagram showing HEAD, TAIL, bidirectional arrows, and NULL boundaries
 */
public class DoublyLinkedList {
    private DNode head;
    private DNode tail;
    private int size;

    public DoublyLinkedList() {
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
        VisualizerUtils.printSuccess("Created Doubly Linked List with " + size + " element(s).");
        visualize();
    }

    /**
     * Insert at the beginning of the list. Time Complexity: O(1)
     */
    public void insertBegin(int data) {
        insertBegin(data, true);
    }

    private void insertBegin(int data, boolean showVisual) {
        DNode newNode = new DNode(data);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;

        if (showVisual) {
            VisualizerUtils.printSuccess("Inserted " + data + " at beginning (Position 1).");
            visualize();
        }
    }

    /**
     * Insert at the end of the list. Time Complexity: O(1) with tail pointer
     */
    public void insertEnd(int data) {
        insertEnd(data, true);
    }

    private void insertEnd(int data, boolean showVisual) {
        DNode newNode = new DNode(data);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
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

        DNode curr = head;
        for (int i = 1; i < position - 1; i++) {
            curr = curr.next;
        }

        DNode newNode = new DNode(data);
        newNode.next = curr.next;
        newNode.prev = curr;
        if (curr.next != null) {
            curr.next.prev = newNode;
        }
        curr.next = newNode;
        size++;

        VisualizerUtils.printSuccess("Inserted " + data + " at position " + position + ".");
        visualize();
        return true;
    }

    /**
     * Delete from the beginning. Time Complexity: O(1)
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
            head.prev = null;
        }
        size--;

        VisualizerUtils.printSuccess("Deleted " + removedVal + " from beginning.");
        visualize();
        return removedVal;
    }

    /**
     * Delete from the end. Time Complexity: O(1) with tail pointer
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
            tail = tail.prev;
            tail.next = null;
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

        DNode curr = head;
        for (int i = 1; i < position; i++) {
            curr = curr.next;
        }

        int removedVal = curr.data;
        curr.prev.next = curr.next;
        curr.next.prev = curr.prev;
        size--;

        VisualizerUtils.printSuccess("Deleted " + removedVal + " from position " + position + ".");
        visualize();
        return removedVal;
    }

    /**
     * Search for an element. Reports all 1-based positions where it is found.
     */
    public List<Integer> search(int key) {
        List<Integer> positions = new ArrayList<>();
        if (isEmpty()) {
            VisualizerUtils.printWarning("List is empty. Element " + key + " not found.");
            return positions;
        }

        DNode curr = head;
        int pos = 1;
        while (curr != null) {
            if (curr.data == key) {
                positions.add(pos);
            }
            curr = curr.next;
            pos++;
        }

        if (!positions.isEmpty()) {
            VisualizerUtils.printSuccess("Element " + key + " FOUND at 1-based position(s): " + positions);
        } else {
            VisualizerUtils.printWarning("Element " + key + " NOT found in the list.");
        }
        visualizeWithHighlight(key);
        return positions;
    }

    /**
     * Reverse the doubly linked list in-place by swapping next and prev pointers.
     * Time Complexity: O(n), Space Complexity: O(1)
     */
    public void reverse() {
        if (isEmpty()) {
            VisualizerUtils.printWarning("List is empty. Nothing to reverse.");
            return;
        }

        if (head == tail) {
            VisualizerUtils.printInfo("List has only 1 node. Reversal complete.");
            visualize();
            return;
        }

        DNode curr = head;
        DNode temp = null;

        while (curr != null) {
            temp = curr.prev;
            curr.prev = curr.next;
            curr.next = temp;
            curr = curr.prev;
        }

        tail = head;
        head = temp.prev;

        VisualizerUtils.printSuccess("Doubly Linked List successfully reversed in-place!");
        visualize();
    }

    public int count() {
        VisualizerUtils.printInfo("Total nodes in Doubly Linked List: " + size);
        return size;
    }

    public void displayForward() {
        visualize();
    }

    /**
     * Display backward traversal from TAIL to HEAD using prev pointers.
     */
    public void displayBackward() {
        VisualizerUtils.printSectionHeader("DOUBLY LINKED LIST: BACKWARD TRAVERSAL");
        if (isEmpty()) {
            System.out.println("  TAIL -> NULL (Empty List)");
            VisualizerUtils.printDivider();
            return;
        }

        System.out.print("  Backward Traversal: TAIL -> ");
        DNode curr = tail;
        while (curr != null) {
            System.out.print("[" + curr.data + "]");
            if (curr.prev != null) {
                System.out.print(" -> ");
            }
            curr = curr.prev;
        }
        System.out.println(" -> HEAD (NULL)");
        System.out.println("  Verified backwards using `prev` pointer references.");
        VisualizerUtils.printDivider();
    }

    public void visualize() {
        visualizeWithHighlight(null);
    }

    /**
     * ASCII Terminal Visualization of the Doubly Linked List.
     */
    public void visualizeWithHighlight(Integer highlightKey) {
        VisualizerUtils.printSectionHeader("DOUBLY LINKED LIST VISUALIZATION");

        if (isEmpty()) {
            System.out.println("  NULL <- HEAD / TAIL -> NULL");
            System.out.println("  (List is currently empty: Size = 0)");
            VisualizerUtils.printDivider();
            return;
        }

        List<String> nodeBoxes = new ArrayList<>();
        List<Integer> nodeValues = new ArrayList<>();
        DNode curr = head;
        while (curr != null) {
            nodeValues.add(curr.data);
            nodeBoxes.add("[" + curr.data + "]");
            curr = curr.next;
        }

        String leftNull = "NULL <- ";
        String rightNull = " -> NULL";
        String biArrow = " <-> ";

        List<Integer> boxStartCols = new ArrayList<>();
        List<Integer> boxCenterCols = new ArrayList<>();

        int currentCol = leftNull.length();
        for (int i = 0; i < nodeBoxes.size(); i++) {
            boxStartCols.add(currentCol);
            int boxLen = nodeBoxes.get(i).length();
            boxCenterCols.add(currentCol + boxLen / 2);
            currentCol += boxLen;
            if (i < nodeBoxes.size() - 1) {
                currentCol += biArrow.length();
            }
        }

        StringBuilder diagram = new StringBuilder(leftNull);
        for (int i = 0; i < nodeBoxes.size(); i++) {
            diagram.append(nodeBoxes.get(i));
            if (i < nodeBoxes.size() - 1) {
                diagram.append(biArrow);
            }
        }
        diagram.append(rightNull);

        int headCenter = boxCenterCols.get(0);
        int tailCenter = boxCenterCols.get(boxCenterCols.size() - 1);

        int maxLen = Math.max(diagram.length(), tailCenter + 10);
        char[] labelLine = new char[maxLen];
        char[] pointerLine = new char[maxLen];
        for (int i = 0; i < maxLen; i++) {
            labelLine[i] = ' ';
            pointerLine[i] = ' ';
        }

        if (size == 1) {
            String headTailLabel = "HEAD / TAIL";
            int labelStart = Math.max(0, headCenter - (headTailLabel.length() / 2));
            placeString(labelLine, headTailLabel, labelStart);
            pointerLine[headCenter] = 'v';
        } else {
            String headLabel = "HEAD";
            int headStart = Math.max(0, headCenter - (headLabel.length() / 2));
            placeString(labelLine, headLabel, headStart);
            pointerLine[headCenter] = 'v';

            String tailLabel = "TAIL";
            int tailStart = Math.max(headStart + headLabel.length() + 1, tailCenter - (tailLabel.length() / 2));
            placeString(labelLine, tailLabel, tailStart);
            pointerLine[tailCenter] = 'v';
        }

        System.out.println("  " + trimRight(new String(labelLine)));
        System.out.println("  " + trimRight(new String(pointerLine)));
        System.out.println("  " + diagram.toString());

        StringBuilder posLine = new StringBuilder("  ");
        for (int i = 0; i < nodeBoxes.size(); i++) {
            int pad = boxStartCols.get(i) - (posLine.length() - 2);
            if (pad > 0) {
                posLine.append(" ".repeat(pad));
            }
            String pStr = "(Pos " + (i + 1) + ")";
            posLine.append(pStr);
        }
        System.out.println(posLine.toString());

        System.out.print("  Forward  : ");
        curr = head;
        while (curr != null) {
            if (highlightKey != null && curr.data == highlightKey) {
                System.out.print("*" + curr.data + "* <-> ");
            } else {
                System.out.print(curr.data + " <-> ");
            }
            curr = curr.next;
        }
        System.out.println("NULL");

        System.out.println("  Metadata : Size = " + size + " | Head = " + head.data + " | Tail = " + tail.data);
        VisualizerUtils.printDivider();
    }

    private static void placeString(char[] buffer, String str, int startIdx) {
        for (int i = 0; i < str.length() && (startIdx + i) < buffer.length; i++) {
            buffer[startIdx + i] = str.charAt(i);
        }
    }

    private static String trimRight(String s) {
        int i = s.length() - 1;
        while (i >= 0 && Character.isWhitespace(s.charAt(i))) {
            i--;
        }
        return s.substring(0, i + 1);
    }
}
