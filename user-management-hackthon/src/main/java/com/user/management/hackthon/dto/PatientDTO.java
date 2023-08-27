package com.user.management.hackthon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PatientDTO {

	@JsonProperty("Patient Id")
	private String patientId;
	
	@JsonProperty("Patient Name")
	private String patientName;
	
	@JsonProperty("Gender")
	private String gender;
	
	@JsonProperty("Email Id")
	private String mailId;
	
	@JsonProperty("Phone")
	private String contact;
	
	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
	
}
