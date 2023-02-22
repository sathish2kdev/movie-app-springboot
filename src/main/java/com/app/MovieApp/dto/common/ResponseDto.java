package com.app.MovieApp.dto.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDto<T> {

	private String status;
	private String code;
	private String message;
	private T responseData;

}
