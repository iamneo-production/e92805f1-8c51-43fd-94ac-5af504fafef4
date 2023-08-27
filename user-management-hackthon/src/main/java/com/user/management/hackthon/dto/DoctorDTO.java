package com.user.management.hackthon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DoctorDTO {

	@JsonProperty("Doctor Id")
	private String doctorId;
	
	@JsonProperty("Doctor Name")
	private String doctorName;
	
	@JsonProperty("Specialist")
	private String specialization;
	
	@JsonProperty("Gender")
	private String gender;

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
}
