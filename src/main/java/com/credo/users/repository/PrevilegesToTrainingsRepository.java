package com.credo.users.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.credo.users.model.PrevilegesToActions;
import com.credo.users.model.PrevilegesToTrainings;
import com.credo.users.model.Trainings;
import com.credo.users.model.UsersPrevileges;

@Repository
public interface PrevilegesToTrainingsRepository extends JpaRepository<PrevilegesToTrainings, Long> {
	@Modifying
	@Transactional
    @Query("delete from PrevilegesToTrainings c WHERE c.previlegestotrainingid = :previlegestotrainingid")
    public void deletePrevelegesToTraining(@Param("previlegestotrainingid") Long previlegestotrainingid);
	
	@Modifying
	@Transactional
    @Query("delete from ExperienceToTrainings c WHERE c.trainings = :trainings")
    public void deletePrevilegesToTrainingByTraining(@Param("trainings") Trainings trainings);
}
