package com.app.MovieApp.security.common.exception;

/**
 * @author Venkat;
 */
@SuppressWarnings("serial")
public class ApplicationException extends Exception {
	public ApplicationException() {

	}

	public ApplicationException(String message) {
		super(message);

	}

	public ApplicationException(Throwable cause) {
		super(cause);

	}

	public ApplicationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ApplicationException(String message, Throwable cause) {
		super(message, cause);
	}

}
