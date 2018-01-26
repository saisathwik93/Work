package com.credo.users.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "experiencetotrainings")
public class ExperienceToTrainings implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long experiencetotrainingid;	
	private Long maxyears;

	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "expid")
    private Experience experience;
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "trainingid")
    private Trainings trainings;
	
	public Long getMaxyears() {
		return maxyears;
	}

	public void setMaxyears(Long maxyears) {
		this.maxyears = maxyears;
	}

	public Long getExperiencetotrainingid() {
		return experiencetotrainingid;
	}

	public void setExperiencetotrainingid(Long experiencetotrainingid) {
		this.experiencetotrainingid = experiencetotrainingid;
	}

	public Experience getExperience() {
		return experience;
	}

	public void setExperience(Experience experience) {
		this.experience = experience;
	}

	public Trainings getTraining() {
		return trainings;
	}

	public void setTraining(Trainings training) {
		this.trainings = training;
	}
	
	
}
