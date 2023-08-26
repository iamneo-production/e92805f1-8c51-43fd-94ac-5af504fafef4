package com.user.management.hackthon.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserRegisterResponse {

//	@JsonProperty("token")
//	public String token;
	
	@JsonProperty("role")
	public String role;
	
	@JsonProperty("userId")
	public String userName;
	
	/*
	 * public String getToken() { return token; }
	 * 
	 * public void setToken(String token) { this.token = token; }
	 */

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
