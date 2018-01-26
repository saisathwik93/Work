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
@Table(name = "userexperience")
public class UserExperience implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userexpid;
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "expid")
    private Experience experience;
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "userid")
    private Users users;
	
	private Long years;

	public Long getUserexpid() {
		return userexpid;
	}

	public void setUserexpid(Long userexpid) {
		this.userexpid = userexpid;
	}

	
	public Long getYears() {
		return years;
	}

	public void setYears(Long years) {
		this.years = years;
	}

	public Experience getExperience() {
		return experience;
	}

	public void setExperience(Experience experience) {
		this.experience = experience;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}
	
	
}
