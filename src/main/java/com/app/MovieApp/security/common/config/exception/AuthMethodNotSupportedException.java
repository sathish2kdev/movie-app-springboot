package com.app.MovieApp.security.common.config.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * Thrown if an authentication mehtod is not supported.
 *
 * @author Praveen Kumar
 */
public class AuthMethodNotSupportedException extends AuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a <code>AuthMethodNotSupportedException</code> with the
	 * specified message.
	 *
	 * @param msg
	 *            the detail message
	 */
	public AuthMethodNotSupportedException(String msg) {
		super(msg);
	}

	/**
	 * Constructs a <code>AuthMethodNotSupportedException</code> with the
	 * specified message and root cause.
	 *
	 * @param msg
	 *            the detail message
	 * @param t
	 *            root cause
	 */
	public AuthMethodNotSupportedException(String msg, Throwable t) {
		super(msg, t);
	}
}
