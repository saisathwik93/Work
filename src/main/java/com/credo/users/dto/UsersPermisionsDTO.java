package com.credo.users.dto;

public class UsersPermisionsDTO {
	private long userid;
	private String username;	
	private String password;
	private boolean fullaccess;
	private Long userprevid;	
	private String previleges;
	private String actions;
	private String completedtrainings;
	private String pendingtrainings;
	private String jdyearsexp;
	
	
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
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
	public Long getUserprevid() {
		return userprevid;
	}
	public void setUserprevid(Long userprevid) {
		this.userprevid = userprevid;
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
	public String getCompletedtrainings() {
		return completedtrainings;
	}
	public void setCompletedtrainings(String completedtrainings) {
		this.completedtrainings = completedtrainings;
	}
	public String getPendingtrainings() {
		return pendingtrainings;
	}
	public void setPendingtrainings(String pendingtrainings) {
		this.pendingtrainings = pendingtrainings;
	}
	public String getJdyearsexp() {
		return jdyearsexp;
	}
	public void setJdyearsexp(String jdyearsexp) {
		this.jdyearsexp = jdyearsexp;
	}
	
}
