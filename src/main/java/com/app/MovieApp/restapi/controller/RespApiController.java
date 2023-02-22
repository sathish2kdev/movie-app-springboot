package com.app.MovieApp.restapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/RestAPi")
public class RespApiController {

	@GetMapping("/v1/sampleData")
	public ResponseEntity<?> getSampleData(){
		String sumvalue="RespApi";
		return new ResponseEntity<String>(sumvalue,HttpStatus.OK);
	}
}
