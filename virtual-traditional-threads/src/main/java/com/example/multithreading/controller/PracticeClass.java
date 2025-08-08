package com.example.multithreading.controller;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

public
class PracticeClass {

    @AllArgsConstructor
    public static class Customer{
        String name;
        List<Order> Order;
    }

    @AllArgsConstructor
    static
    class Order{
        int id;
    }

    public void FilterBasedOnNumber(){

        List<Customer> customers = List.of(
                new Customer("Alice", List.of(new Order(1), new Order(2))),
                new Customer("Bob", List.of(new Order(1), new Order(2), new Order(3), new Order(4))),
                new Customer("Charlie", List.of(new Order(1)))
        );

        List<Customer> list = customers.stream().filter(c-> c.Order.size() > 3).collect(Collectors.toList());

        System.out.println(list);

    }

}
