package com.app.MovieApp.refreshToken.controller;

import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.MovieApp.common.ErrorConstant;
import com.app.MovieApp.common.IDSContant;
import com.app.MovieApp.dto.RefreshTokenRequest;
import com.app.MovieApp.dto.common.ResponseDto;
import com.app.MovieApp.entity.RefreshToken;
import com.app.MovieApp.entity.UserEntity;
import com.app.MovieApp.repository.RefreshTokenRepository;
import com.app.MovieApp.repository.UserRepository;
import com.app.MovieApp.security.common.config.security.jwt.service.JWTTokenService;
import com.app.MovieApp.security.login.dto.UserContext;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/authToken")
public class RefreshTokenController {

	@Autowired
	RefreshTokenRepository refreshTokenRepository;

	@Autowired
	JWTTokenService jwtTokenService;

	@Autowired
	UserRepository userRepository;

	@PostMapping("/v1/refreshToken")
	public ResponseEntity<?> getRefreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest)
			throws JsonMappingException, JsonProcessingException {

		ResponseDto<Map<String, Object>> response = new ResponseDto<>();

		String refreshToken = refreshTokenRequest.getToken();
		RefreshToken refreshTokenEntity = refreshTokenRepository.findByToken(refreshToken);
		if (refreshTokenEntity != null) {
			if (refreshTokenEntity.getExpiryDate().compareTo(Instant.now()) < 0) {
				refreshTokenRepository.delete(refreshTokenEntity);
				response.setStatus(IDSContant.STATUS_ERROR.getValue());
				response.setMessage(ErrorConstant.REFRESH_TOKEN_EXPIRED.getValue());
			} else {
				ObjectMapper objectMapper = new ObjectMapper();
				String userContent = refreshTokenEntity.getUserContent();
				UserContext userContextDto = objectMapper.readValue(userContent, UserContext.class);
				List<GrantedAuthority> userAuthority = getAuthoity(
						userRepository.findByUsernameOrEmail(userContextDto.getUserName()));
				userContextDto.setAuthorities(userAuthority);
				String accessToken = jwtTokenService.createAccessJwtToken(userContextDto);
				Map<String, Object> tokenMap = new LinkedHashMap<>();
				tokenMap.put(IDSContant.ACCESS_TOKEN.getValue(), accessToken);
				tokenMap.put(IDSContant.REFRESH_TOKEN.getValue(), refreshTokenEntity.getToken());
				response.setResponseData(tokenMap);
			}
		} else {
			response.setStatus(IDSContant.STATUS_ERROR.getValue());
			response.setMessage(ErrorConstant.INVALID_TOKEN.getValue());
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	private List<GrantedAuthority> getAuthoity(UserEntity userEntity) {
		List<GrantedAuthority> autorityList = new ArrayList<>();
		if (userEntity != null && userEntity.getUserRoles() != null)
			userEntity.getUserRoles().forEach(p -> autorityList.add(new SimpleGrantedAuthority(p.getRoleCode())));
		return autorityList;
	}

}
