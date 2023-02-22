package com.app.MovieApp.login.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.MovieApp.entity.UserEntity;
import com.app.MovieApp.login.service.LoginService;
import com.app.MovieApp.repository.UserRepository;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserEntity checkByUsernameOrEmail(String username) {
		UserEntity userData = null;
		try {
			userData = userRepository.findByUsernameOrEmail(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userData;
	}

}
