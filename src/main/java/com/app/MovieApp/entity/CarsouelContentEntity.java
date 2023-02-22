package com.app.MovieApp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "carsouel_content")
@Entity(name = "CarsouelContentEntity")
public class CarsouelContentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "CONTENT_NAME")
	private String contentName;

	@Column(name = "CONTENT_PATH")
	private String contentpath;

	@Column(name = "LANGUAGE")
	private String language;

	@Column(name = "GENRE")
	private String genre;

	@Column(name = "YEAR")
	private String year;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "STATUS")
	private String status;
}
