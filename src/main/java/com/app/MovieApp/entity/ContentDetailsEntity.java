package com.app.MovieApp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

//@Getter
//@Setter
//@Entity
//@Table(name="video_information_table")
public class ContentDetailsEntity {

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//	
//	@Column(name="CATEGORY_TYPE")
//	private String categoryType;
//	
//	@Column(name="CONTENT_NAME")
//	private String contentName;
//	
//	@Column(name="IMAGE_PATH")
//	private String imagePath;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
//	id, application_name, category_type, genre, year_of_release, language, description, status, creation_date, updation_date, img_path
	@Column(name="application_name")
	private String applicationName;
	
	@Column(name="category_type")
	private String categoryType;
	
	private String genre;
}
