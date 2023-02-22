package com.app.MovieApp.security.common.config.security.jwt.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.app.MovieApp.security.login.dto.UserContext;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

@Service
public interface JWTTokenService {

	String createAccessJwtToken(UserContext userContext);

	String createRefreshToken(UserContext userContext);

	String ExtractToken(HttpServletRequest request);

	UserContext parseClaims(String accessToken);

	Jws<Claims> decodeJWT(String token);
}
