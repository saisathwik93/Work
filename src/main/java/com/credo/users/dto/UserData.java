package com.credo.users.dto;

public class UserData {
	private Long userid;
	private String username;
	private String previlege;
	private String action;
	
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
	public String getPrevilege() {
		return previlege;
	}
	public void setPrevilege(String previlege) {
		this.previlege = previlege;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
}
