package com.credo.users.dto;

public class UsersDTO {
	private long userid;
	private String username;	
	private String password;
	private boolean fullaccess;
	private String userprev;
	
	
	public String getUserprev() {
		return userprev;
	}
	public void setUserprev(String userprev) {
		this.userprev = userprev;
	}
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
	
}
