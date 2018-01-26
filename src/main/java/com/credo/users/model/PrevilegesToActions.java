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
@Table(name = "previlegestoactions")
public class PrevilegesToActions implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long previlegestoactionid;
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "previd")
    private Previleges previleges;
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "actionid")
    private Actions actions;

	public Long getPrevilegestoactionid() {
		return previlegestoactionid;
	}

	public void setPrevilegestoactionid(Long previlegestoactionid) {
		this.previlegestoactionid = previlegestoactionid;
	}

	public Previleges getPrevileges() {
		return previleges;
	}

	public void setPrevileges(Previleges previleges) {
		this.previleges = previleges;
	}

	public Actions getActions() {
		return actions;
	}

	public void setActions(Actions actions) {
		this.actions = actions;
	}
	
}
