package com.app.MovieApp.security.common.config.security.jwt.service.impl;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import com.app.MovieApp.entity.RefreshToken;
import com.app.MovieApp.repository.RefreshTokenRepository;
import com.app.MovieApp.security.common.config.exception.JwtExpiredTokenException;
import com.app.MovieApp.security.common.config.security.jwt.service.JWTTokenService;
import com.app.MovieApp.security.login.dto.UserContext;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Service
@PropertySource({ "classpath:configuration/JWTConfig.properties" })
public class JWTTokenServiceImpl implements JWTTokenService {

	private static final String TOKEN_ISSUER = "TokenIssuer";
	private static final String TOKENEXPIRATIONTIME = "TokenExpirationTime";
	private static final String TOKENSIGNINGKEY = "TokenSigningKey";
	private static final String JWT_TOKEN_HEADER_PARAM = "JWT_TOKEN_HEADER_PARAM";
	private static final String HEADER_PREFIX = "Bearer ";
	private static final String REFRESHTOKENTIME = "REFRESHTOKENTIME";

//	@Resource
//	LoginService loginService;

	@Resource
	private Environment env;

	@Autowired
	RefreshTokenRepository refreshTokenRepository;

	@Override
	public String createAccessJwtToken(UserContext userContext) {
		if (StringUtils.isBlank(userContext.getUserName()))
			throw new IllegalArgumentException("Cannot create JWT Token without username");
		if (userContext.getAuthorities() == null || userContext.getAuthorities().isEmpty())
			throw new IllegalArgumentException("User doesn't have any privileges");
		Claims claims = Jwts.claims().setSubject(userContext.getUserId());
		claims.put("scopes", userContext.getAuthorities().stream().map(s -> s.toString()).collect(Collectors.toList()));
		claims.put("UserContext", userContext);

		// claims.put("AgentDetails", userContext.getResponseDto());
		LocalDateTime currentTime = LocalDateTime.now();
		return Jwts.builder().setClaims(claims).setIssuer(env.getProperty(TOKEN_ISSUER))
				.setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()))
				.setExpiration(Date.from(currentTime.plusMinutes(Integer.parseInt(env.getProperty(TOKENEXPIRATIONTIME)))
						.atZone(ZoneId.systemDefault()).toInstant()))
				.signWith(SignatureAlgorithm.HS512, env.getProperty(TOKENSIGNINGKEY)).compact();
	}

	@Override
	public String ExtractToken(HttpServletRequest request) {
		// HttpHeaders header = request.getHeaderNames();
		String tokenPayload = request.getHeader(env.getProperty(JWT_TOKEN_HEADER_PARAM));
		return extract(tokenPayload);
	}

	private String extract(String header) {
		if (StringUtils.isBlank(header)) {
			throw new AuthenticationServiceException("Authorization header cannot be blank!");
		}

		if (header.length() < HEADER_PREFIX.length()) {
			throw new AuthenticationServiceException("Invalid authorization header size.");
		}
		return header.substring(HEADER_PREFIX.length(), header.length());
	}

	@Override
	public UserContext parseClaims(String accessToken) {
		UserContext userContext = null;
		try {
			Jws<Claims> jwsClaims = Jwts.parser().setSigningKey(env.getProperty(TOKENSIGNINGKEY))
					.parseClaimsJws(accessToken);
			if (jwsClaims == null) {
				throw new BadCredentialsException("Invalid JWT token. ");
			} else {
//				/*String userName = jwsClaims.getBody().getSubject();
//				if (true) 
//				{*/
//				/*List<String> scopes = jwsClaims.getBody().get("scopes", List.class);
//					List<GrantedAuthority> authorities = scopes.stream()
//							.map(authority -> new SimpleGrantedAuthority(authority)).collect(Collectors.toList());*/
				userContext = new ObjectMapper().convertValue(jwsClaims.getBody().get("UserContext"),
						UserContext.class);
//				/*} else 
//				{
//					throw new JwtExpiredTokenException("JWT Token expired");
//				}*/
			}
		} catch (UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException ex) {
			throw new BadCredentialsException("Invalid JWT token. ", ex);
		} catch (ExpiredJwtException expiredEx) {
			throw new JwtExpiredTokenException("JWT Token expired", expiredEx);
		}
		return userContext;
	}

	public String createRefreshToken(String token) {
		Jws<Claims> jwsClaims = decodeJWT(token);
		UserContext userContext = new ObjectMapper().convertValue(jwsClaims.getBody().get("UserContext"),
				UserContext.class);
		return createRefreshToken(userContext);
	}

	@Override
	public String createRefreshToken(UserContext userContext) {
		if (StringUtils.isBlank(userContext.getUserName())) {
			throw new IllegalStateException("Cannot Create JWT Token without username");
		}
		ObjectMapper objectmapper = new ObjectMapper();
		RefreshToken refresh = new RefreshToken();
		try {
			refresh.setUserContent(objectmapper.writeValueAsString(userContext));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		refresh.setExpiryDate(Instant.now().plusMillis(Long.valueOf(env.getProperty(REFRESHTOKENTIME))));
		refresh.setToken(UUID.randomUUID().toString());
		refresh = refreshTokenRepository.save(refresh);
		return refresh.getToken();
	}

	@Override
	public Jws<Claims> decodeJWT(String token) {
		Jws<Claims> jwsClaims = Jwts.parser().setSigningKey(env.getProperty(TOKENSIGNINGKEY)).parseClaimsJws(token);
		return jwsClaims;
	}
}
