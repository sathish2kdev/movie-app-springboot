package com.app.MovieApp.igonore.controller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class AsyncController {

	@Autowired
	AsyncService asyncService;

	Logger log = LoggerFactory.getLogger(AsyncController.class);

	@GetMapping("/check")
	public ResponseEntity<String> checkReponseENtity()
			throws InterruptedException, JsonProcessingException, JSONException, ExecutionException {
		log.info("start of service");
//		AsyncService AsyncService = new AsyncService();
//		AsyncService.getApiBasedOnName();
		CompletableFuture<String> name = asyncService.getApiBasedOnName();
		CompletableFuture<String> mail = asyncService.getApiBasedOnMial();
		CompletableFuture<String> no = asyncService.getApiBasedOnPhno();
		CompletableFuture.allOf(name, mail, no).join();
		log.info(name + " " + mail + " " + no);
		JSONObject json = new JSONObject();
		json.put("name", name.get());
		json.put("mailID", mail.get());
		json.put("phNo", no.get());
		log.info("end of service");
		String jsonData = json.toString();
		return new ResponseEntity<String>(jsonData, HttpStatus.OK);
	}
}
