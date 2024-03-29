package com.app.MovieApp.security.common.config.security;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;


@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

	
	protected static final org.slf4j.Logger logger = LoggerFactory.getLogger(RestAuthenticationEntryPoint.class);

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {
		logger.info("Enter Into RestAuthenticationEntryPoint--commence Started");
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
		logger.info("Enter Into RestAuthenticationEntryPoint--commence Ended");
		
	}
}