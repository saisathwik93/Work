package com.credo.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.credo.users.model.ActionsToTrainings;

@Repository
public interface ActionsToTrainingsRepository extends JpaRepository<ActionsToTrainings, Long> {
	/*@Query("SELECT r FROM UserPrevileges r where r.userid=:userid")
	public UserPrevileges getUserPrevileges(@Param("userid") Long userid);*/
	
	/*@Query("SELECT r FROM UserPrevileges r where r.username=:username")
	public UserPrevileges getUserPrevByName(@Param("username") String username);*/
}