package com.credo.users.model;

import java.util.List;

public class UserPrevilegesResponse {
	
	private String username;
	private String previleges;
	private Long userid;	
	private String actions;
	private String[] completedtrainings;
	private String[] waivedtrainings;
	private List<TrainingsResponse> pendingtrainings;
	private String[] jd;
	private Long[] years;
	private List<TrainingsResponse> blockedtrainings;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPrevileges() {
		return previleges;
	}
	public void setPrevileges(String previleges) {
		this.previleges = previleges;
	}
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
	public String getActions() {
		return actions;
	}
	public void setActions(String actions) {
		this.actions = actions;
	}
	public String[] getJd() {
		return jd;
	}
	public void setJd(String[] jd) {
		this.jd = jd;
	}
	public Long[] getYears() {
		return years;
	}
	public void setYears(Long[] years) {
		this.years = years;
	}
	public String[] getCompletedtrainings() {
		return completedtrainings;
	}
	public void setCompletedtrainings(String[] completedtrainings) {
		this.completedtrainings = completedtrainings;
	}
	
	public List<TrainingsResponse> getBlockedtrainings() {
		return blockedtrainings;
	}
	public void setBlockedtrainings(List<TrainingsResponse> blockedtrainings) {
		this.blockedtrainings = blockedtrainings;
	}
	public String[] getWaivedtrainings() {
		return waivedtrainings;
	}
	public void setWaivedtrainings(String[] waivedtrainings) {
		this.waivedtrainings = waivedtrainings;
	}
	public List<TrainingsResponse> getPendingtrainings() {
		return pendingtrainings;
	}
	public void setPendingtrainings(List<TrainingsResponse> pendingtrainings) {
		this.pendingtrainings = pendingtrainings;
	}		
}