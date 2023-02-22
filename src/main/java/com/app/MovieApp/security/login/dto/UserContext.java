package com.app.MovieApp.security.login.dto;

import java.io.Serializable;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserContext implements Serializable {

	private static final long serialVersionUID = 1L;


	@JsonProperty("userId")
	private String userId;

	@JsonProperty("Username")
	private String userName;

	@JsonIgnore
	private List<GrantedAuthority> authorities;

	@JsonProperty("accessType")
	private String accessType;

	@JsonProperty("authToken")
	private String authToken;

	@JsonProperty("userRoles")
	private List<String> userRoles;

	@JsonProperty("dob")
	private String dob;

	@JsonProperty("emailId")
	private String emailId;

	@JsonProperty("firstName")
	private String firstName;

	@JsonProperty("middleName")
	private String middleName;

	@JsonProperty("lastName")
	private String lastName;

	@JsonProperty("refreshToken")
	private String refreshToken;


}
