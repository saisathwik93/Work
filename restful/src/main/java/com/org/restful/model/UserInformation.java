package com.org.restful.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="userinformation")
public class UserInformation implements Serializable {

	 @Id
	private long userinformationid;
	
	private String Designation;
	
	private String city;
	
	private String country;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="userid")
	private UserDetails userDetails;

	public long getUserinformationid() {
		return userinformationid;
	}

	public void setUserinformationid(long userinformationid) {
		this.userinformationid = userinformationid;
	}

	public String getDesignation() {
		return Designation;
	}

	public void setDesignation(String designation) {
		Designation = designation;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}	
}
