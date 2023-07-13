
package com.app.MovieApp.security.common.config.security;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.PersistenceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.www.NonceExpiredException;
import org.springframework.stereotype.Component;

//import com.app.MovieApp.entity.UserAuthoritiesEntity;
import com.app.MovieApp.entity.UserEntity;
//import com.app.MovieApp.security.login.entity.UserAuthority;
import com.app.MovieApp.login.service.LoginService;
//import com.app.MovieApp.entity.UserProfileEntity;
import com.app.MovieApp.repository.LoginHistoryRepository;
import com.app.MovieApp.security.login.dto.UserContext;

@Component
public class LoginAuthenticationProvider implements AuthenticationProvider {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	BCryptPasswordEncoder encoder;

	@Resource
	LoginService loginService;

	@Resource
	LoginHistoryRepository loginHistoryRepository;

	@Resource
	AuthenticationManagerBuilder authenticationManagerBuilder;

	@Resource
	CustomUserDetailsService customUserDetailsService;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	public Authentication authenticate(Authentication auth) throws AuthenticationException {

		UsernamePasswordAuthenticationToken authentication = null;
		try {

			String username = (String) auth.getPrincipal();
			String password = (String) auth.getCredentials();

			UserEntity user = loginService.checkByUsernameOrEmail(username);

			System.out.println(encoder.encode(password));

			System.out.println(encoder.encode(password).equals(user.getPassword()));

			if (user == null) {
				logger.error("LoginAuthenticationProvider -- authenticate -- UserId not found in DataBase " + username);
				throw new UsernameNotFoundException("User not found: " + username);
			}
			if (!password.equals(user.getPassword())) {
				logger.error(
						"LoginAuthenticationProvider -- authenticate -- Authentication Failed. Username or Password not valid---->Userid "
								+ username);
				throw new BadCredentialsException("Authentication Failed. Username or Password not valid.");
			}

			String branchName = null;
//			
			List<GrantedAuthority> authorities = null;

			UserContext userContext = new UserContext();
			userContext.setAuthorities(authorities);

			authentication = new UsernamePasswordAuthenticationToken(username, password, authorities);
			authentication.setDetails(userContext);
			SecurityContextHolder.getContext().setAuthentication(authentication);

		} catch (PersistenceException e) {
			logger.error("DUPLICATE VALUE OR NULL VALUE NOT ALLOWED T0 SAVE" + e);
			logger.info("Error in LoginAuthenticationProvider ", e);
		} catch (NonceExpiredException e) {
			logger.error("User or Password Details Expired", e);
			throw new NonceExpiredException("User or Password Details Expired");
		} catch (Exception e) {
			logger.info("Error in LoginAuthenticationProvider ", e);
		}
		return authentication;
	}

	public boolean supports(Class<?> auth) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(auth));
	}

}
