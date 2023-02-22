package com.app.MovieApp.security.login.dto;
import java.io.Serializable;

public class changepassworddto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	

	private String newPswrd;
	
	private String confirmPswrd;
	
	private String userName; 
	 
	private String oldpassword;

	public String getNewPswrd() {
		return newPswrd;
	}

	public void setNewPswrd(String newPswrd) {
		this.newPswrd = newPswrd;
	}

	public String getConfirmPswrd() {
		return confirmPswrd;
	}

	public void setConfirmPswrd(String confirmPswrd) {
		this.confirmPswrd = confirmPswrd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOldpassword() {
		return oldpassword;
	}

	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

}
