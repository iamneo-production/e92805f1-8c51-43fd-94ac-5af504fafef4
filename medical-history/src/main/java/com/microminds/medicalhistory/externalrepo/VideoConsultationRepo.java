package com.microminds.medicalhistory.externalrepo;

import com.microminds.medicalhistory.dto.Prescription;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("VIDEO-CONSULTATION-SERVICE")
public interface VideoConsultationRepo {
    @GetMapping("/api/v1/consultation/prescription/{patientId}")
    List<Prescription> getPrescriptionsByPatientId(@PathVariable("patientId") String patientId);
}
