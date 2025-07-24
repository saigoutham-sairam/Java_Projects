package com.example.multithreading;

import com.example.multithreading.controller.AppLogger;
import lombok.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

//@SpringBootApplication
//public class ThreadComparisonApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(ThreadComparisonApplication.class, args);
//	}
//}

public class ThreadComparisonApplication {
	public static void main(String[] args) {
		update();
	}

	public static synchronized void update(){
		//
	}

	private final
	ReentrantLock lock = new ReentrantLock();

	public void updateLock(){
		lock.lock();
		try{
			//
		} finally {
			lock.unlock();
		}
	}

}

