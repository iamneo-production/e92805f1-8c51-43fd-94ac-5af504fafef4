package com.user.management.hackthon.response;

import java.util.List;

import com.user.management.hackthon.dto.DoctorDTO;

public class DoctorResponse {

	private List<DoctorDTO> doctors;

	public List<DoctorDTO> getDoctors() {
		return doctors;
	}

	public void setDoctors(List<DoctorDTO> doctors) {
		this.doctors = doctors;
	}
	
}
