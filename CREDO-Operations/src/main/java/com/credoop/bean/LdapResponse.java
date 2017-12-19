package com.credoop.bean;


import java.io.Serializable;

import org.springframework.util.MultiValueMap;

public class LdapResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String appKey;
	private String environment;
	private String username;
	private String error;
	private String password;
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private MultiValueMap<String, Object> properties;

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public MultiValueMap<String, Object> getProperties() {
		return properties;
	}

	public void setProperties(MultiValueMap<String, Object> properties) {
		this.properties = properties;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}  

