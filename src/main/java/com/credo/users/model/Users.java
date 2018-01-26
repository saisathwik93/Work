package com.credo.users.model;

import java.io.Serializable;
import java.util.Set;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "users")
public class Users implements Serializable {
	private static final long serialVersionUID = -3009157732242241606L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userid;
	private String username;	
	private String password;
	private boolean fullaccess;
	
	@OneToOne( mappedBy = "users")	
	private UsersPrevileges usersPrevileges; 
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="users",cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
    private Set<UserCompletedTrainings> usercompletedtrainings;
	
	
	
	@OneToMany(mappedBy="users",cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
    private Set<UserExperience> userExperience;
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isFullaccess() {
		return fullaccess;
	}
	public void setFullaccess(boolean fullaccess) {
		this.fullaccess = fullaccess;
	}
	public UsersPrevileges getUsersPrevileges() {
		return usersPrevileges;
	}
	public void setUsersPrevileges(UsersPrevileges usersPrevileges) {
		this.usersPrevileges = usersPrevileges;
	}
	
	
	public Set<UserCompletedTrainings> getUsercompletedtrainings() {
		return usercompletedtrainings;
	}
	public void setUsercompletedtrainings(Set<UserCompletedTrainings> usercompletedtrainings) {
		this.usercompletedtrainings = usercompletedtrainings;
	}
	public Set<UserExperience> getUserExperience() {
		return userExperience;
	}
	public void setUserExperience(Set<UserExperience> userExperience) {
		this.getUserExperience().clear();
		this.getUserExperience().addAll(userExperience);
	}
	
}