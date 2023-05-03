package com.app.MovieApp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class InformationDetailsDto {

	private String applicationName;

	private String categoryType;

	private String genre;

	private String yearOfReleasing;

	private String language;

	private String description;

	private String status;
	
	private String imgPath;
	
	private String link;
	
	private String imdbRating;

	private String yourRating;

	private String popularity;
	
	private String genreCategory;
	
}
