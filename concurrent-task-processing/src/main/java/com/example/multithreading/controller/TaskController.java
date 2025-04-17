package com.example.multithreading.controller;

import com.example.multithreading.service.TraditionalThreadService;
import com.example.multithreading.service.VirtualThreadService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

    private final TraditionalThreadService traditionalThreadService;
    private final VirtualThreadService virtualThreadService;

    public TaskController(TraditionalThreadService traditionalThreadService, VirtualThreadService virtualThreadService) {
        this.traditionalThreadService = traditionalThreadService;
        this.virtualThreadService = virtualThreadService;
    }

    @GetMapping("/process-traditional")
    public String processUsingTraditionalThreads() {
        traditionalThreadService.executeTasks();
        return "Processing tasks with traditional threads!";
    }

    @GetMapping("/process-virtual")
    public String processUsingVirtualThreads() {
        virtualThreadService.executeTasks();
        return "Processing tasks with virtual threads!";
    }
}




