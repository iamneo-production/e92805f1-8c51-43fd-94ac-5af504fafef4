package com.user.management.hackthon.response;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class UserLoginResponse {
	
	@JsonInclude(Include.NON_NULL)
	private Map<String, String> respnose = new HashMap<>();
	
	public Map<String, String> getRespnose() {
		return respnose;
	}

	public void setRespnose(Map<String, String> respnose) {
		this.respnose = respnose;
	}

}