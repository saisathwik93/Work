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
@Table(name = "previleges")
public class Previleges implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long previd;
	private String prevname;

	@OneToOne(cascade =  CascadeType.ALL, mappedBy = "previleges")	
	private UsersPrevileges usersPrevileges; 
	
	@OneToMany( fetch = FetchType.EAGER,mappedBy="previleges",cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
    private Set<PrevilegesToTrainings> previlegestotrainings;
	
	@OneToMany( fetch = FetchType.EAGER,mappedBy="previleges",cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
    private Set<PrevilegesToActions> previlegestoactions;

	public Long getPrevid() {
		return previd;
	}

	public void setPrevid(Long previd) {
		this.previd = previd;
	}

	public String getPrevname() {
		return prevname;
	}

	public void setPrevname(String prevname) {
		this.prevname = prevname;
	}

	public UsersPrevileges getUsersPrevileges() {
		return usersPrevileges;
	}

	public void setUsersPrevileges(UsersPrevileges usersPrevileges) {
		this.usersPrevileges = usersPrevileges;
	}

	public Set<PrevilegesToTrainings> getPrevilegestotrainings() {
		return previlegestotrainings;
	}

	public void setPrevilegestotrainings(Set<PrevilegesToTrainings> previlegestotrainings) {
		this.previlegestotrainings = previlegestotrainings;
	}

	public Set<PrevilegesToActions> getPrevilegestoactions() {
		return previlegestoactions;
	}

	public void setPrevilegestoactions(Set<PrevilegesToActions> previlegestoactions) {
		this.previlegestoactions = previlegestoactions;
	}
	
}
