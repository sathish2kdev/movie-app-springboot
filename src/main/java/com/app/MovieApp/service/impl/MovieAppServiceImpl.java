package com.app.MovieApp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
import com.app.MovieApp.dto.InformationMainDto;
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
	public ResponseDto<List<CategoryInformationDto>> getHeaderDetails(String categoryType) {
		logger.info("Start of getHeaderDetails in getCarsouelData");
		ResponseDto<List<CategoryInformationDto>> response = new ResponseDto<>();
		try {
			List<CategoryInformationDto> listCategoryInformation = new ArrayList<CategoryInformationDto>();
			if(Objects.nonNull(categoryType) && !categoryType.isEmpty()) {
				CategoryInformationDto categoryInformationDto = new CategoryInformationDto();
				categoryInformationDto.setCategory(categoryType);
				List<InformationMainDto> informationList = getCategoryWithDetails(categoryType);
				categoryInformationDto.setInformation(informationList);
				categoryInformationDto.setHiddenCategory("More Like This");
				listCategoryInformation.add(categoryInformationDto);
			}else {
				List<String> listOfCategory = categoryTypeRepository.findCategory();
				for(String category : listOfCategory) {
					CategoryInformationDto categoryInformationDto = new CategoryInformationDto();
					categoryInformationDto.setCategory(category);
					List<InformationMainDto> informationList = getCategoryWithDetails(category);
					categoryInformationDto.setInformation(informationList);
					listCategoryInformation.add(categoryInformationDto);
				}
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

	public List<InformationMainDto> getCategoryWithDetails(String categoryType) {
		List<InformationMainDto> informationList = null;
		try {
//			List<ContentDetailsEntity> contentDetails = contentDetailsRepository.findByCategoryType(categoryType);
			List<ContentDetailsEntity> contentDetails = contentDetailsRepository.findByGenre(categoryType);
			
			
			informationList = contentDetails.stream().map(p -> modelMapper.map(p, InformationMainDto.class)).collect(Collectors.toList()); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return informationList;
	}
	
	@Override
	public ResponseDto<InformationDetailsDto> getContentDetails(String name) {
		logger.info("Start of getContentDetails in getCarsouelData");
		ResponseDto responseDto = new ResponseDto<>();
		try {
			ContentDetailsEntity contentDetailsEntity = contentDetailsRepository.findByApplicationName(name);
			InformationDetailsDto informationDetailsDt = modelMapper.map(contentDetailsEntity, InformationDetailsDto.class);
			responseDto.setResponseData(informationDetailsDt);
			responseDto.setCode(IDSContant.SUCCESS.getValue());
		} catch (Exception e) {
			responseDto.setCode(IDSContant.FAILED.getValue());
			responseDto.setMessage(e.getMessage());
			logger.info("Error In MovieAppServiceImpl, getContentDetails =>  ", e);
			e.printStackTrace();
		}
		return responseDto;
	}
}
