package com.app.MovieApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.MovieApp.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

	@Query(value="SELECT * FROM movie_app.user_details where USER_NAME = :name or EMAIL_ID = :name ; ",nativeQuery = true)
	UserEntity findByUsernameOrEmail(@Param("name") String name);

	boolean existsByUserName(String userName);

	boolean existsByEmailId(String emailId);

}
