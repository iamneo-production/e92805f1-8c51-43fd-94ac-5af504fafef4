package com.telemedicine.video.service;

import com.telemedicine.video.entity.Consultation;
import com.telemedicine.video.entity.Prescription;
import com.telemedicine.video.exception.ResourceNotFoundException;
import com.telemedicine.video.payload.PrescriptionRequest;
import com.telemedicine.video.repository.PrescriptionRepository;
import com.telemedicine.video.repository.VideoConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VideoConsultationService {

    @Autowired
    private VideoConsultationRepository consultationRepository;

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    public Consultation getVideoConsultation(Long patientId) {
        return consultationRepository.findByPatientId(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Video consultation not found for patient: " + patientId));
    }

    public Prescription createPrescription(Long patientId, Long doctorId, PrescriptionRequest prescriptionRequest) {
        Consultation consultation = consultationRepository.findByPatientId(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Video consultation not found for patient: " + patientId));

        Prescription prescription = new Prescription();
        prescription.setPatientId(patientId);
        prescription.setDoctorId(doctorId);
        prescription.setDate(LocalDate.now());
        prescription.setMedicines(prescriptionRequest.getMedicines());
        prescriptionRepository.save(prescription);

        return prescription;
    }

    public List<Prescription> getPrescriptions(Long patientId) {
        return prescriptionRepository.findByPatientId(patientId);
    }
}

