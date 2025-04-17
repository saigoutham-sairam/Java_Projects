package com.example.multithreading.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;

@Service
public class VirtualThreadService {

    public void executeTasks() {
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 100; i++) {
                executor.submit(() -> {
                    TaskSimulator.simulateTask();
                    System.out.println("Task executed by virtual thread: " + Thread.currentThread().getName());
                });
            }
        }
    }
}


