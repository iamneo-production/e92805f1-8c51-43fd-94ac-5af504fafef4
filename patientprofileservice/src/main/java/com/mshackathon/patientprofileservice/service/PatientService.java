package com.mshackathon.patientprofileservice.service;

import com.mshackathon.patientprofileservice.dto.PatientProfileDto;

public interface PatientService {
    void savePatientProfile(PatientProfileDto patientProfileDto);
    void updatePatientProfile(PatientProfileDto patientProfileDto,Long patientId);
    PatientProfileDto viewPatientProfile(Long patientId);
}
