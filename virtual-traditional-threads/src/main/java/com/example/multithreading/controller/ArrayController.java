package com.example.multithreading.controller;

import com.example.multithreading.LearningApplication;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public
class ArrayController {

    public
    void getSumOfArray() {
        int[] numbers = {1, 2, 3, 3, 5, 7, 5, 11};
        int sum = 0;
        for (int number : numbers) {
            sum = sum + number;
        }
    }

    public
    void findMaximumOfArray() {
        int[] numbers = {1, 2, 3, 3, 5, 7, 5, 11};
        int max = numbers[0];
        for (int number : numbers) {
            if (number > max) {
                max = number;
            }
        }
        System.out.println("Sum of the Array:" + max);
    }

    public
    void removeDuplicatesFromArray() {
        int[] numbers = {1, 2, 1091, 3, 3, 5, 424, 7, 5, 11, 83};
        Set<Integer> set = new LinkedHashSet<>();
        for (int number : numbers) {
            set.add(number);
        }

        ArrayList<Integer> noDuplicateList = new ArrayList<>(set);
        System.out.println(noDuplicateList);
    }

    public
    void sortAnArray() {
        int[] numbers = {1, 2, 1091, 3, 3, 5, 424, 7, 5, 11, 83};
        Set<Integer> set = new LinkedHashSet<Integer>();
        for (int number : numbers) {
            set.add(number);
        }
        List<Integer> noDuplicateList = new ArrayList<Integer>(set);
        Collections.sort(noDuplicateList);
        System.out.println(noDuplicateList);

        //int[] sortedArray = noDuplicateList.stream().mapToInt(i -> i).toArray();
    }

