package com.example.multithreading;

import java.util.*;

public
class LRUCache {

    static
    class Node {
        int key, value;
        Node prev, next;

        Node(int k, int v) {
            key = key;
            value = value;
        }
    }

    private int capacity;
    Map<Integer, Node> map;
    Node head, tail;

    public LRUCache(int capacity){
        this.capacity = capacity;
        map = new HashMap<>();

        head = new Node(0, 0);
        tail = new Node(0,0);
        head.next = tail;
        tail.prev = head;
    }


    public
    int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }

        Node node = map.get(key);
        remove(node);
        insertToHead(node);
        return node.value;
    }

    public
    void put(int key, int value) {
        if (map.containsKey(key)) {
            Node existingNode = map.get(key);
            existingNode.value = value;
            remove(existingNode);
            insertToHead(existingNode);
        } else {
            if (map.size() >= capacity) {
                Node lru = tail.prev; // Least recently used
                remove(lru);
                map.remove(lru.key);
            }

            Node newNode = new Node(key, value);
            map.put(key, newNode);
            insertToHead(newNode);
        }
    }

    // --- Helper to remove a node from the linked list ---
    private
    void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // --- Helper to insert a new node right after the head ---
    private
    void insertToHead(Node node) {
        node.next = head.next;
        node.prev = head;

        head.next.prev = node;
        head.next = node;
    }


    // Optional: debug utility
    public
    void printCache() {
        Node curr = head.next;
        System.out.print("Cache: ");
        while (curr != tail) {
            System.out.print("(" + curr.key + ":" + curr.value + ") ");
            curr = curr.next;
        }
        System.out.println();
    }

    // --- Test code ---
    public static
    void main(String[] args) {
        LRUCache cache = new LRUCache(2);

        cache.put(1, 1);   // cache = {1=1}
        cache.put(2, 2);   // cache = {1=1, 2=2}
        System.out.println(cache.get(1)); // returns 1 → use 1, now most recently used

        cache.put(3, 3);   // evicts key 2 → cache = {1=1, 3=3}
        System.out.println(cache.get(2)); // returns -1 (not found)

        cache.put(4, 4);   // evicts key 1 → cache = {4=4, 3=3}
        System.out.println(cache.get(1)); // returns -1 (was evicted)
        System.out.println(cache.get(3)); // returns 3
        System.out.println(cache.get(4)); // returns 4

        cache.printCache();  // Optional debug output
    }
}
