package com.user.management.hackthon.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.management.hackthon.contants.UserContants;
import com.user.management.hackthon.dto.DoctorDTO;
import com.user.management.hackthon.dto.PatientDTO;
import com.user.management.hackthon.entity.Doctor;
import com.user.management.hackthon.entity.Patient;
import com.user.management.hackthon.entity.User;
import com.user.management.hackthon.repository.DoctorRepository;
import com.user.management.hackthon.repository.PatientRepository;
import com.user.management.hackthon.repository.UserRepository;

@Service
public class ActorService {

	@Autowired
	private DoctorRepository actorRepository;

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private UserRepository userRepository;

	public List<DoctorDTO> getDoctors() {
		List<DoctorDTO> doctors = new ArrayList<>();
		List<Doctor> doctorsList = actorRepository.findAll();

		doctorsList.stream().forEach(doctor -> {
			DoctorDTO dto = new DoctorDTO();
			dto.setDoctorId(doctor.getId());
			dto.setDoctorName(doctor.getDoctorName());
			dto.setSpecialization(doctor.getSpecialization());
			dto.setGender(
					UserContants._M.equalsIgnoreCase(doctor.getGender()) ? UserContants.MALE : UserContants.FEMALE);
			doctors.add(dto);
		});

		return doctors;
	}

	public PatientDTO getPatientById(String patientId) {
		PatientDTO patient = new PatientDTO();
		Patient entityPatient = patientRepository.findById(patientId).orElse(new Patient());
		if (null != entityPatient && Objects.nonNull(entityPatient.getId())) {
			User user = userRepository.findById(patientId).orElse(new User());
			patient.setPatientId(entityPatient.getId());
			patient.setPatientName(user.getUsername());
			patient.setGender(UserContants._M.equalsIgnoreCase(entityPatient.getGender()) ? UserContants.MALE
					: UserContants.FEMALE);
			patient.setContact(user.getContact());
			patient.setMailId(user.getEmail());
		}

		return patient;
	}
}
