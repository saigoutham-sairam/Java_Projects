package com.example.multithreading.service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public
class TaskSimulator {
    public static
    void simulateTask() {
        try {
            URL url = new URL("https://httpbin.org/delay/1"); // Simulates 1-second delay
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                System.out.println("API call simulated successfully.");
            }
        } catch (IOException e) {
            System.err.println("Error simulating API call: " + e.getMessage());
        }
    }
}
