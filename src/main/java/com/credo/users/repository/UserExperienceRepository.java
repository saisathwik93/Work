package com.credo.users.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.credo.users.model.Experience;
import com.credo.users.model.ExperienceToTrainings;
import com.credo.users.model.Trainings;
import com.credo.users.model.UserCompletedTrainings;
import com.credo.users.model.UserExperience;
import com.credo.users.model.Users;

@Repository
public interface UserExperienceRepository extends JpaRepository<UserExperience, Long> {
	@Modifying
	@Transactional
    @Query("Update UserExperience c SET c.years = :years WHERE c.userexpid = :userexpid")
    public int updateExperience(@Param("years") Long years, @Param("userexpid") Long userexpid);
	
	/*@Modifying
	@Transactional
    @Query("delete from UserExperience c WHERE c.userexpid = :userexpid")
    public void deleteExperience(@Param("userexpid") Long userexpid);
	*/
	@Query("SELECT r FROM UserExperience r where r.experience = :experience and r.users=:users")
	public UserExperience getExperienceByExpId(@Param("experience") Experience experience, @Param("users") Users users);
	
}