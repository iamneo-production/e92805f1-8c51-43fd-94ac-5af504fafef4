package com.microminds.medicalhistory.externalrepo;

import com.microminds.medicalhistory.dto.PatientProfile;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("PATIENT-PROFILE-SERVICE")
public interface PatientProfileRepo {
    @GetMapping("/api/v1/profile/view/{patientId}")
    PatientProfile getPatientProfileById(@PathVariable("patientId") String patientId);
}
