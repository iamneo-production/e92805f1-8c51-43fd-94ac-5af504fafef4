package com.user.management.hackthon.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserLoginRequest {

	@JsonProperty("UserId")
	private String userName;
	
	@JsonProperty("Password")
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
