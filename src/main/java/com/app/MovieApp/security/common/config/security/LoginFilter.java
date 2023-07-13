package com.app.MovieApp.security.common.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import com.app.MovieApp.security.common.config.exception.AuthMethodNotSupportedException;
import com.app.MovieApp.security.login.dto.LoginRequestDto;
import com.app.MovieApp.security.login.dto.UserContext;

public class LoginFilter extends AbstractAuthenticationProcessingFilter {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	private final LoginAuthenticationSuccessHandler successHandler;

	private final LoginAuthenticationFailureHandler failureHandler;

	public LoginFilter(String url, LoginAuthenticationSuccessHandler successHandler,
			LoginAuthenticationFailureHandler failureHandler, AuthenticationManager authManager) {
		//super(url);
		super("/auth/v1/login");
		setAuthenticationManager(authManager);
		this.successHandler = successHandler;
		this.failureHandler = failureHandler;
	}


	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException, IOException, ServletException {
		if (!HttpMethod.POST.name().equals(req.getMethod())) {
			logger.error("Authentication Method Not Supported" + req.getMethod());
			throw new AuthMethodNotSupportedException("Authentication method not supported");
		}

		LoginRequestDto creds = null;
		String userName = req.getParameter("username");
		String password = req.getParameter("password");
		String accessType = req.getParameter("accessType");
		if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)) {
			logger.error("Username or Password not provided");
			throw new AuthenticationServiceException("Username or Password not provided");
		} else {
			creds = new LoginRequestDto();
			creds.setUserName(userName);
			//creds.setPassword(new String(Base64.decodeBase64(password)));
			 creds.setPassword(password);
			creds.setAccessType(accessType);
		}
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(creds.getUserName(),
				creds.getPassword());
		
		Authentication authentication = this.getAuthenticationManager().authenticate(token);

		return authentication;
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		successHandler.onAuthenticationSuccess(req, res, auth);
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		SecurityContextHolder.clearContext();
		failureHandler.onAuthenticationFailure(request, response, failed);
	}

}