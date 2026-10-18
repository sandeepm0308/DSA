package com.dsa.linkedlist;

import java.util.ArrayList;
import java.util.List;

/**
 * Singly Linked List implementation with terminal-based ASCII visualization.
 * 
 * Features:
 * - Insertions: Beginning, End, Specific Position (1-based)
 * - Deletions: Beginning, End, Specific Position (1-based)
 * - Search with 1-based index reporting
 * - Reverse list in-place (O(n) time, O(1) auxiliary space)
 * - Node count
 * - High-compatibility ASCII diagram showing HEAD, TAIL, next pointers, and NULL
 */
public class SinglyLinkedList {
    private Node head;
    private int size;

    public SinglyLinkedList() {
        this.head = null;
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
        VisualizerUtils.printSuccess("Created Singly Linked List with " + size + " element(s).");
        visualize();
    }

    /**
     * Insert at the beginning of the list. Time Complexity: O(1)
     */
    public void insertBegin(int data) {
        insertBegin(data, true);
    }

    private void insertBegin(int data, boolean showVisual) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
        size++;

        if (showVisual) {
            VisualizerUtils.printSuccess("Inserted " + data + " at beginning (Position 1).");
            visualize();
        }
    }

    /**
     * Insert at the end of the list. Time Complexity: O(n)
     */
    public void insertEnd(int data) {
        insertEnd(data, true);
    }

    private void insertEnd(int data, boolean showVisual) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            head = newNode;
        } else {
            Node curr = head;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = newNode;
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
     * Delete from the beginning of the list. Time Complexity: O(1)
     */
    public Integer deleteBegin() {
        if (isEmpty()) {
            VisualizerUtils.printError("Underflow! Cannot delete from an empty list.");
            return null;
        }

        int removedVal = head.data;
        head = head.next;
        size--;

        VisualizerUtils.printSuccess("Deleted " + removedVal + " from beginning.");
        visualize();
        return removedVal;
    }

    /**
     * Delete from the end of the list. Time Complexity: O(n)
     */
    public Integer deleteEnd() {
        if (isEmpty()) {
            VisualizerUtils.printError("Underflow! Cannot delete from an empty list.");
            return null;
        }

        if (head.next == null) {
            int removedVal = head.data;
            head = null;
            size = 0;
            VisualizerUtils.printSuccess("Deleted " + removedVal + " from end (list is now empty).");
            visualize();
            return removedVal;
        }

        Node curr = head;
        while (curr.next.next != null) {
            curr = curr.next;
        }

        int removedVal = curr.next.data;
        curr.next = null;
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
     * Search for an element. Reports all 1-based positions where it is found.
     */
    public List<Integer> search(int key) {
        List<Integer> positions = new ArrayList<>();
        if (isEmpty()) {
            VisualizerUtils.printWarning("List is empty. Element " + key + " not found.");
            return positions;
        }

        Node curr = head;
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
     * Reverse the singly linked list in-place.
     * Time Complexity: O(n), Space Complexity: O(1)
     */
    public void reverse() {
        if (isEmpty()) {
            VisualizerUtils.printWarning("List is empty. Nothing to reverse.");
            return;
        }

        if (head.next == null) {
            VisualizerUtils.printInfo("List has only 1 node. Reversal complete.");
            visualize();
            return;
        }

        Node prev = null;
        Node curr = head;
        Node nextNode = null;

        while (curr != null) {
            nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
        }

        head = prev;
        VisualizerUtils.printSuccess("Singly Linked List successfully reversed in-place!");
        visualize();
    }

    /**
     * Count nodes and display summary.
     */
    public int count() {
        VisualizerUtils.printInfo("Total nodes in Singly Linked List: " + size);
        return size;
    }

    public void visualize() {
        visualizeWithHighlight(null);
    }

    /**
     * ASCII Terminal Visualization of the Singly Linked List.
     */
    public void visualizeWithHighlight(Integer highlightKey) {
        VisualizerUtils.printSectionHeader("SINGLY LINKED LIST VISUALIZATION");

        if (isEmpty()) {
            System.out.println("  HEAD -> NULL");
            System.out.println("  (List is currently empty: Size = 0)");
            VisualizerUtils.printDivider();
            return;
        }

        List<String> nodeBoxes = new ArrayList<>();
        List<Integer> nodeValues = new ArrayList<>();
        Node curr = head;
        while (curr != null) {
            nodeValues.add(curr.data);
            nodeBoxes.add("[" + curr.data + " | *]");
            curr = curr.next;
        }

        String arrow = " -> ";
        List<Integer> boxStartCols = new ArrayList<>();
        List<Integer> boxCenterCols = new ArrayList<>();

        int currentCol = 0;
        for (int i = 0; i < nodeBoxes.size(); i++) {
            boxStartCols.add(currentCol);
            int boxLen = nodeBoxes.get(i).length();
            boxCenterCols.add(currentCol + boxLen / 2);
            currentCol += boxLen;
            if (i < nodeBoxes.size() - 1) {
                currentCol += arrow.length();
            }
        }

        StringBuilder nodesLine = new StringBuilder();
        for (int i = 0; i < nodeBoxes.size(); i++) {
            nodesLine.append(nodeBoxes.get(i));
            if (i < nodeBoxes.size() - 1) {
                nodesLine.append(arrow);
            }
        }
        nodesLine.append(" -> NULL");

        int headCenter = boxCenterCols.get(0);
        int tailCenter = boxCenterCols.get(boxCenterCols.size() - 1);

        int maxLen = Math.max(nodesLine.length(), tailCenter + 10);
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
        System.out.println("  " + nodesLine.toString());

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

        System.out.print("  Traversal: ");
        curr = head;
        while (curr != null) {
            if (highlightKey != null && curr.data == highlightKey) {
                System.out.print("*" + curr.data + "* -> ");
            } else {
                System.out.print(curr.data + " -> ");
            }
            curr = curr.next;
        }
        System.out.println("NULL");

        System.out.println("  Metadata : Size = " + size + " | Head = " + head.data + " | Tail = " + nodeValues.get(nodeValues.size() - 1));
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
