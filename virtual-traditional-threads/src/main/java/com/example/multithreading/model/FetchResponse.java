package com.example.multithreading.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FetchResponse {
    private long totalTimeMillis;
    private int successCount;
    private int failureCount;
    private int activeThreadCount;
    private long heapUsedBytes;
    private List<FetchResult> results;
}

