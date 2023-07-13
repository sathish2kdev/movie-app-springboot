package com.app.MovieApp.security.common.config.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.app.MovieApp.common.IDSContant;
import com.app.MovieApp.entity.UserEntity;
import com.app.MovieApp.entity.UserRolesEntity;
import com.app.MovieApp.login.service.LoginService;
import com.app.MovieApp.security.common.config.security.jwt.service.JWTTokenService;
import com.app.MovieApp.security.login.dto.UserContext;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author Sathish S
 */
@Component
public class LoginAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Resource
	JWTTokenService jWTTokenService;

	@Resource
	LoginService loginService;

	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
			throws IOException, ServletException {
		String accessToken = null;
		String refreshToken = null;
		UserEntity user = loginService.checkByUsernameOrEmail(auth.getName());

		UsernamePasswordAuthenticationToken authentication = null;
		UserContext userContext = new UserContext();
		userContext.setUserName(user.getUserName());
		userContext.setEmailId(user.getEmailId());
		userContext.setFirstName(userContext.getFirstName());
		userContext.setLastName(userContext.getLastName());

		// SET USER ROLES
		List<GrantedAuthority> authorities = null;
		if (user.getUserRoles() != null) {
			authorities = new ArrayList<>();
			Optional<UserRolesEntity> userAuth = user.getUserRoles().stream().findFirst();
			if (userAuth != null)
				authorities.add(new SimpleGrantedAuthority(userAuth.get().getRoleCode()));
		}
		if (authorities.isEmpty() || authorities == null) {
			throw new InsufficientAuthenticationException("User has no roles assigned");
		}
		List<String> userRole = authorities.stream().map(x -> x.getAuthority()).collect(Collectors.toList());
		userContext.setAuthorities(authorities);
		userContext.setUserRoles(userRole);

		authentication = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword(), authorities);
		authentication.setDetails(userContext);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// AFTER SUCCESSFULL LOGIN CREATE TOKEN
		accessToken = jWTTokenService.createAccessJwtToken(userContext);
		refreshToken = jWTTokenService.createRefreshToken(userContext);
		Map<String, Object> tokenMap = new HashMap<String, Object>();
		String role = userContext.getUserRoles().get(0);
		if (role != null) {
			tokenMap.put("token", accessToken);
			tokenMap.put("refreshToken", refreshToken);
		} else {
			tokenMap.put("userDetails", userContext);
		}
		tokenMap.put("loginStatus",IDSContant.SUCCESS.getValue());
		userContext.setAuthToken(accessToken);
		userContext.setRefreshToken(refreshToken);
		response.setStatus(HttpStatus.OK.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setHeader("auth-token", accessToken);

//		response.setHeader("refresh-token", refreshToken);
		new ObjectMapper().writeValue(response.getWriter(), tokenMap);
		clearAuthenticationAttributes(request);
	}

	protected final void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}

}
