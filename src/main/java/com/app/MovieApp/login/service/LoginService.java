package com.app.MovieApp.login.service;

import com.app.MovieApp.entity.UserEntity;

public interface LoginService {

	UserEntity checkByUsernameOrEmail(String username);

}
