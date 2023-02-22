package com.app.MovieApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.MovieApp.dto.CarsouelContentDto;
import com.app.MovieApp.dto.common.ResponseDto;
import com.app.MovieApp.service.MovieAppService;

@RestController
public class MovieAppController {

	@Autowired
	MovieAppService movieAppService;

	@GetMapping({ "/api/dashboard/v1/getCarsoelDetails", "/dashboard/v1/getCarsoelDetails" })
	public ResponseEntity<?> getCarsoelDetails() {
		ResponseDto<List<CarsouelContentDto>> response = movieAppService.getCarsouelData();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping({ "/api/dashboard/v1/headerLabel", "/dashboard/v1/headerLabel" })
	public ResponseEntity<?> getHeaderLabelContent() {
		ResponseDto<List<String>> response = movieAppService.getHeaderDetails();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping({"/api/Category/v1/getAllCategortype","/Category/v1/getAllCategortype"})
	public ResponseEntity<?> getCategoryType(){
		return null;
	}

	@GetMapping("/carsouel")
	public void Test() {
		System.err.println("HEL OL");
	}
}
