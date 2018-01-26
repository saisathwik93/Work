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
@Table(name = "usercompletedtrainings")
public class UserCompletedTrainings implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long usercompletedtrainingid;
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "trainingid")
    private Trainings trainings;
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "userid")
    private Users users;

	public Long getUsercompletedtrainingid() {
		return usercompletedtrainingid;
	}

	public void setUsercompletedtrainingid(Long usercompletedtrainingid) {
		this.usercompletedtrainingid = usercompletedtrainingid;
	}

	public Trainings getTrainings() {
		return trainings;
	}

	public void setTrainings(Trainings trainings) {
		this.trainings = trainings;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}
	
}
