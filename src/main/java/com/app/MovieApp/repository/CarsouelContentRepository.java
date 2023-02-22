package com.app.MovieApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.MovieApp.entity.CarsouelContentEntity;

@Repository
public interface CarsouelContentRepository extends JpaRepository<CarsouelContentEntity, Long> {

	@Query(value = "SELECT * FROM movie_app.carsouel_content where STATUS='Active' LIMIT 8 ", nativeQuery = true)
	List<CarsouelContentEntity> findByActiveStatus();

}
