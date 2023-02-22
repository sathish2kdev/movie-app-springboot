package com.app.MovieApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.MovieApp.entity.LoginHistoryEntity;

@Repository
public interface LoginHistoryRepository extends JpaRepository<LoginHistoryEntity,Long> {

}
