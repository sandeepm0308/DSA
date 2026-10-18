package com.dsa.linkedlist;

/**
 * Node representation for Singly and Circular Linked Lists.
 * Contains an integer data value and a reference to the next node.
 */
public class Node {
    public int data;
    public Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }

    public Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }
}
