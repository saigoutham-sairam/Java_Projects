package com.example.demo;

import com.example.multithreading.ThreadComparisonApplication;
import com.example.multithreading.model.FetchResponse;
import com.example.multithreading.service.ThreadService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@AutoConfigureMockMvc
@SpringBootTest(classes = ThreadComparisonApplication.class)
public class ThreadServiceTest {

    @Autowired
    private ThreadService threadService;

    private List<String> validUrls;
    private List<String> mixedUrls;

    @BeforeEach
    void setup() {
        validUrls = Arrays.asList(
                "https://httpbin.org/delay/1",
                "https://httpbin.org/get"
        );

        mixedUrls = Arrays.asList(
                "https://httpbin.org/delay/1",
                "https://bad.url.fake"
        );
    }

    @Test
    void testFetchUsingTraditionalThreads_validUrls() throws InterruptedException {
        FetchResponse response = threadService.fetchUsingTraditionalThreads(validUrls);

        assertEquals(2, response.getSuccessCount());
        assertEquals(0, response.getFailureCount());
        assertEquals(2, response.getResults().size());
        assertTrue(response.getTotalTimeMillis() >= 1000);
    }

    @Test
    void testFetchUsingVirtualThreads_mixedUrls() throws InterruptedException {
        FetchResponse response = threadService.fetchUsingVirtualThreads(mixedUrls);

        assertEquals(1, response.getSuccessCount());
        assertEquals(1, response.getFailureCount());
        assertEquals(2, response.getResults().size());

        boolean hasError = response.getResults().stream()
                .anyMatch(r -> !r.isSuccess() && r.getBody().has("error"));

        assertTrue(hasError);
    }
}

