package com.org.restful.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.org.restful.model.UserDetails;
import com.org.restful.model.UserInformation;

public interface UserInformationRepository  extends JpaRepository<UserInformation,Long>{

	
	@Query("SELECT r from UserInformation r where r.userinformationid=:userinformationid")
	public UserInformation getUserByUserinformationid(@Param("userinformationid") Long userinformationid);	
	
	
	@Query("SELECT r FROM UserInformation r where r.city=:city")
	public UserInformation getCityByName(@Param("city") String city);


	
	
	
	
	
}


