package com.credo.users.model;

import java.util.List;

public class UserPrevilegesResponse {
	
	private String username;
	private String previleges;
	private Long userid;	
	private String actions;
	private String[] completedtrainings;
	private String[] waivedtrainings;
	private List<TrainingsResponse> pendingtrainings2;
	private String[] jd;
	private Long[] years;
	private String[] blockedtrainings;
	
	
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
	
	public String[] getBlockedtrainings() {
		return blockedtrainings;
	}
	public void setBlockedtrainings(String[] blockedtrainings) {
		this.blockedtrainings = blockedtrainings;
	}
	public String[] getWaivedtrainings() {
		return waivedtrainings;
	}
	public void setWaivedtrainings(String[] waivedtrainings) {
		this.waivedtrainings = waivedtrainings;
	}
	public List<TrainingsResponse> getPendingtrainings2() {
		return pendingtrainings2;
	}
	public void setPendingtrainings2(List<TrainingsResponse> pendingtrainings2) {
		this.pendingtrainings2 = pendingtrainings2;
	}		
}