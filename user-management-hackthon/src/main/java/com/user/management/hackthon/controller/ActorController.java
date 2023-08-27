package com.user.management.hackthon.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.management.hackthon.response.DoctorResponse;
import com.user.management.hackthon.response.PatientResponse;
import com.user.management.hackthon.service.ActorService;

@RestController
@RequestMapping("/api/v1/actor")
public class ActorController {

	@Autowired
	private ActorService actorService;

	@GetMapping("/health")
	public String healthCheck() {
		return "User management service is up, " + new Date();
	}

	@GetMapping("/patient/{patientId}")
	public ResponseEntity<PatientResponse> getPatientById(@PathVariable String patientId) {
		PatientResponse patient = new PatientResponse();
		patient.setPatient(actorService.getPatientById(patientId));
		return ResponseEntity.ok().body(patient);
	}

	@GetMapping("/doctors")
	public ResponseEntity<DoctorResponse> getDoctors() {
		DoctorResponse doctors = new DoctorResponse();
		doctors.setDoctors(actorService.getDoctors());
		return ResponseEntity.ok().body(doctors);
	}
}
