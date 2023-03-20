package com.app.MovieApp.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryInformationDto {

	private String category;
	private List<InformationDetailsDto> information = new ArrayList<InformationDetailsDto>();


}
