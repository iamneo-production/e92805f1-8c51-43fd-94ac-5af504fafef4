package com.billing.payment.hackthon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentPatientDTO {

	@JsonProperty("Patient Id")
	private String patientId;
	
	@JsonProperty("Item")
	private String item;
	
	@JsonProperty("Amount")
	private String amount;
	
	@JsonProperty("token")
	private String token;

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
