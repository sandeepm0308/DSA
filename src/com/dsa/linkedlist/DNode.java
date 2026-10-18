package com.dsa.linkedlist;

/**
 * Node representation for Doubly Linked List.
 * Contains an integer data value, a pointer to the previous node,
 * and a pointer to the next node.
 */
public class DNode {
    public int data;
    public DNode prev;
    public DNode next;

    public DNode(int data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }

    public DNode(int data, DNode prev, DNode next) {
        this.data = data;
        this.prev = prev;
        this.next = next;
    }
}
