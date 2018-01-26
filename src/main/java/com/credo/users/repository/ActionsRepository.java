package com.credo.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.credo.users.model.Actions;
import com.credo.users.model.Users;

@Repository
public interface ActionsRepository extends JpaRepository<Actions, Long> {
	@Query("SELECT r FROM Actions r where r.actionname=:actionname")
	public Actions getActionByName(@Param("actionname") String actionname);
}
