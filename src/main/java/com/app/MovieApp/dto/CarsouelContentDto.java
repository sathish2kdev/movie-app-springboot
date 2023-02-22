package com.app.MovieApp.dto;

import javax.persistence.Column;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarsouelContentDto {

	private String contentName;

	private String contentpath;

	private String language;

	private String genre;

	private String year;

	private String description;

}
