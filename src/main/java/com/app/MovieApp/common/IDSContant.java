package com.app.MovieApp.common;

public enum IDSContant {

	ACTIVE("Active"), USER("User"), SUCCESS("Success"), FAILED("Failed"), STATUS_ERROR("Error"), ACCESS_TOKEN("Access Token"),
	REFRESH_TOKEN("Refresh Token");

	private String value;

	private IDSContant(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
