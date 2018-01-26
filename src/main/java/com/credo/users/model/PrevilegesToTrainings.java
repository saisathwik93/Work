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
@Table(name = "previlegestotrainings")
public class PrevilegesToTrainings implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long previlegestotrainingid;
		
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "previd")
    private Previleges previleges;
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "trainingid")
    private Trainings trainings;

	public Long getPrevilegestotrainingid() {
		return previlegestotrainingid;
	}

	public void setPrevilegestotrainingid(Long previlegestotrainingid) {
		this.previlegestotrainingid = previlegestotrainingid;
	}

	public Previleges getPrevileges() {
		return previleges;
	}

	public void setPrevileges(Previleges previleges) {
		this.previleges = previleges;
	}

	public Trainings getTrainings() {
		return trainings;
	}

	public void setTrainings(Trainings trainings) {
		this.trainings = trainings;
	}
	
}
