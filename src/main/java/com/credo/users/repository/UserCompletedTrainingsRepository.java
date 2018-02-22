package com.credo.users.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.credo.users.model.Actions;
import com.credo.users.model.Trainings;
import com.credo.users.model.UserCompletedTrainings;
import com.credo.users.model.UsersPrevileges;


@Repository
public interface UserCompletedTrainingsRepository extends JpaRepository<UserCompletedTrainings, Long> {
	@Query("SELECT r FROM UserCompletedTrainings r where r.trainings=:trainings")
	public UserCompletedTrainings getCompletedTrainingByName(@Param("trainings") Trainings trainings);
	
	/*@Modifying
	@Transactional
    @Query("delete from UserCompletedTrainings c WHERE c.usercompletedtrainingid = :usercompletedtrainingid")
    public void deleteCompletedTraining(@Param("usercompletedtrainingid") Long usercompletedtrainingid);*/
}