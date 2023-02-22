package com.app.MovieApp.security.common.exception;
/**
 * @author Venkat 
 */
public interface ApplicationError {
	public static final String LIST_OF_INSURER_EMPTY_DESC = "List of Insurer is Empty";
	public static final String LIST_OF_INSURER_EMPTY_CODE = "10001";
	public static final String PREMIUM_CALCULATION_FAILED_DESC = "Error While Calculating the Premium";	
	public static final String PREMIUM_CALCULATION_FAILED_CODE = "500";
	public static final String SERVER_SIDE_PROBLEM = "500";
	enum status{SUCCESS,FAILIURE};
}
