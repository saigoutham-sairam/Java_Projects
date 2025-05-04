# Getting Started

### Service Summary
We have built a Spring Boot REST API that takes a list of URLs and fetches their responses concurrently. 
The service returns a structured JSON array of all the results.

There are two endpoints:
POST /traditional: Uses traditional (platform) threads
POST /virtual: Uses virtual threads (Java 21+ feature)

Refer **src/main/resources/sample-test-data.json** file for sample request data.

### How It Works:
You POST a JSON array of URLs.
The service sends HTTP GET requests to all URLs in parallel.
It collects each JSON response (or error), formats it nicely, and returns a combined JSON array.

### What Are We Comparing?
You’re comparing Traditional Threads vs Virtual Threads on:

### Traditional Threads
Threading Model - OS-managed
Memory Usage - High (each thread ~1MB stack)
Concurrency Limit - Limited (~hundreds to low thousands)
Performance - Slows down or crashes under load
Scalability - Needs tuning (pools, etc.)

### Virtual Threads
Threading Model - JVM-managed (lightweight)
Memory Usage - Low (as low as a few KB)
Concurrency Limit - Very high (~tens of thousands or more)
Performance - Handles high concurrency smoothly
Scalability -Scales naturally for I/O-bound work

### Goal of This Exercise
Stress test both endpoints with many URLs (e.g., 100+ slow HTTP calls like httpbin.org/delay/1)

### Measure and observe:
API response time
CPU & memory usage
Thread behavior under load

### The virtual thread version should be faster and more resource-efficient when dealing with many concurrent I/O operations.