
package com.app.MovieApp.igonore.controller;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AsyncService {

	@Autowired
	RestTemplate restTemplate;

	Logger log = LoggerFactory.getLogger(AsyncService.class);

	@Async
	public CompletableFuture<String> getApiBasedOnName() throws InterruptedException {
		log.info("Start of getApiBasedOnName");
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8090/name", String.class);
		String name = response.getBody();
		log.info("mail, {}", name);
		Thread.sleep(1000L); // Intentional delay
		log.info("End of getApiBasedOnName");
		return CompletableFuture.completedFuture(response.getBody());
	}

	@Async
	public CompletableFuture<String> getApiBasedOnMial() throws InterruptedException {
		log.info("Start of getApiBasedOnMial");
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8090/mail", String.class);
		String mail = response.getBody();
		log.info("mail, {}", mail);
		Thread.sleep(1000L); // Intentional delay
		log.info("End of getApiBasedOnMial");
		return CompletableFuture.completedFuture(response.getBody());
	}

	@Async
	public CompletableFuture<String> getApiBasedOnPhno() throws InterruptedException {
		log.info("Start of getApiBasedOnPhno");
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8090/number", String.class);
		String number = response.getBody();
		log.info("number, {}", number);
		Thread.sleep(1000L); // Intentional delay
		log.info("End of getApiBasedOnPhno");
		return CompletableFuture.completedFuture(response.getBody());
	}
}
