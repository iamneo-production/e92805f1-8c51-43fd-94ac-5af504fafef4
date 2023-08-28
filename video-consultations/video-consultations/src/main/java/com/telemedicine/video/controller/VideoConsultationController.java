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
    private VideoConsultationService videoConsultationService;

    public VideoConsultationController(VideoConsultationService consultationService) {
        this.videoConsultationService = consultationService;
    }

    @GetMapping("/video/{patientId}")
    public ResponseEntity<String> getVideoConsultation(@PathVariable String patientId) {
        // Fetch consultation data using the ConsultationService
        Consultation consultationDto = videoConsultationService.getConsultationData(patientId);

        // Create a response or process the fetched data as needed
        String response = "Patient: " + consultationDto.getPatientName()
                + ", Doctor: " + consultationDto.getDoctorId()
                + ", Date: " + consultationDto.getDate()
                + ", Time: " + consultationDto.getTime();

        return ResponseEntity.ok(response);
    }
//    @GetMapping("/video/{patientId}")
//    public ResponseEntity<Consultation> getVideoConsultation(@PathVariable String patientId) {
//        Consultation consultation = videoConsultationService.getVideoConsultation(patientId);
//        return ResponseEntity.ok(consultation);
//    }

    @PostMapping("/prescription/{patientId}/{doctorId}")
    public ResponseEntity<Prescription> createPrescription(
            @PathVariable String patientId, @PathVariable String doctorId,
            @RequestBody PrescriptionRequest prescriptionRequest) {
        Prescription prescription = videoConsultationService.createPrescription(patientId, doctorId, prescriptionRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(prescription);
    }

    @GetMapping("/prescription/{patientId}")
    public ResponseEntity<List<Prescription>> getPrescriptions(@PathVariable String patientId) {
        List<Prescription> prescriptions = videoConsultationService.getPrescriptions(patientId);
        return ResponseEntity.ok(prescriptions);
    }
}

