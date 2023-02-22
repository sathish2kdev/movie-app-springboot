/**
 * 
 */
package com.app.MovieApp.security.constants;

/**
 * @author Suresh
 *
 */
public interface SecurityConstants {

	static String Encrypt_Decrypt_key = "FAS";

	static String STATUS_ACTIVE = "ACTIVE";

	static String STATUS_INACTIVE = "INACTIVE";

	static Integer USER_EXPIRY_DAYS = 35;

	static String NULL_MESSAGE = "PLEASE FILL ALL THE VALUES";
	static String DATE_MESSAGE = "INCORRECT DATE FORMAT";
	static String NULL_FROMUSER = "NULL VALUE FROM USER IS NOT ACCEPTED";
	static String PERSISTANCE_MESSAGEINLOG = "DUPLICATE VALUE OR NULL VALUE NOT ALLOWED T0 SAVE";

	static String INTERMEDIARY = "I";
	static String EMPLOYEE = "E";
	String[] pathToSkip = { "/dashboard/v1/getCarsoelDetails" };

}
