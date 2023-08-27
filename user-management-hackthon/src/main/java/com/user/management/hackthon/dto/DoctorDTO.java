package com.user.management.hackthon.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DoctorDTO {

	@JsonProperty("Doctor Id")
	@JsonInclude(Include.NON_NULL)
	private String doctorId;
	
	@JsonProperty("Doctor Name")
	@JsonInclude(Include.NON_NULL)
	private String doctorName;
	
	@JsonProperty("Specialist")
	@JsonInclude(Include.NON_NULL)
	private String specialization;
	
	@JsonProperty("Gender")
	@JsonInclude(Include.NON_NULL)
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
