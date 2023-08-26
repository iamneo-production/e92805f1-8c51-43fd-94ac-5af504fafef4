package com.user.management.hackthon.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="DOCTOR_TBL")
public class Doctor {

	@Id
	@Column(name = "DOCTOR_ID")
	private String id;
	
	@Column(name="DOCTOR_NAME", length = 25, nullable = false)
	private String doctorName;
	
	@Column(name="SPECIALIZATION", length = 50, nullable = false, updatable = true)
	private String specialization;
	
	@Column(name="GENDER")
	private String gender;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
