package com.app.MovieApp.security.common.config.security.dto;

import java.util.Date;

import org.springframework.http.HttpStatus;

import com.app.MovieApp.security.common.config.security.constants.ErrorCode;
/**
 * @author Praveen
 *
 */

public class ErrorResponse {

	private final HttpStatus status;

	private final String message;

	private final ErrorCode errorCode;

	private final Date timestamp;

	public ErrorResponse(final String message, final ErrorCode errorCode, HttpStatus status) {
		this.message = message;
		this.errorCode = errorCode;
		this.status = status;
		this.timestamp = new java.util.Date();
	}

	public Integer getStatus() {
		return status.value();
	}

	public String getMessage() {
		return message;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public Date getTimestamp() {
		return timestamp;
	}
}
