package com.app.MovieApp.service;

import java.util.List;

import com.app.MovieApp.dto.CarsouelContentDto;
import com.app.MovieApp.dto.CategoryInformationDto;
import com.app.MovieApp.dto.InformationDetailsDto;
import com.app.MovieApp.dto.common.ResponseDto;

public interface MovieAppService {

	ResponseDto<List<CarsouelContentDto>> getCarsouelData();
	
	ResponseDto<List<CategoryInformationDto>> getHeaderDetails(String category);

	ResponseDto<InformationDetailsDto> getContentDetails(String name);

}
