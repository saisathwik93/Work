package com.credo.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.credo.users.model.Actions;
import com.credo.users.model.Previleges;

@Repository
public interface PrevilegesRepository extends JpaRepository<Previleges, Long> {
	@Query("SELECT r FROM Previleges r where r.prevname=:prevname")
	public Previleges getUserPrevByName(@Param("prevname") String prevname);
}
