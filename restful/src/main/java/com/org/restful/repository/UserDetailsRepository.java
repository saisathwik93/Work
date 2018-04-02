package com.org.restful.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.org.restful.model.UserDetails;


	@Repository
	public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
		
		@Query("SELECT r FROM UserDetails r where r.username=:username and r.password=:password")
		
		public UserDetails getUserDetails(@Param("username") String username,@Param("password") String password);
	
		@Query("SELECT r FROM UserDetails r where r.username=:username")
		public UserDetails getUserByUsername(@Param("username") String username);
	}
	
	

