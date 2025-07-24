package com.example.multithreading;

import org.springframework.boot.autoconfigure.integration.IntegrationAutoConfiguration;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public
class LearnerClass {
    /*************************
     3. Find First Non-Repeating Character in a String
     Write a function that returns the first non-repeating character.
     ***************************/
    public
    char firstUniqChar(String str) {
        if (str == null || str.isEmpty()) {
            return '#'; // or throw exception
        }
        char[] strArr = str.toCharArray();
        Map<Character, Integer> charMap = new LinkedHashMap<>();
        for (char c : strArr) {
            charMap.put(c, charMap.getOrDefault(c, 0) + 1);  // find the frequency of the char
        }
        for (Map.Entry<Character, Integer> entry : charMap.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }

        charMap.entrySet().stream().filter(entry -> entry.getValue() == 1).map(Map.Entry::getKey).findFirst();

        return charMap.entrySet().stream().filter(entry -> entry.getValue() == 1).map(Map.Entry::getKey).findFirst().orElse('#');
        //return '#'; // denotes no unique character found
    }

    /************************
     * 1. Detect a Cycle in a Linked List
     * Problem: Given the head of a singly linked list, determine if the list has a cycle in it.
     * *********************/

    static
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public
    static
    boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    private
    void checkCycle() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next = head.next.next.next.next;
        System.out.print(hasCycle(head));
    }
/*
4. Group Anagrams
Given a list of strings, group the anagrams together.

Java

public List<List<String>> groupAnagrams(String[] strs)
Hint: Use a map with sorted string as key.
 * ***********************************/

    public
    List<List<String>> groupAnagrams(String[] input) {
        //String[] input = {"eat", "tea", "tan", "ate", "nat", "bat"};
        Map<String, List<String>> anagramMap = new LinkedHashMap<>();
        for (String word : input) {
            char[] wordArr = word.toCharArray();  // convert word to char Array
            Arrays.sort(wordArr);  // get the sortedkey
            String sortedKey = new String(wordArr); // convert back to String

            if (!anagramMap.containsKey(sortedKey)) {
                anagramMap.put(sortedKey, new ArrayList<>()); // put the sorted key and empty array
            }
            anagramMap.get(sortedKey).add(word); // add the word to corresponding sortedkey in the list in the value section
        }
        System.out.println(anagramMap);
        return new ArrayList<>(anagramMap.values()); // return the values as a list, O/P would be list of list
    }

    public
    List<Integer> topKFrequent(int[] nums, int k) {

        int[] numArr = {1, 1, 1, 2, 2, 3};
        int key = 3;
        Map<Integer, Integer> numberMap = new LinkedHashMap<>();

        for (int j : numArr) {
            numberMap.put(j, numberMap.getOrDefault(j, 0) + 1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>(
                Comparator.comparingInt(Map.Entry::getValue)
        );

        for (Map.Entry<Integer, Integer> entry : numberMap.entrySet()) {
            minHeap.offer(entry);
            if (minHeap.size() > key) {
                minHeap.poll();
            }
        }
        List<Integer> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            result.add(minHeap.poll().getKey());
        }
        return result;
    }

    public void findCommon(){
        //Q. Write a program to compare the elements between two arrays.
        Integer[] Arr1 = {1,2,3,4,5};
        Integer[] Arr2 = {1,3,6,9,5};

        var superSet = Arrays.stream(Arr1).filter(s -> Arrays.stream(Arr2).noneMatch(x -> x.equals(s))).toList();
        System.out.println("superSet :" + superSet);

        System.out.println("Works when exactly arrays are same :" + Arrays.compare(Arr1, Arr2));
        Set<Integer> set1 = new LinkedHashSet<>(Arrays.asList(Arr1));
        Set<Integer> set2 = new LinkedHashSet<>(Arrays.asList(Arr2));

        System.out.println("Stream : " + set1.stream().filter(set2::contains).collect(Collectors.toList()));

        set1.retainAll(set2);
        System.out.print("Retained common elements from both sets in Set 1 : " + set1);
        System.out.print("Set 2 : " + set2);

        for (int i : Arr1) {
            for (int j : Arr2) {
                if (i == j) {
                    System.out.println("Elements in Array 1 and 2 are equal :" + i + " =  " + j);
                }
            }
        }
    }

}
