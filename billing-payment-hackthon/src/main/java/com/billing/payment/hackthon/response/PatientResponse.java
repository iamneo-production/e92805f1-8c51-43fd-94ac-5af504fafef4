package com.billing.payment.hackthon.response;

import com.billing.payment.hackthon.dto.PatientDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class PatientResponse {

	@JsonInclude(Include.NON_NULL)
	PatientDTO patient;

	public PatientDTO getPatient() {
		return patient;
	}

	public void setPatient(PatientDTO patient) {
		this.patient = patient;
	}
	
}
