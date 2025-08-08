package com.example.multithreading;

import com.example.multithreading.controller.PracticeClass;
import lombok.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@SpringBootApplication
@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableAspectJAutoProxy
public
class LearningApplication {

    public static
    void main(String[] args) {


        List<Integer> list = Arrays.asList(1,21,11,31,101,91,101);
        System.out.println(list.stream().distinct().sorted(Comparator.reverseOrder()).skip(1).findFirst());

    }


}

