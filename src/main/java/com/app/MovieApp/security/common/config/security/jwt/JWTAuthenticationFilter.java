package com.app.MovieApp.security.common.config.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.app.MovieApp.security.common.config.security.jwt.service.JWTTokenService;

/**
 * @author Sathish S
 *
 *         This class used to provide custom processing of authentication
 *         requests. If the api request comes from {/api/**} then this filter
 *         class will be invoked.
 */
public class JWTAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	private final AuthenticationFailureHandler failureHandler;

	private final JWTTokenService jwtTokenService;

	public JWTAuthenticationFilter(AuthenticationFailureHandler failureHandler, JWTTokenService jwtTokenService) {
		super("/api/**");
		this.jwtTokenService = jwtTokenService;
		this.failureHandler = failureHandler;
	}

	/**
	 * This method is used to De-serialization and basic validation of the
	 * incoming JSON payload.
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		String token = jwtTokenService.ExtractToken(request);
		Authentication auth = getAuthenticationManager().authenticate(new JwtAuthenticationToken(token));
		return auth;
	}

	/**
	 * This method is invoked while the authentication success.
	 */
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		SecurityContext context = SecurityContextHolder.createEmptyContext();
		context.setAuthentication(authResult);
		SecurityContextHolder.setContext(context);
		chain.doFilter(request, response);
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
}
