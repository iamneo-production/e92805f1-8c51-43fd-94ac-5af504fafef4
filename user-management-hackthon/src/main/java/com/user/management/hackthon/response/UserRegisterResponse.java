package com.user.management.hackthon.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class UserRegisterResponse {

	@JsonProperty("role")
	@JsonInclude(Include.NON_NULL)
	public String role;
	
	@JsonProperty("userId")
	@JsonInclude(Include.NON_NULL)
	public String userName;
	
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
