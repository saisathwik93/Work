package com.credo.users.model;

import java.io.Serializable;
import java.util.List;
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

@Entity
@Table(name = "actions")
public class Actions implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long actionid;
	private String actionname;

	@OneToOne( cascade =  CascadeType.ALL, mappedBy = "actions")	
	private ActionsToTrainings actionstotrainings; 	
	
	@OneToMany( fetch = FetchType.EAGER,mappedBy="actions",cascade = CascadeType.ALL)
    private Set<PrevilegesToActions> previlegestoactions;

	public Long getActionid() {
		return actionid;
	}

	public void setActionid(Long actionid) {
		this.actionid = actionid;
	}

	public String getActionname() {
		return actionname;
	}

	public void setActionname(String actionname) {
		this.actionname = actionname;
	}

	public ActionsToTrainings getActionstotrainings() {
		return actionstotrainings;
	}

	public void setActionstotrainings(ActionsToTrainings actionstotrainings) {
		this.actionstotrainings = actionstotrainings;
	}

	public Set<PrevilegesToActions> getPrevilegestoactions() {
		return previlegestoactions;
	}

	public void setPrevilegestoactions(Set<PrevilegesToActions> previlegestoactions) {
		this.previlegestoactions = previlegestoactions;
	}
		
	
}
