/**
 * 
 */
package com.app.MovieApp.security.login.dto;
import java.io.Serializable;

/**
 * @author SURESH
 *
 */


public class LoginHistoryDto implements Serializable{

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private String userId;
	private String userName;
	private String hostName;
	private String ipAddress;
	private String status;
	private String loginTime;
	private String browser;
	private String token;
	
	
	
	
	

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	public String getToken() 
	{
		return token;
	}
	public void setToken(String token) 
	{
		this.token = token;
	}
	
}
