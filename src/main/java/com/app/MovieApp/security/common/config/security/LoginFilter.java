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

/**
 * @author Praveen
 *
 *         This class used to provide custom processing of authentication
 *         requests. If the api request comes from {/login} then this filter
 *         class will be invoked.
 */
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

	/**
	 * This method is used to De-serialization and basic validation of
	 * 
	 * the incoming JSON payload.
	 */
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
		/*
		 * UsernamePasswordAuthenticationToken token = new
		 * UsernamePasswordAuthenticationToken(creds.getUserName(),
		 * Base64.decode(creds.getPassword()));
		 */

		/**
		 * Here check the role of user
		 */
		Authentication authentication = this.getAuthenticationManager().authenticate(token);
//		UserContext userContext = (UserContext) authentication.getDetails();
		//if (!creds.getAccessType().equals(userContext.getAccessType()))
//		if (!creds.getAccessType().equals("user")) {
//			logger.error("This user does not have permission to access ");
//			throw new AuthenticationServiceException("This user does not have permission to access ");
//		}
		return authentication;
	}

	/**
	 * This method is invoked while the authentication success.
	 */
	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		successHandler.onAuthenticationSuccess(req, res, auth);
	}

	/**
	 * This method is invoked while the authentication unsuccessful.
	 */
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		SecurityContextHolder.clearContext();
		failureHandler.onAuthenticationFailure(request, response, failed);
	}

//	public static void main(String[] args) {
//		// Encode data on your side using BASE64
//		byte[] bytesEncoded = Base64.encodeBase64("1".getBytes());
//		System.out.println("encoded value is " + new String(bytesEncoded));
//
//		// Decode data on other side, by processing encoded data
//		byte[] valueDecoded = Base64.decodeBase64(bytesEncoded);
//		System.out.println("Decoded value is " + new String(valueDecoded));
//	}
}