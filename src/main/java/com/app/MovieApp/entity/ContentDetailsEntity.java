package com.app.MovieApp.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	
	@Column(name="youtube_link")
	private String link;
	
	@Column(name="imdb_rating")
	private String imdbRating;
	
	@Column(name="your_rating")
	private String yourRating;

	@Column(name="popularity")
	private String popularity;
	
	@Column(name="genre_category")
	private String genreCategory;
	
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "CONTENT_CREW_MAPPING" , joinColumns = {@JoinColumn(name="content_id")},inverseJoinColumns = {@JoinColumn(name="cast_id")})
	private Set<CastDetails> cast;
	
}
