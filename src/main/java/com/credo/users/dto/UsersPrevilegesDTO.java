package com.credo.users.dto;

import java.util.List;

public class UsersPrevilegesDTO {
	private Long userid;
	private String username;
	private String previleges;
	private String actions;
	private List<String> completedtrainings;
	private List<String> pendingtrainings;
	private List<String> jd;
	private List<Long> years;
	
	
	public List<String> getCompletedtrainings() {
		return completedtrainings;
	}
	public void setCompletedtrainings(List<String> completedtrainings) {
		this.completedtrainings = completedtrainings;
	}
	public List<String> getPendingtrainings() {
		return pendingtrainings;
	}
	public void setPendingtrainings(List<String> pendingtrainings) {
		this.pendingtrainings = pendingtrainings;
	}
	public List<String> getJd() {
		return jd;
	}
	public void setJd(List<String> jd) {
		this.jd = jd;
	}
	public List<Long> getYears() {
		return years;
	}
	public void setYears(List<Long> years) {
		this.years = years;
	}
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
	public String getPrevileges() {
		return previleges;
	}
	public void setPrevileges(String previleges) {
		this.previleges = previleges;
	}
	public String getActions() {
		return actions;
	}
	public void setActions(String actions) {
		this.actions = actions;
	}	
}
