package com.example.multithreading.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;

@Service
public
class VirtualThreadService {

    public
    void executeTasks() {
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 100; i++) {
                executor.submit(() -> {
                    TaskSimulator.simulateTask();
                    System.out.println("Task executed by virtual thread: " + Thread.currentThread().getName());
                });
            }
        }
    }

    public
    String executeDynamicVirtualThread() {
        StringBuilder result = new StringBuilder();

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 1; i <= 10; i++) { // Adjust the number of tasks as needed
                int taskNumber = i;
                executor.execute(() -> {
                    String taskResult = "Task " + taskNumber + " executed in virtual thread: " + Thread.currentThread() + "\n";
                    result.append(taskResult);
                    try {
                        Thread.sleep(500); // Simulate some work
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        result.append("Task ").append(taskNumber).append(" was interrupted.\n");
                    }
                });
            }
        } // Automatically closes the executor here
        result.append("All tasks have been submitted!");
        return result.toString();
    }
}


