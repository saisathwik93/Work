package com.credo.users.model;

import java.io.Serializable;
import java.util.Set;
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
@Table(name = "trainings")
public class Trainings implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long trainingid;
	private String trainingname;
	private String url;
	
	@OneToOne( cascade =  CascadeType.ALL, mappedBy = "trainings")	
	private ActionsToTrainings actionstotrainings; 	
	
	@OneToMany( fetch = FetchType.EAGER,mappedBy="trainings",cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
    private Set<PrevilegesToTrainings> previlegestotrainings;
	
	@OneToMany( fetch = FetchType.EAGER,mappedBy="trainings",cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
    private Set<ExperienceToTrainings> experiencetotrainings;

	@OneToMany( fetch = FetchType.EAGER,mappedBy="trainings",cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
    private Set<UserCompletedTrainings> usercompletedtrainings;

	public Long getTrainingid() {
		return trainingid;
	}

	public void setTrainingid(Long trainingid) {
		this.trainingid = trainingid;
	}

	public String getTrainingname() {
		return trainingname;
	}

	public void setTrainingname(String trainingname) {
		this.trainingname = trainingname;
	}

	public ActionsToTrainings getActionstotrainings() {
		return actionstotrainings;
	}

	public void setActionstotrainings(ActionsToTrainings actionstotrainings) {
		this.actionstotrainings = actionstotrainings;
	}

	public Set<PrevilegesToTrainings> getPrevilegestotrainings() {
		return previlegestotrainings;
	}

	public void setPrevilegestotrainings(Set<PrevilegesToTrainings> previlegestotrainings) {
		this.previlegestotrainings = previlegestotrainings;
	}

	public Set<ExperienceToTrainings> getExperiencetotrainings() {
		return experiencetotrainings;
	}

	public void setExperiencetotrainings(Set<ExperienceToTrainings> experiencetotrainings) {
		this.experiencetotrainings = experiencetotrainings;
	}

	public Set<UserCompletedTrainings> getUsercompletedtrainings() {
		return usercompletedtrainings;
	}

	public void setUsercompletedtrainings(Set<UserCompletedTrainings> usercompletedtrainings) {
		this.usercompletedtrainings = usercompletedtrainings;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}