package com.app.MovieApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.MovieApp.entity.ContentDetailsEntity;

@Repository
public interface ContentDetailsRepository extends JpaRepository<ContentDetailsEntity,Long>{

	List<ContentDetailsEntity> findByCategoryType(String categoryType);
	
	List<ContentDetailsEntity> findByGenre(String genre);

}
