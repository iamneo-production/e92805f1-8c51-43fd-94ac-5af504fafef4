package com.microminds.medicalhistory.service;

import com.microminds.medicalhistory.dto.Appointment;
import com.microminds.medicalhistory.dto.MedicalRecord;
import com.microminds.medicalhistory.dto.PatientProfile;
import com.microminds.medicalhistory.dto.Prescription;
import com.microminds.medicalhistory.externalrepo.AppointmentRepo;
import com.microminds.medicalhistory.externalrepo.PatientProfileRepo;
import com.microminds.medicalhistory.externalrepo.VideoConsultationRepo;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MedicalHistoryService {

    @Autowired
    private AppointmentRepo appointmentRepo;

    @Autowired
    private VideoConsultationRepo videoConsultationRepo;

    @Autowired
    private PatientProfileRepo patientProfileRepo;

    public ResponseEntity<MedicalRecord> getMedicalRecords(String patientId){
        List<Appointment> appointments = getAppointmentHistory(patientId);
        List<Prescription> prescriptions = getPrescriptions(patientId);
        PatientProfile patientProfile = getpatientProfile(patientId);
        return new ResponseEntity<>(MedicalRecord.builder().appointmentList(appointments).prescriptions(prescriptions).history(patientProfile.getHistory()).documents(patientProfile.getDocuments()).build(), HttpStatus.OK);
    }

    @CircuitBreaker(name="videoConsultationServiceCircuitBreaker", fallbackMethod = "videoConsultationBreakHandler")
    private List<Prescription> getPrescriptions(String patientId){
        return videoConsultationRepo.getPrescriptionsByPatientId(patientId);
    }

    //fallback for videoConsultation Service
    public List<Prescription> videoConsultationBreakHandler(String patientId, Exception ex){
        return new ArrayList<>();
    }


    @CircuitBreaker(name= "appointmentServiceCircuitBreaker" ,fallbackMethod = "appointmentBreakHandler")
    private List<Appointment> getAppointmentHistory(String patientId){
        return appointmentRepo.getAppointmentsByPatientId(patientId);
    }

    //fallback for appointmentService

    public List<Appointment> appointmentBreakHandler(String patientId, Exception ex){
        return new ArrayList<>();
    }

    @CircuitBreaker(name= "patientProfileServiceCircuitBreaker" ,fallbackMethod = "patientProfileBreakHandler")
    private PatientProfile getpatientProfile(String patientId){
        return patientProfileRepo.getPatientProfileById(patientId);
    }

    //fallback for patientProfileService

    public PatientProfile patientProfileBreakHandler(String patientId, Exception ex) {
        return PatientProfile.builder().build();
    }
}
