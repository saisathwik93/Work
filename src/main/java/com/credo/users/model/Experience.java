package com.credo.users.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "experience")
public class Experience implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long expid;
	private String expname;
	
	@OneToMany( fetch = FetchType.EAGER,mappedBy="experience",cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
    private Set<ExperienceToTrainings> experiencetotrainings;
	
	@OneToMany( fetch = FetchType.EAGER,mappedBy="experience",cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
    private Set<UserExperience> userExperience;

	public Long getExpid() {
		return expid;
	}

	public void setExpid(Long expid) {
		this.expid = expid;
	}

	public String getExpname() {
		return expname;
	}

	public void setExpname(String expname) {
		this.expname = expname;
	}

	public Set<ExperienceToTrainings> getExperiencetotrainings() {
		return experiencetotrainings;
	}

	public void setExperiencetotrainings(Set<ExperienceToTrainings> experiencetotrainings) {
		this.experiencetotrainings = experiencetotrainings;
	}

	public Set<UserExperience> getUserExperience() {
		return userExperience;
	}

	public void setUserExperience(Set<UserExperience> userExperience) {
		this.userExperience = userExperience;
	}
	
	
}
