package com.app.MovieApp.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name="LoginHistoryEntity")
@Table(name="LOGIN_HISTORY")
public class LoginHistoryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="USER_NAME")
	private String userName;
	
	@Column(name="HOST_NAME")
	private String hostName;
	
	@Column(name="IP_ADDRESS")
	private String ipAddress;
	
	@Column(name="IS_LOGIN_SUCCESS")
	private String isLoginSuccess;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LOGIN_TIME")
	private Date loginTime; 
	
	@Column(name="BROWSER")
	private String browser;
	
	@Column(name="TOKEN")
	private String token;
	
}
