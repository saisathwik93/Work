package com.credo.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.credo.users.model.Trainings;

@Repository
public interface TrainingsRepository extends JpaRepository<Trainings, Long> {
	@Query("SELECT r FROM Trainings r where r.trainingname=:trainingname")
	public Trainings getTrainingByName(@Param("trainingname") String trainingname);
	
	
}