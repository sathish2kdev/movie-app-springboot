package com.app.MovieApp.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.MovieApp.common.IDSContant;
import com.app.MovieApp.dto.CarsouelContentDto;
import com.app.MovieApp.dto.common.ResponseDto;
import com.app.MovieApp.entity.CarsouelContentEntity;
import com.app.MovieApp.repository.CarsouelContentRepository;
import com.app.MovieApp.repository.CategoryTypeRepository;
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
	public ResponseDto<List<String>> getHeaderDetails() {
		logger.info("Start of getHeaderDetails in getCarsouelData");
		ResponseDto<List<String>> response = new ResponseDto<>();
		try {
			List<String> listOfCategory = categoryTypeRepository.findCategory();
			response.setResponseData(listOfCategory);
			response.setCode(IDSContant.SUCCESS.getValue());
		} catch (Exception e) {
			response.setCode(IDSContant.FAILED.getValue());
			response.setMessage(e.getMessage());
			logger.info("Error In MovieAppServiceImpl, getHeaderDetails =>  ", e);
			e.printStackTrace();
		}
		logger.info("End of getHeaderDetails in getCarsouelData");
		return null;
	}
}
