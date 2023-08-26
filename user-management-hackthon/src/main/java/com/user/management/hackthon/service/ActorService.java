package com.user.management.hackthon.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.management.hackthon.dto.DoctorDTO;
import com.user.management.hackthon.entity.Doctor;
import com.user.management.hackthon.repository.DoctorRepository;

@Service
public class ActorService {

	@Autowired
	private DoctorRepository actorRepository;
	
	public List<DoctorDTO> getDoctors() {
		List<DoctorDTO> doctors = new ArrayList<>(); 
		List<Doctor> doctorsList = actorRepository.findAll();
		
		doctorsList.stream().forEach(doctor -> {
			DoctorDTO dto = new DoctorDTO();
			dto.setDoctorId(doctor.getId());
			dto.setDoctorName(doctor.getDoctorName());
			dto.setSpecialization(doctor.getSpecialization());
			dto.setGender("M".equalsIgnoreCase(doctor.getGender()) ? "Male" : "Female");
			doctors.add(dto);
		});
		
		return doctors;
	}
}
