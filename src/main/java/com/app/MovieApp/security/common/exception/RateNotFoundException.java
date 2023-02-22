package com.app.MovieApp.security.common.exception;

/**
 * @author Venkat;
 */
@SuppressWarnings("serial")
public class RateNotFoundException extends Exception {
	public RateNotFoundException() {

	}

	public RateNotFoundException(String message) {
		super(message);

	}

	public RateNotFoundException(Throwable cause) {
		super(cause);

	}

	public RateNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RateNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
