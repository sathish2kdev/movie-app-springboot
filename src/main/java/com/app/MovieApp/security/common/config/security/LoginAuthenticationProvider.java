
package com.app.MovieApp.security.common.config.security;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.persistence.PersistenceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.www.NonceExpiredException;
import org.springframework.stereotype.Component;

//import com.app.MovieApp.entity.UserAuthoritiesEntity;
import com.app.MovieApp.entity.UserEntity;
import com.app.MovieApp.entity.UserRolesEntity;
//import com.app.MovieApp.entity.UserProfileEntity;
import com.app.MovieApp.repository.LoginHistoryRepository;
//import com.app.MovieApp.repository.UserProfileRepository;

import com.app.MovieApp.security.constants.SecurityConstants;
import com.app.MovieApp.security.encryptdecrypt.FaSoftwareSolutinonEncryptionDecryption;
import com.app.MovieApp.security.login.dto.UserContext;

//import com.app.MovieApp.security.login.entity.UserAuthority;
import com.app.MovieApp.login.service.LoginService;

/***
 * @author Praveen Kumar Responsibility of the LoginAuthenticationProvider class
 *         is to: 1. Verify user credentials against database which holds the
 *         user data. 2. If user name and password do not match the record in
 *         the database authentication exception is thrown otherwise Create
 *         UserContext and populate it with user data you need. 3. Upon
 *         successful authentication delegate creation of JWT Token
 */
@Component
public class LoginAuthenticationProvider implements AuthenticationProvider {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	BCryptPasswordEncoder encoder;

	@Resource
	LoginService loginService;

	@Resource
	LoginHistoryRepository loginHistoryRepository;
	
	@Resource
	AuthenticationManagerBuilder authenticationManagerBuilder;
	

	@Resource
	CustomUserDetailsService customUserDetailsService;
	
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	FaSoftwareSolutinonEncryptionDecryption fasencryptdecrypt = new FaSoftwareSolutinonEncryptionDecryption();

	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		
		UsernamePasswordAuthenticationToken authentication = null;
		try {
			
			
			String username = (String) auth.getPrincipal();
			String password = (String) auth.getCredentials();
		
			
			UserEntity user = loginService.checkByUsernameOrEmail(username);
			/**
			 * Check user is exist or not
			 */
			
			System.out.println(encoder.encode(password));
			
			System.out.println(encoder.encode(password).equals(user.getPassword()));
			
			
			if (user == null) {
				logger.error("LoginAuthenticationProvider -- authenticate -- UserId not found in DataBase " + username);
				throw new UsernameNotFoundException("User not found: " + username);
			}
			/**
			 * Check User Password.
			 */
			/*
			 * String encryptPassword = fasencryptdecrypt.encryptionText(password,
			 * SecurityConstants.Encrypt_Decrypt_key);
			 */
			if (!password.equals(user.getPassword())) {
				logger.error(
						"LoginAuthenticationProvider -- authenticate -- Authentication Failed. Username or Password not valid---->Userid "
								+ username);
				throw new BadCredentialsException("Authentication Failed. Username or Password not valid.");
			}

			/**
			 * 
			 * Check Password in db based on Expiry Details
			 **/

			/**
			 * Check the user having authority
			 */
			String branchName = null;
			// loginService.getBranch(user.getBranchCode());
//			if (branchName == null) {
//				logger.error("LoginAuthenticationProvider -- authenticate -- User has no roles assigned " + username);
//				throw new InsufficientAuthenticationException("User has no roles assigned");
//			}

			/*
			 * Hided Due To No Completed UserProfileEntity userProfileEntity =
			 * userProfileRepository.getUserProfileByUserId(user.getUsername());
			 * if(userProfileEntity != null && userProfileEntity.getLastLoginSuccess() !=
			 * null) { int inActivePeriod = 90; Date lastLoginDate =
			 * userProfileEntity.getLastLoginSuccess(); Calendar newDate =
			 * Calendar.getInstance(); Calendar oldDate = Calendar.getInstance();
			 * newDate.setTime(new Date()); oldDate.setTime(lastLoginDate);
			 * newDate.add(Calendar.DAY_OF_YEAR, -inActivePeriod);
			 * if(newDate.after(oldDate)) {
			 * logger.error("LoginAuthenticationProvider -- User account has Expired. " +
			 * username); throw new NonceExpiredException("User account has Expired.");
			 * }else { loginService.saveUserProfileExisting(userProfileEntity); } }else {
			 * loginService.saveUserProfileFirstTime(user.getUsername()); }
			 */
			/**
			 * Check the user having access
			 */

			List<GrantedAuthority> authorities = null;
//			if (user.getUserRoles() != null) {
//				authorities = new ArrayList<>();
//				Optional<UserRolesEntity> userAuth =user.getUserRoles().stream().findFirst();
//				if(userAuth != null)
//					authorities.add(new SimpleGrantedAuthority(userAuth.get().getRoleCode()));
//			}
//			if (authorities.isEmpty() || authorities == null) {
//				throw new InsufficientAuthenticationException("User has no roles assigned");
//			}
//			List<String> userRole = authorities.stream().map(x -> x.getAuthority()).collect(Collectors.toList());

			/*
			 * Hided Due To No Completed String lastLoginTime
			 * =loginHistoryRepository.getUserLoginTime(username);
			 */

			UserContext userContext = new UserContext();
			userContext.setAuthorities(authorities);

			authentication = new UsernamePasswordAuthenticationToken(username, password, authorities);
			authentication.setDetails(userContext);
			SecurityContextHolder.getContext().setAuthentication(authentication);

		} catch (PersistenceException e) {
			logger.error(SecurityConstants.PERSISTANCE_MESSAGEINLOG + e);
			logger.info("Error in LoginAuthenticationProvider ", e);
		} catch (NonceExpiredException e) {
			logger.error("User or Password Details Expired", e);
			throw new NonceExpiredException("User or Password Details Expired");
		} catch (Exception e) {
			logger.info("Error in LoginAuthenticationProvider ", e);
		}
		return authentication;
	}

	public boolean supports(Class<?> auth) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(auth));
	}

}
