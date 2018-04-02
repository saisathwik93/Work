package com.org.restful.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="userdetails")
public class UserDetails implements Serializable {


	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userid;
	private String username;
	private String password;
	
	@OneToOne(mappedBy ="userDetails")
	private UserInformation userInformation;
	
	
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
	public UserInformation getUserInformation() {
		return userInformation;
	}
	public void setUserInformation(UserInformation userInformation) {
		this.userInformation = userInformation;
	}
	
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
