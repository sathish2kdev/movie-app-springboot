package com.app.MovieApp.security.common.config.security.constants;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ErrorCode {
	GLOBAL(2),

	AUTHENTICATION(10), JWT_TOKEN_EXPIRED(11);

	private int errorCode;

	private ErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	@JsonValue
	public int getErrorCode() {
		return errorCode;
	}
}
