package com.billing.payment.hackthon.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InvoiceRequest {

	@JsonProperty("Patient Ids")
	private List<String> patientIds;

	public List<String> getPatientIds() {
		return patientIds;
	}

	public void setPatientIds(List<String> patientIds) {
		this.patientIds = patientIds;
	}
	
}
