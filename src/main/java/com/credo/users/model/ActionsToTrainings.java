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
@Table(name = "actionstotrainings")
public class ActionsToTrainings implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long actionstotrainingid;
	
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "actionid")	
	private Actions actions;
	
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "trainingid")	
	private Trainings trainings;

	public Long getActionstotrainingid() {
		return actionstotrainingid;
	}

	public void setActionstotrainingid(Long actionstotrainingid) {
		this.actionstotrainingid = actionstotrainingid;
	}

	public Actions getActions() {
		return actions;
	}

	public void setActions(Actions actions) {
		this.actions = actions;
	}

	public Trainings getTrainings() {
		return trainings;
	}

	public void setTrainings(Trainings trainings) {
		this.trainings = trainings;
	}
	
	
}
