package com.app.MovieApp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.MovieApp.common.IDSContant;
import com.app.MovieApp.dto.CarsouelContentDto;
import com.app.MovieApp.dto.CategoryInformationDto;
import com.app.MovieApp.dto.InformationDetailsDto;
import com.app.MovieApp.dto.common.ResponseDto;
import com.app.MovieApp.entity.CarsouelContentEntity;
import com.app.MovieApp.entity.ContentDetailsEntity;
import com.app.MovieApp.repository.CarsouelContentRepository;
import com.app.MovieApp.repository.CategoryTypeRepository;
import com.app.MovieApp.repository.ContentDetailsRepository;
import com.app.MovieApp.service.MovieAppService;

@Service
public class MovieAppServiceImpl implements MovieAppService {

	Logger logger = LoggerFactory.getLogger(MovieAppServiceImpl.class);

	@Autowired
	CarsouelContentRepository carsouelContentRepository;

	@Autowired
	CategoryTypeRepository categoryTypeRepository;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	ContentDetailsRepository contentDetailsRepository;

	@Override
	public ResponseDto<List<CarsouelContentDto>> getCarsouelData() {
		logger.info("Start of MovieAppServiceImpl in getCarsouelData");
		ResponseDto<List<CarsouelContentDto>> response = new ResponseDto<>();
		try {

			List<CarsouelContentEntity> carsouelContentList = carsouelContentRepository.findByActiveStatus();
			List<CarsouelContentDto> carsouelContentDtoList = carsouelContentList.stream()
					.map(p -> modelMapper.map(p, CarsouelContentDto.class)).collect(Collectors.toList());
			response.setResponseData(carsouelContentDtoList);
			response.setCode(IDSContant.SUCCESS.getValue());
		} catch (Exception e) {
			response.setCode(IDSContant.FAILED.getValue());
			response.setMessage(e.getMessage());
			logger.info("Error In MovieAppServiceImpl, getCarsouelData =>  ", e);
			e.printStackTrace();
		}
		logger.info("End of MovieAppServiceImpl in getCarsouelData");
		return response;
	}

	@Override
	public ResponseDto<List<CategoryInformationDto>> getHeaderDetails() {
		logger.info("Start of getHeaderDetails in getCarsouelData");
		ResponseDto<List<CategoryInformationDto>> response = new ResponseDto<>();
		try {
			List<CategoryInformationDto> listCategoryInformation = new ArrayList<CategoryInformationDto>();
			List<String> listOfCategory = categoryTypeRepository.findCategory();
			for(String category : listOfCategory) {
				CategoryInformationDto categoryInformationDto = new CategoryInformationDto();
				categoryInformationDto.setCategory(category);
				List<InformationDetailsDto> informationList = getCategoryWithDetails(category);
				categoryInformationDto.setInformation(informationList);
				listCategoryInformation.add(categoryInformationDto);
			}
			response.setResponseData(listCategoryInformation);
			response.setCode(IDSContant.SUCCESS.getValue());
		} catch (Exception e) {
			response.setCode(IDSContant.FAILED.getValue());
			response.setMessage(e.getMessage());
			logger.info("Error In MovieAppServiceImpl, getHeaderDetails =>  ", e);
			e.printStackTrace();
		}
		logger.info("End of getHeaderDetails in getCarsouelData");
		return response;
	}

	public List<InformationDetailsDto> getCategoryWithDetails(String categoryType) {
		List<InformationDetailsDto> informationList = null;
		try {
//			List<ContentDetailsEntity> contentDetails = contentDetailsRepository.findByCategoryType(categoryType);
			List<ContentDetailsEntity> contentDetails = contentDetailsRepository.findByGenre(categoryType);
			
			
			informationList = contentDetails.stream().map(p -> modelMapper.map(p, InformationDetailsDto.class)).collect(Collectors.toList()); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return informationList;
	}
}
