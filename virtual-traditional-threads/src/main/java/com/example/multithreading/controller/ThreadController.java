package com.example.multithreading.controller;

import com.example.multithreading.model.FetchResponse;
import com.example.multithreading.service.ThreadService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ThreadController {

    private final ThreadService threadService;

    public
    ThreadController(ThreadService threadService) {
        this.threadService = threadService;
    }

    @PostMapping("/traditional")
    public
    FetchResponse fetchTraditional(@RequestBody List<String> urls) throws InterruptedException {
        return threadService.fetchUsingTraditionalThreads(urls);
    }

    @PostMapping("/virtual")
    public
    FetchResponse fetchVirtual(@RequestBody List<String> urls) throws InterruptedException {
        return threadService.fetchUsingVirtualThreads(urls);
    }
}




