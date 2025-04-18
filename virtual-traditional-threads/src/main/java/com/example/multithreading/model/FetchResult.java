package com.example.multithreading.model;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class FetchResult {
    private String url;
    private long durationMillis;
    private boolean success;
    private JsonNode body;
}

