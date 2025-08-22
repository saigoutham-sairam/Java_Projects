package com.example.multithreading;

import lombok.*;
import org.jetbrains.annotations.NotNull;

import javax.swing.text.html.parser.Entity;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public
class StreamAPIPractice {

/*Requirement:
Remove duplicates
Multiply by 3
Keep only numbers divisible by 2
Sort descending
Print all remaining elements*/

    public static
    void test1() {
        int[] nums = {2, 3, 5, 6, 2, 3, 10, 5, 7};
        Stream<Integer> result = Arrays.stream(nums)
                .distinct()
                .map(n -> n * 3)
                .filter(n -> (n % 2 == 0))
                .boxed()
                .sorted(Comparator.reverseOrder());

        result.forEach(System.out::println);
    }

    /* Requirement: Remove duplicates,Convert to uppercase,Filter those with length > 5,Sort alphabetically,Count them */
    public static
    void test2() {

        String[] fruits = {"apple", "banana", "mango", "apple", "kiwi", "banana"};
        long count = Arrays.stream(fruits)
                .distinct()
                .map(String::toUpperCase)
                .filter(s -> s.length() >= 5)
                //   .sorted()
                .count();
        System.out.println(count);
    }

    /*Find the sum of all distinct even numbers greater than 15*/
    public static
    void test3() {
        List<Integer> numbers = Arrays.asList(10, 15, 20, 25, 30, 30, 10);
        int sum = numbers.stream().distinct()
                .filter(n -> n % 2 == 0 && n > 15)
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println("test 3: " + sum);
    }


//     Challenge 1: Distinct, Sort, Filter, Count
// 1. Remove duplicates
// 2. Sort in ascending order
// 3. Keep only even numbers > 5
// 4. Count and print how many such numbers

    public static
    void test4() {
        int[] nums = {4, 5, 6, 5, 6, 7, 8, 9};
        Object[] array = Arrays.stream(nums)
                .distinct()
                .boxed()
                .sorted()
                .filter(n -> n % 2 == 0 && n > 5)
                .toArray();
        System.out.println(Arrays.stream(array).count() + " -> " + Arrays.toString(array));
    }


// Challenge 2: Square & Filter
// 1. Square each number
// 2. Filter those divisible by 4
// 3. Collect and print them in a list
    public static
    void test5() {
        List<Integer> numbers = Arrays.asList(3, 5, 6, 7, 8);

        List<Integer> result = numbers
                .stream()
                .map(n -> n * n)
                .filter(n -> n % 4 == 0)
                .toList();

        result.forEach(System.out::println);
    }

    //Challenge 3: Transform & Join Strings
// 1. Convert all to uppercase
// 2. Filter names with length > 2
// 3. Join them using ", " in a single line and print


    public static
    void test6() {
        List<String> languages = Arrays.asList("java", "python", "go", "rust", "js");

        String result = languages.stream()
                .map(String::toUpperCase)
                .filter(s -> s.length() > 2)
                .collect(Collectors.joining(","));
        System.out.println(result);

        System.out.println(languages.stream()
                .map(String::toUpperCase)
                .filter(n -> n.length() > 2)
                .collect(Collectors.joining("-")));
    }

    // Challenge 4: Custom Sort
// 1. Filter names starting with 'C' or later
// 2. Sort in reverse alphabetical order
// 3. Print each name
    public static
    void test7() {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");
        names.stream()
                .filter(n -> n.compareToIgnoreCase("c") >= 0)
                .sorted(Comparator.reverseOrder())
                .forEach(System.out::println);
    }

    //Challenge 5: Grouping
// 1. Group words by their first letter
// 2. Print the map: {a=[apple, apricot], b=[banana, blueberry], g=[grape]}
    public static
    void test8() {
        List<String> words = Arrays.asList("apple", "banana", "grape", "apricot", "blueberry");
        Map<Character, List<String>> grouped = words.stream()
                .collect(Collectors.groupingBy(s -> s.charAt(0)));
        System.out.println(grouped);
    }

    //Challenge 6: Word Frequency Map
// 1. Calculate frequency of each word
// 2. Output: {java=3, python=1, go=2}
    public static
    void test9() {
        String[] sentence = {"java", "python", "java", "go", "go", "java"};

        Map<String, Long> collect = Arrays.stream(sentence)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(collect
        );
    }

    //Challenge 7: Average Calculation : Find average of marks > 75
    public static
    void test10() {
        List<Integer> marks = Arrays.asList(60, 70, 80, 90, 100);

        OptionalDouble average = marks.stream().filter(m -> m > 75)
                .mapToInt(Integer::intValue)
                .average();
    }

    // Challenge 8: FlatMap
// 1. Flatten into a single List<String>
// 2. Output: [A, B, C, D, E]
    public static
    void test11() {

        List<List<String>> nestedList = Arrays.asList(
                Arrays.asList("A", "B"),
                Arrays.asList("C", "D"),
                Arrays.asList("E")
        );
        nestedList.stream().flatMap(Collection::stream).toList();
    }

    /*
     * Partition a list
     * Given a list of user with id, name and age.
     * separate the list based on major and minor ages
     * */

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @ToString
    public static
    class User {
        int id;
        String name;
        int age;
    }

    public static
    void test12() {
        List<User> user = Arrays.asList(
                new User(1, "Alice", 15),
                new User(1, "Bob", 23),
                new User(1, "Charlie", 17),
                new User(1, "Danny", 31)
        );
        Map<Boolean, List<User>> collect =
                user
                        .stream()
                        .collect(Collectors.partitioningBy(u -> u.getAge() > 18));
        System.out.println(collect);
    }


    // Challenge 9: Top N Elements : Find top 3 highest scores
    public static
    void test13() {
        List<Integer> scores = Arrays.asList(88, 91, 82, 67, 99, 100, 74);

        List<Integer> result = scores.stream().sorted(Comparator.reverseOrder()).limit(3).toList();
        System.out.println(result);
    }

    //Challenge 10: Number Summary : Use summaryStatistics to print count, max, min, average, sum
    public static
    void test14() {
        List<Integer> nums = Arrays.asList(10, 20, 30, 40, 50);

        int[] numArr = {4, 5, 6, 5, 6, 7, 8, 9};

        IntSummaryStatistics stats =
                nums.stream()
                        .mapToInt(Integer::intValue)
                        .summaryStatistics();
        System.out.println(stats);

        IntSummaryStatistics intSummaryStatistics = Arrays.stream(numArr)
                .summaryStatistics();
        System.out.println("From Array : -> " + intSummaryStatistics);
    }


    private static void secondHighest(){
        List<Integer> numbers = Arrays.asList(5, 1, 9, 2, 9, 7, 5);

        Integer kNumber = numbers.stream()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(1).findFirst()
                .orElse(null);

        System.out.println(kNumber);
    }

    private static void ListToMap(){
        List<String> list = Arrays.asList("Java", "Spring", "Docker", "AWS");

        Map<Integer, List<String>> map = list.stream()
                .collect(Collectors.groupingBy(String::length));
        System.out.println(map);
    }

    private static void charFrequency(){
        String str = "epam interview";

        Map<Character, Long> collect = str.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(collect);


    }

    public static
    void main(String[] args) {
        charFrequency();
    }

}
