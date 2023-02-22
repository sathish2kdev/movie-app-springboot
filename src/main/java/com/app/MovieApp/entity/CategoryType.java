package com.app.MovieApp.entity;

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

import antlr.collections.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "CATEGORY_CONTENT")
public class CategoryType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "CATEGORY_TYPE", nullable = false, unique = true)
	private String categoryType;
	

	@Column(name = "STATUS")
	private String status;
	
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable(name = "CATEGORY_GENRE_MAPPING", joinColumns = {@JoinColumn(name="CATEGORY_ID")},inverseJoinColumns = {@JoinColumn(name="GENRE_ID")})
	private Set<GenreContentEntity> genre;
	
	
	
}
 