package com.app.MovieApp.security.common.config.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.www.NonceExpiredException;
import org.springframework.stereotype.Component;

import com.app.MovieApp.security.common.config.exception.AuthMethodNotSupportedException;
import com.app.MovieApp.security.common.config.exception.JwtExpiredTokenException;
import com.app.MovieApp.security.common.config.security.constants.ErrorCode;
import com.app.MovieApp.security.common.config.security.dto.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This is an implementation class for AuthenticationFailureHandler. If
 * authentication fails, the configured AuthenticationFailureHandler will be
 * invoked.
 * 
 * @author Praveen
 *
 */

@Component
public class LoginAuthenticationFailureHandler implements AuthenticationFailureHandler {

	/**
	 * This is an customized class for handling authentication fails.
	 */
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		Class aClass = exception.getClass();
		System.out.println("exception->" + aClass.getName());
		System.out.println(exception.getMessage());
		if (exception instanceof BadCredentialsException) {
			new ObjectMapper().writeValue(response.getWriter(), new ErrorResponse("Invalid username or password",
					ErrorCode.AUTHENTICATION, HttpStatus.UNAUTHORIZED));
		} else if (exception instanceof JwtExpiredTokenException) {
			new ObjectMapper().writeValue(response.getWriter(),
					new ErrorResponse("Token has expired", ErrorCode.JWT_TOKEN_EXPIRED, HttpStatus.UNAUTHORIZED));
		} else if (exception instanceof AuthMethodNotSupportedException) {
			new ObjectMapper().writeValue(response.getWriter(), new ErrorResponse("Authentication Method Not Supported",
					ErrorCode.AUTHENTICATION, HttpStatus.UNAUTHORIZED));
		} else if (exception instanceof NonceExpiredException) {
			new ObjectMapper().writeValue(response.getWriter(),
					new ErrorResponse("Login credentials expired. Please reset the password.", ErrorCode.AUTHENTICATION, HttpStatus.UNAUTHORIZED));
		} else {
			new ObjectMapper().writeValue(response.getWriter(),
					new ErrorResponse("Authentication failed", ErrorCode.AUTHENTICATION, HttpStatus.UNAUTHORIZED));
		}

	}

}
