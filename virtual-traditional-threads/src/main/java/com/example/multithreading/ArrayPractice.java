package com.example.multithreading;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public
class ArrayPractice {


    public static
    void MaxSubArraySum() {
        int[] Num = {-2, -1, 0, 1, 4, -7, 5};
        int maxSoFar = Num[0];
        int currMax = Num[0];
        for (int i : Num) {
            currMax = Math.max(i, currMax + i);
            maxSoFar = Math.max(currMax, maxSoFar);
        }
        System.out.println(maxSoFar);
    }

    public  static  void firstNonRepeating(){

        String str = "swiss";
       char[] strArr = str.toCharArray();

       Map<Character, Integer> mapper = new LinkedHashMap<>();

       for (char c : strArr){
           mapper.put(c, mapper.getOrDefault(c, 0)+ 1);
       }
        System.out.println(mapper);

       for(Map.Entry<Character, Integer> entry : mapper.entrySet()){
           if(entry.getValue() == 1){
               System.out.println(entry.getKey());
               break;
           }
       }

        Character stream = str.chars().mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() == 1)
                .map(Map.Entry :: getKey)
                .findFirst()
                .orElse(null);

        System.out.println(stream);

    }

    private static void missingNumber(){
        int[] arr = {1,2,4,5};
        int length = arr.length + 1;
        int sum = length * (length +1) / 2;
        System.out.println("totalSum : " + sum);

        for(int i : arr){
            sum -= i;
        }
        System.out.println("Missing number: " + sum);

    }

    private static void reverseWords(){
        String sentence = "Java 8 is powerful";
        String collect = Arrays.stream(sentence.split(" "))
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        lst -> {
                            Collections.reverse(lst);
                            return String.join(" ", lst);
                        }
                ));

        System.out.println(collect);
    }


    public static
    void main(String[] args) {
        reverseWords();
    }
}
