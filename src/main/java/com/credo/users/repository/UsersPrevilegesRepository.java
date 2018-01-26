package com.credo.users.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.credo.users.model.Users;
import com.credo.users.model.UsersPrevileges;
@Repository
public interface UsersPrevilegesRepository extends JpaRepository<UsersPrevileges, Long> {
	/*@Query("SELECT r FROM UsersPrevileges r where r.userid=:userid")
	public List<UsersPrevileges> getUserPrevileges(@Param("userid") Long userid);*/
	
	
}
