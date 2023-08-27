package com.telemedicine.video.controller;

import com.telemedicine.video.entity.Consultation;
import com.telemedicine.video.entity.Prescription;
import com.telemedicine.video.payload.PrescriptionRequest;
import com.telemedicine.video.service.VideoConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/consultation")
public class VideoConsultationController {

    @Autowired
    private VideoConsultationService consultationService;

    @GetMapping("/video/{patientId}")
    public ResponseEntity<Consultation> getVideoConsultation(@PathVariable String patientId) {
        Consultation consultation = consultationService.getVideoConsultation(patientId);
        return ResponseEntity.ok(consultation);
    }

    @PostMapping("/prescription/{patientId}/{doctorId}")
    public ResponseEntity<Prescription> createPrescription(
            @PathVariable String patientId, @PathVariable String doctorId,
            @RequestBody PrescriptionRequest prescriptionRequest) {
        Prescription prescription = consultationService.createPrescription(patientId, doctorId, prescriptionRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(prescription);
    }

    @GetMapping("/prescription/{patientId}")
    public ResponseEntity<List<Prescription>> getPrescriptions(@PathVariable String patientId) {
        List<Prescription> prescriptions = consultationService.getPrescriptions(patientId);
        return ResponseEntity.ok(prescriptions);
    }
}

