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
@Table(name = "usersprevileges")
public class UsersPrevileges implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long usersprevilegesid;
	
	@OneToOne( cascade= CascadeType.ALL)
	@JoinColumn(name = "userid")	
	private Users users;
	
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "previd")	
	private Previleges previleges;

	public Long getUsersprevilegesid() {
		return usersprevilegesid;
	}

	public void setUsersprevilegesid(Long usersprevilegesid) {
		this.usersprevilegesid = usersprevilegesid;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Previleges getPrevileges() {
		return previleges;
	}

	public void setPrevileges(Previleges previleges) {
		this.previleges = previleges;
	}
	
	
}
