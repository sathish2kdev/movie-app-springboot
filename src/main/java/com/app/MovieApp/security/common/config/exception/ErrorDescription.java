/**
 * 
 */
package com.app.MovieApp.security.common.config.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * This is an pojo class for generating error descriptions
 * 
 * @author Praveen Kumar
 *
 */

@JsonRootName("Error")
public class ErrorDescription {

	@JsonProperty("Code")
	private String errorCode;

	@JsonProperty("Description")
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
