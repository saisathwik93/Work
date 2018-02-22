package com.credo.users.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.credo.users.model.Actions;
import com.credo.users.model.PrevilegesToActions;
import com.credo.users.model.PrevilegesToTrainings;

@Repository
public interface PrevilegesToActionsRepository extends JpaRepository<PrevilegesToActions, Long> {
	/*@Query("SELECT r.actionid FROM PrevilegesToActions r where r.previd=:previd")
	public List<Long> getUserPrevileges(@Param("previd") Long previd);*/
	
	/*@Modifying
	@Transactional
    @Query("delete from PrevilegesToActions c WHERE c.previlegestoactionid = :previlegestoactionid")
    public void deleteActions(@Param("previlegestoactionid") Long previlegestoactionid);*/
}
