package com.telemedicine.video.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telemedicine.video.dto.ConsultationDTO;
import com.telemedicine.video.entity.Prescription;
import com.telemedicine.video.payload.PrescriptionRequest;
import com.telemedicine.video.service.VideoConsultationService;

@RestController
@RequestMapping("/api/v1/consultation")
public class VideoConsultationController {

	@Autowired
	private VideoConsultationService consultationService;
	
	@GetMapping("/health")
	public String health() {
		return "Video Consultation Serivce is up, "+new Date();
	}

	@GetMapping("/video/{patientId}")
	public ResponseEntity<ConsultationDTO> getVideoConsultation(@PathVariable("patientId") String patientId) {
		ConsultationDTO consultation = consultationService.getVideoConsultation(patientId);
		return ResponseEntity.ok(consultation);
	}

	@PostMapping("/prescription/{patientId}/{doctoerId}")
	public ResponseEntity<Prescription> createPrescription(@PathVariable("patientId") String patientId,
			@PathVariable("doctoerId") String doctorId, @RequestBody PrescriptionRequest prescriptionRequest) {
		Prescription prescription = consultationService.createPrescription(patientId, doctorId, prescriptionRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(prescription);
	}

	@GetMapping("/prescription/{patientId}")
	public ResponseEntity<List<Prescription>> getPrescriptions(@PathVariable("patientId") String patientId) {
		List<Prescription> prescriptions = consultationService.getPrescriptions(patientId);
		return ResponseEntity.ok(prescriptions);
	}
}
