package com.example.multithreading;

import java.util.*;

public class BalancedParenthesesValidator {

    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char ch : s.toCharArray()) {
            // Push opening brackets
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            }
            // Process closing brackets
            else if (ch == ')' || ch == ']' || ch == '}') {
                if (stack.isEmpty()) return false; // No opening for closing
                char top = stack.pop();
                if (!isMatchingPair(top, ch)) return false;
            }
        }

        return stack.isEmpty(); // If stack is empty, it's valid
    }

    private static boolean isMatchingPair(char open, char close) {
        return (open == '(' && close == ')') ||
                (open == '[' && close == ']') ||
                (open == '{' && close == '}');
    }

    public static void main(String[] args) {
        String[] testCases = {
                "()", "([])", "{[()]}", "([)]", "(((", "({}", "[]{}"
        };

        for (String test : testCases) {
            System.out.println("Input: " + test + " ➜ " + isValid(test));
        }
    }
}
