package com.example.multithreading.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class TraditionalThreadService {

    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    @Async
    public void executeTasks() {
        for (int i = 0; i < 100; i++) {
            executorService.submit(() -> {
                TaskSimulator.simulateTask();
                System.out.println("Task executed by traditional thread: " + Thread.currentThread().getName());
            });
        }
        executorService.shutdown();
    }
}


