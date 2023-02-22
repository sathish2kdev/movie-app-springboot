package com.app.MovieApp.security.common.config.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.MovieApp.entity.UserEntity;
import com.app.MovieApp.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userRepository.findByUsernameOrEmail(username);
		if (user == null) {
//			logger.error("LoginAuthenticationProvider -- authenticate -- UserId not found in DataBase " + username);
			throw new UsernameNotFoundException("User not found: " + username);
		}

		return UserPrincipal.create(user);
	}

}
