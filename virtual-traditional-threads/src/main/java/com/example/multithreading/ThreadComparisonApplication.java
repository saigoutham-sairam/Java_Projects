package com.example.multithreading;

import com.example.multithreading.controller.AppLogger;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.*;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.Condition;
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
	ReentrantLock lock = new ReentrantLock(true);
	Condition condition = lock.newCondition();

	public
	void updateLock() {
		lock.lock();
		try {
			condition.signal();
		} finally {
			lock.unlock();
		}
	}

	@PostConstruct
	public
	void init() {

	}

	@PreDestroy
	public
	void cleanUp() {

	}

	public static
	class Bean implements InitializingBean, DisposableBean {

		@Override
		public
		void destroy() throws Exception {

		}

		@Override
		public
		void afterPropertiesSet() throws Exception {

		}
	}


	//JWT
	public static
	class JWTAuthenticationFilter extends OncePerRequestFilter {

		@Override
		protected
		void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
			String authHeader = String.valueOf(request.getHeaders("Authorization"));

			if(authHeader.contains("Bearer")){
				//
			}

			filterChain.doFilter(request, response);
		}
	}




}

