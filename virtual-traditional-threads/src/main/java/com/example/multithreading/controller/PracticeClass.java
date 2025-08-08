package com.example.multithreading.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


public
class PracticeClass {

    @AllArgsConstructor
    @Getter
    @ToString
    public static class Customer{
        String name;
        List<Order> Order;
    }

    @AllArgsConstructor
    @Getter
    @ToString
    static
    class Order{
        int id;
        List<String> item;

        public
        Order(int id) {
        }
    }

    public static void FilterBasedOnNumber(){

        List<Customer> customers = List.of(
                new Customer("Alice", List.of(new Order(1), new Order(2))),
                new Customer("Bob", List.of(new Order(1), new Order(2), new Order(3), new Order(4))),
                new Customer("Charlie", List.of(new Order(1)))
        );

        List<String> list = customers.stream().filter(c-> c.getOrder().size() > 3).map(Customer::getName).toList();
        list.forEach(System.out::print);
    }

    public static void flattenList(){
        List<Order> orders = List.of(
                new Order(1, List.of("Item1", "Item2")),
                new Order(2, List.of("Item3")),
                new Order(3, List.of("Item4", "Item5", "Item6"))
        );
        List<String> list = orders.stream().flatMap(order -> order.getItem().stream()).toList();
    }

    @AllArgsConstructor
    @ToString
    public static
    class Orders {
        public int id;
        public LocalDate orderDate;
    }

    public
    void groupOrder() {

        List<Orders> orders = List.of(
                new Orders(1, LocalDate.of(2025, 7, 22)),
                new Orders(2, LocalDate.of(2025, 7, 22)),
                new Orders(3, LocalDate.of(2025, 7, 23))
        );

        Map<LocalDate, List<Orders>> collect = orders.stream().collect(Collectors.groupingBy(o -> o.orderDate));
        System.out.println(collect);

    }

    @AllArgsConstructor
    @Getter
    public static
    class Employee {
        private String name;
        private double salary;

        @Override
        public String toString(){
            return name + " - " + salary;
        }
    }

    public static
    void SortBasedOnNumber(){

        List<Employee> employees = List.of(
                new Employee("John", 50000),
                new Employee("Alice", 70000),
                new Employee("Bob", 60000)
        );

        Map<Double, List<Employee>> collect = employees.stream().collect(Collectors.groupingBy(Employee::getSalary));
       // System.out.println(collect);

       List<Employee> list = employees.stream().sorted(Comparator.comparing(Employee::getSalary).reversed()).toList();
       //list.forEach(e -> System.out.println(e.getName() + " - " + e.getSalary()));

        list.forEach(System.out::println);
    }


    public static void returnTopKElements(){

        List<String> words = List.of("apple", "banana", "apple", "orange", "banana", "apple", "kiwi");

        LinkedHashMap<String, Long> map = words.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(3)
                .collect(Collectors.toMap(
                        Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1,  LinkedHashMap::new

                ));

        List<String> top3 = words.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(3)
                .map(Map.Entry::getKey)
                .toList();

        words.stream().sorted(Comparator.comparing(String::length)).toList();
        words.stream().collect(Collectors.groupingBy(String::length));
        words.stream().filter(w -> w.contains("Java")).toList();
        words.stream().sorted(Comparator.naturalOrder()).skip(words.size() - 2);

    }

    private void streamsHandsOn(){

        List<Integer> list = Arrays.asList(1,21,11,31,101,91);

        System.out.println(list.stream().filter(num -> String.valueOf(num).startsWith("1")).toList());

        System.out.println(list.stream().sorted(Comparator.reverseOrder()).skip(1).toList());
        System.out.println(list.stream().distinct().sorted(Comparator.reverseOrder()).skip(1).findFirst());

        System.out.println(list.stream().min(Integer::compareTo).orElseThrow(NoSuchElementException::new));

        System.out.println(list.stream().max(Integer::compareTo).orElseThrow(NoSuchElementException::new));
    }

    private
    void handsOn(){
/*
Question No:1: Implement the below requirement for the above integer array using single Stream API statement:
Requirements:
1. remove duplicates
2. Sort it in ascending order
3. Square all the numbers
4. find greater than 20
5. find how many greater than 20 is there
6. Print the count of how many greater than 20 is there
Conditions:
7. condition is you have to implement requirements 1 to 5 altogether (in the same order of requirements) using only one stream() method in the first statement.
8. second statement is for Print
9. totally 2 statements are allowed
**/

        Integer[] nums = {-6,2,-4,3,-5,1,-6,2,-4,3,-5,1};

        long count = Arrays.stream(nums).distinct().sorted().map(n -> n * n).filter(n -> n > 20).count();


    }




}
