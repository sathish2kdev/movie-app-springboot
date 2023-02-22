package com.app.MovieApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.MovieApp.entity.CategoryType;

@Repository
public interface CategoryTypeRepository extends JpaRepository<CategoryType, Long> {

	@Query(value = "SELECT CATEGORY_TYPE FROM movie_app.category_content where STATUS='Active'order by id ", nativeQuery = true)
	List<String> findCategory();
}
