package com.user.management.hackthon.response;

import java.util.HashMap;
import java.util.Map;

public class UserLoginResponse {
	
	private Map<String, String> respnose = new HashMap<>();
	
	public Map<String, String> getRespnose() {
		return respnose;
	}

	public void setRespnose(Map<String, String> respnose) {
		this.respnose = respnose;
	}

}