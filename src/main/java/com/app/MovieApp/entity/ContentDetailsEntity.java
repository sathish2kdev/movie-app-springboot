package com.app.MovieApp.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "video_information_table")
public class ContentDetailsEntity  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "application_name")
	private String applicationName;

	@Column(name = "category_type")
	private String categoryType;

	@Column(name = "genre")
	private String genre;

	@Column(name = "year_of_release")
	private String yearOfReleasing;

	@Column(name = "language")
	private String language;

	@Column(name = "description")
	private String description;

	@Column(name = "status")
	private String status;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="creation_date")
	private Date creationDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updation_date")
	private Date updationDate;

	
	@Column(name="img_path")
	private String imgPath;
}
