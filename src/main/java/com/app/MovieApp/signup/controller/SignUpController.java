package com.app.MovieApp.signup.controller;

import java.util.Collections;
import java.util.Date;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.MovieApp.common.IDSContant;
import com.app.MovieApp.dto.SignUpRequest;
import com.app.MovieApp.entity.UserEntity;
import com.app.MovieApp.entity.UserRolesEntity;
import com.app.MovieApp.repository.UserRepository;
import com.app.MovieApp.repository.UserRoleRepository;

@RestController
@RequestMapping("/auth")
public class SignUpController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserRoleRepository userRoleRepository;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@PostMapping("/v1/signup")
	@Transactional
	public ResponseEntity<?> registerUser(@Validated @RequestBody SignUpRequest signUpRequest) {
		if (userRepository.existsByUserName(signUpRequest.getUserName())) {
			return new ResponseEntity("Username is already taken!", HttpStatus.BAD_REQUEST);
		}

		if (userRepository.existsByEmailId(signUpRequest.getEmailId())) {
			return new ResponseEntity("Email Address already in use!", HttpStatus.BAD_REQUEST);
		}

		// Creating user's account
		UserEntity user = new UserEntity();
		user.setFirstName(signUpRequest.getFirstName());
		user.setMiddleName(signUpRequest.getMiddleName());
		user.setLastName(signUpRequest.getLastName());
		user.setUserName(signUpRequest.getUserName());
		user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
		user.setCreatedBy(IDSContant.USER.getValue());
		user.setCreatedDate(new Date());
		user.setStatus(IDSContant.ACTIVE.getValue());
		user.setMobileNumber(Long.valueOf(signUpRequest.getMobileNumber()));
		Set<UserRolesEntity> userRole = userRoleRepository.findByRoleName(IDSContant.USER.getValue());
		user.setUserRoles(userRole);
		userRepository.save(user);
		return new ResponseEntity<String>("User registered successfully", HttpStatus.ACCEPTED);
	}

}
