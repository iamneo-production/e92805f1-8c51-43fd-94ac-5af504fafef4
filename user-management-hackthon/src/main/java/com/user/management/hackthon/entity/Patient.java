package com.user.management.hackthon.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="PATIENT_TBL")
public class Patient {

	@Id
	@Column(name = "PATIENT_ID")
	private String id;
	
	@Column(name="PATIENT_NAME", length = 25, nullable = false)
	private String patientName;
	
	@Column(name="GENDER")
	private String gender;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
}
