package com.example.demo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ThreadComparisonTest {

	@Test
	public void testThreadExecution() {
		Thread thread1 = new Thread(() -> {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		});

		Thread thread2 = new Thread(() -> {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		});

		thread1.start();
		thread2.start();

		assertTrue(thread1.isAlive() || thread2.isAlive(), "At least one thread should be alive during execution");
	}
}