    public
    void reverseLinkedList() {
        LinkedList<Integer> list = new LinkedList<Integer>();
        list.add(1091);
        list.add(247);
        list.add(365);
        list.add(456);
        list.sort(Collections.reverseOrder());
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list);
        }
    }

    public
    void findDuplicates() {
        int[] numbers = {4, 2, 7, 2, 5, 4, 9, 7};

        Set<Integer> seen = new HashSet<>();
        Set<Integer> duplicates = new HashSet<>();
        for (int num : numbers) {
            if (!seen.add(num)) {
                duplicates.add(num);
            }
        }
        if (duplicates.isEmpty()) {
            System.out.println("No duplicates found.");
        } else {
            System.out.println("Duplicate elements: " + duplicates);
        }
    }

    public
    void filterTransactions() {
        @Getter
        @Setter
        @ToString
        @AllArgsConstructor
        @NoArgsConstructor
        class Transaction {
            public String name;
            public int amount;
        }

        List<Transaction> transactions = Arrays.asList(
                new Transaction("T1", 900),
                new Transaction("T2", 1100),
                new Transaction("T3", 500),
                new Transaction("T4", 1300),
                new Transaction("T5", 700),
                new Transaction("T6", 1600)
        );



        List<Transaction> list = transactions.stream().filter(t -> t.getAmount() > 1000).collect(Collectors.toList());
        list.forEach(System.out::println);

        Optional<List<String>> transactionList =Optional.of(transactions.stream().map(Transaction::
                getName).filter(Objects::nonNull).collect(Collectors.toList()));
        transactionList.ifPresent(System.out::println);
    }

    public
    void singletonExample() {
        // Singleton Class helper
        AppLogger logger = AppLogger.getInstance();
        logger.log("Application Started");
    }

    public
    void MaxSubarraySum() {
        int[] Num = {-2, -1, 0, 1, 4, -7, 5};

        int maxSoFar = Num[0];
        int currentMax = Num[0];

        for (int i = 1; i < Num.length; i++) {
            currentMax = Math.max(Num[i], currentMax + Num[i]);
            maxSoFar = Math.max(currentMax, maxSoFar);
        }
        System.out.println(maxSoFar);

        Optional<String> name = Optional.of("chatgpt");
        name.ifPresent(System.out::println);

        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "Sai");

        Hashtable<Integer, String> table = new Hashtable<>();
        table.put(1, "Goutham");

        Optional<String> str = Optional.of("Hello");
        str.ifPresent(System.out::println);
    }

    private
    void getFibonacciSeries() {
        int n1 = 0, n2 = 1;
        int N = 10;
        for (int i = 0; i < N; i++) {
            int next = n1 + n2;
            n1 = n2;
            n2 = next;
            System.out.println(next);
        }
    }

    private
    void findFirstNonRepeatingCharacterInAString() {

        //Logic 1
        String name = "aabbcdeffgh";
//        for (int i =0; i< name.length(); i++) {
//            char current = name.charAt(i);
//            if(name.indexOf(current) == name.lastIndexOf(current)){
//                System.out.println(current);
//                return;
//            }
//        }

        //logic 2
        Map<Character, Integer> charCountMap = new LinkedHashMap<>();
        char[] strArray = name.toCharArray();
        for (char c : strArray) {
            charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
        }
        for (Map.Entry<Character, Integer> entry : charCountMap.entrySet()) {
            if (entry.getValue() == 1) {
                System.out.println(entry.getKey());
                return;
            }
        }
    }

    public void completableFutureExample() throws ExecutionException, InterruptedException {

        CompletableFuture<String> greeting = CompletableFuture.supplyAsync(() -> "Hello").
                thenApply(msg -> msg + "Saigoutham").
                thenApply(String::toUpperCase);
        System.out.println(greeting.get());

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() ->
                {
                    return "Hello";
                }
        );
        System.out.println(future.get());

        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> "World");
        CompletableFuture<String> Combined = f1.thenCombine(f2, (a, b) -> a + " " + b);
        System.out.println(Combined.get());

        CompletableFuture<Integer> exception = CompletableFuture
                .supplyAsync(()-> 10/0)
                .exceptionally(ex -> {
                    System.out.println("Illegal Operation");
                    return 0;
                });
        System.out.println(exception.get());
    }

    public void findRepeatingWords(){
        String str = "aabbcdeffgh";
        char[] strArr = str.toCharArray();
        Map<Character, Integer> charMap = new HashMap<>();
        for(int c = 0; c< strArr.length; c++){
            charMap.put(strArr[c], charMap.getOrDefault(strArr[c],0)+1);
        }
        for (Map.Entry<Character, Integer> mapper : charMap.entrySet()){
            if(mapper.getValue() > 1) {
                System.out.println(mapper.getKey() + " " + mapper.getValue());
            }
        }

        for(int c = 0; c < strArr.length; c++){
            if(str.indexOf(c) != str.lastIndexOf(c)){
                System.out.println(c + " " + str.lastIndexOf(c));
            }
        }
    }

    public void palindromeExample(){
        List<String> dataset = Arrays.asList(
                "madam", "SpringBoot", "java", "deed", "Python", "noon", "Kotlin", "Bootspring", "Racecar"
        );
        List<String> lowerCaseData = dataset.stream().map(String::toLowerCase).collect(Collectors.toList());
        Set<String> result = new HashSet<>();
        for (String s : lowerCaseData) {
            if (s.contains("spring") && s.contains("boot")) {
                result.add(s);
            }
        }
        result.forEach(System.out::println);
        for (String str : lowerCaseData) {
            String reversedStr = new StringBuilder(str).reverse().toString();
            if(str.equals(reversedStr)){
                System.out.println(str);
            }
        }
    }

    public
    static
    boolean checkDuplicate(String input) {
        Set<Character> duplicateSet = new LinkedHashSet<>();
        Set<Character> uniqueSet = new LinkedHashSet<>();

        for (Character c : input.toCharArray()) {

            if (!uniqueSet.add(c)) {
                duplicateSet.add(c);
            }
        }
        if (!duplicateSet.isEmpty()) {
            System.out.println("There are duplicates in the string");
            duplicateSet.forEach(System.out::println);
            return false;
        } else {
            uniqueSet.forEach(System.out::println);
            System.out.println("There are no duplicates in the string");
            return true;
        }
    }


    /**
     * ControllerAdvice example
     * ***/
    @ControllerAdvice
    public static
    class GlobalExceptionHandler{
        @ExceptionHandler(NullPointerException.class)
        public
        ResponseEntity<Object> handleException(NullPointerException ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    public void reverseString(){
        String input = "Package123";
        char[] inputArr = input.toCharArray();

        int left = 0;
        int right = inputArr.length - 1;

       while(right > left){
           char temp = inputArr[left];
           inputArr[left] = inputArr[right];
           inputArr[right] = temp;
           left ++;
           right --;
       }
       System.out.println(inputArr);

        String reversed = new StringBuilder(input).reverse().toString();
    }

    public void isPrime(){
        int input = 71;
        BigInteger bi = new BigInteger(String.valueOf(input));
        if(bi.isProbablePrime(0)){
            System.out.println("it's a prime");
        }

        boolean b = IntStream.rangeClosed(2, (int) Math.sqrt(input)).noneMatch(n -> n % input == 0);

        for (int i = 3; i < Math.sqrt(input); i += 2){
            if(input % i == 0){
                System.out.println("Prime");
            }
        }
    }


    @AllArgsConstructor
    @Setter
    @Getter
    @NoArgsConstructor
    @ToString
    public static
    class Student {
        public String name;
        public String city;
    }
    public
    void filterTheList() {
        List<Student> studentList = Arrays.asList(
                new Student("test1", "Chennai"),
                new Student("test2", "Mumbai"),
                new Student("test3", "Bangalore"),
                new Student("test4", "Bangalore"));
        var list1 = studentList.stream().filter(list -> list.getCity().equals("Bangalore")).collect(Collectors.toList());
        list1.forEach(System.out::println);
        List<String> filtered = studentList.stream().filter(list -> list.getCity().equals("Bangalore")).map(Student::getName).collect(Collectors.toList());
        filtered.forEach(System.out::println);
    }

    public  void sortBasedOnLength(){
        List<String> listOfStrings = Arrays.asList("AC", "BCD", "EFGHS", "IJKLAMSNCA", "C");
        //sort this based on the length

        listOfStrings.sort(Comparator.comparingInt(String::length));
        listOfStrings.forEach(System.out::println);

        List<String> sorted = listOfStrings.stream().sorted(Comparator.comparingInt(String::length)).collect(Collectors.toList());
        sorted.forEach(System.out::println);
    }

    public void flatMapExample(){

        List<List<String>> listOfLists = Arrays.asList(
                Arrays.asList("C", "JAVA", "PYTHON"),
                Arrays.asList("ANGULAR", "DOCKER", "KUBERNETES"),
                Arrays.asList("JAVA", "REACTJS", "PYTHON")
        );
        //OUPUT :  [C,JAVA,PYTHON,ANGULAR,DOCKER,KUBERNETES,JAVA,REACTJS,PYTHON]

        List<String> list = listOfLists.stream().flatMap(Collection::stream).toList();
        System.out.println(list);

        List<String> newList = listOfLists.stream().flatMap(Collection::stream).sorted(Comparator.comparingInt(String::length).reversed()).collect(Collectors.toList());
        newList.forEach(System.out::print);
        System.out.print(" New :List : " + newList);

        List<List<Integer>> listOfListInt = Arrays.asList(
                Arrays.asList(1,2,3),
                Arrays.asList(4,5));

        List<Integer> intList = listOfListInt.stream().flatMap(Collection::stream).toList();
        intList.forEach( s -> System.out.println("Integer : " + s));
    }

}
