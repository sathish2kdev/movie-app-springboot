package com.app.MovieApp.security.login.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;


public class LoginRequestDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("username")
	private String userName;

	@JsonProperty("passWord")
	private String password;

	@JsonProperty("accessType")
	private String accessType;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccessType() {
		return accessType;
	}

	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}

	

}
