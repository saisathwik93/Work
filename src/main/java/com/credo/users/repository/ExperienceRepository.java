package com.credo.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.credo.users.model.Experience;
import com.credo.users.model.UsersPrevileges;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Long> {	
	@Query("SELECT r FROM Experience r where r.expname=:expname")
	public Experience getUserExpByName(@Param("expname") String expname);
}