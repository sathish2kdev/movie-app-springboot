package com.app.MovieApp.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryInformationDto {

	private String category;
	private String hiddenCategory;
	private List<InformationMainDto> information = new ArrayList<InformationMainDto>();

}
