package com.app.MovieApp.security.common.config.exception;

import org.springframework.security.core.AuthenticationException;


public class AuthMethodNotSupportedException extends AuthenticationException {

	
	private static final long serialVersionUID = 1L;

	
	public AuthMethodNotSupportedException(String msg) {
		super(msg);
	}

	public AuthMethodNotSupportedException(String msg, Throwable t) {
		super(msg, t);
	}
}
