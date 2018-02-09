package com.credo.users.dto;

import java.util.List;

public class UpdateMaxYearsDTO {
	List<Long> experiencetotrainingid;
	List<Long> expids;
	List<Long> trainingids;
	List<Long> maxyears;
	
	public List<Long> getExperiencetotrainingid() {
		return experiencetotrainingid;
	}
	public void setExperiencetotrainingid(List<Long> experiencetotrainingid) {
		this.experiencetotrainingid = experiencetotrainingid;
	}
	public List<Long> getExpids() {
		return expids;
	}
	public void setExpids(List<Long> expids) {
		this.expids = expids;
		
	}
	public List<Long> getTrainingids() {
		return trainingids;
	}
	public void setTrainingids(List<Long> trainingids) {
		this.trainingids = trainingids;
	}
	public List<Long> getMaxyears() {
		return maxyears;
	}
	public void setMaxyears(List<Long> maxyears) {
		this.maxyears = maxyears;
	}
	//update
}
