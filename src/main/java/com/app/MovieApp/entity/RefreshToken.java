package com.app.MovieApp.entity;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "REFRESH_TOKEN")
public class RefreshToken {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "USER_CONTENT")
	private String userContent;

	@Column(name = "TOKEN", nullable = false, unique = true)
	private String token;
	
	@Column(name="EXPIRY_DATE",nullable = false)
	private Instant expiryDate;
}
