package com.app.MovieApp.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="cast_details")
public class CastDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="cast_name")
	private String castName;
	
	@Column(name="cast_indentity")
	private String castIndentity;
	
	   @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
	private PersonDetailsEntity personDetails;

//	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	@JoinTable(name = "CONTENT_CREW_MAPPING" , joinColumns = {@JoinColumn(name="")},inverseJoinColumns = {@JoinColumn(name="")})
//	private Set<CastDetails> cast;
}
