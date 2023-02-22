package com.app.MovieApp.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.MovieApp.entity.UserRolesEntity;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRolesEntity,Long> {

	Set<UserRolesEntity> findByRoleName(String string);


}
