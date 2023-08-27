package com.user.management.hackthon.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.user.management.hackthon.dto.PatientDTO;

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
