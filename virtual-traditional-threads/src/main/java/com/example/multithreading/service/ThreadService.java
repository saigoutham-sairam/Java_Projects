package com.example.multithreading.service;

import com.example.multithreading.model.FetchResponse;
import com.example.multithreading.model.FetchResult;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.ThreadMXBean;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Service
public class ThreadService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public
    FetchResponse fetchUsingTraditionalThreads(List<String> urls) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        return fetchAll(urls, executor);
    }

    public FetchResponse fetchUsingVirtualThreads(List<String> urls) throws InterruptedException {
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
        return fetchAll(urls, executor);
    }

    private FetchResponse fetchAll(List<String> urls, ExecutorService executor) throws InterruptedException {
        long startTime = System.currentTimeMillis();

        List<Future<FetchResult>> futures = new ArrayList<>();
        for (String url : urls) {
            futures.add(executor.submit(() -> fetchUrl(url)));
        }

        List<FetchResult> results = new ArrayList<>();
        int success = 0, failure = 0;

        for (Future<FetchResult> future : futures) {
            try {
                FetchResult result = future.get();
                results.add(result);
                if (result.isSuccess()) success++;
                else failure++;
            } catch (ExecutionException e) {
                failure++;
                ObjectNode errorNode = objectMapper.createObjectNode();
                errorNode.put("error", e.getCause().toString());
                results.add(new FetchResult("error", 0, false, errorNode));
            }
        }

        executor.shutdown();
        executor.awaitTermination(30, TimeUnit.SECONDS);
        long endTime = System.currentTimeMillis();

        // JVM metrics
        ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();
        int threadCount = threadBean.getThreadCount();

        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        long heapUsed = memoryBean.getHeapMemoryUsage().getUsed();

        return new FetchResponse(endTime - startTime, success, failure, threadCount, heapUsed, results);
    }

    private FetchResult fetchUrl(String url) {
        long start = System.currentTimeMillis();
        try (InputStream inputStream = new URL(url).openStream()) {
            JsonNode body = objectMapper.readTree(inputStream);
            long duration = System.currentTimeMillis() - start;
            return new FetchResult(url, duration, true, body);
        } catch (Exception e) {
            long duration = System.currentTimeMillis() - start;
            ObjectNode errorNode = objectMapper.createObjectNode();
            errorNode.put("error", e.toString());
            return new FetchResult(url, duration, false, errorNode);
        }
    }
}


