package com.credo.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.credo.users.model.ExperienceToTrainings;
import com.credo.users.model.Trainings;
import com.credo.users.model.UsersPrevileges;

@Repository
public interface ExperienceToTrainingsRespository extends JpaRepository<ExperienceToTrainings, Long> {
	/*@Query("SELECT r FROM UserPrevileges r where r.userid=:userid")
	public UserPrevileges getUserPrevileges(@Param("userid") Long userid);*/
	
	@Query("SELECT r FROM ExperienceToTrainings r where r.trainings = :trainings")
	public ExperienceToTrainings getExperienceByTrainingName(@Param("trainings") Trainings trainings);
	
	@Modifying
	@Transactional
    @Query("delete from ExperienceToTrainings c WHERE c.trainings = :trainings")
    public void deleteExperienceToTraining(@Param("trainings") Trainings trainings);
}