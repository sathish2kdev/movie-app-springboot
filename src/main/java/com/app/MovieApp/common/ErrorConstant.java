package com.app.MovieApp.common;

public enum ErrorConstant {

	TOKEN_EXPIRED(""),REFRESH_TOKEN_EXPIRED("Refresh Token Expired"),INVALID_TOKEN("Invalid Token");

	private String value = null;
	private ErrorConstant(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
}
