/**
 * 
 */
package com.app.MovieApp.security.common.exception;

/**
 * @author Praveen Kumar
 *
 */

public class ErrorDescription {
	private String errorCode;
	private String errorDescription;
	public String getErrorCode() {
		return errorCode;
	}
	public String getErrorDescription() {
		return errorDescription;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
	
}
