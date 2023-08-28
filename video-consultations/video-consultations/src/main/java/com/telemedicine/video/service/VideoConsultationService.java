package com.telemedicine.video.service;
import com.telemedicine.video.entity.Consultation;
import com.telemedicine.video.entity.Prescription;
import com.telemedicine.video.exception.ResourceNotFoundException;
import com.telemedicine.video.payload.PrescriptionRequest;
import com.telemedicine.video.repository.PrescriptionRepository;
import com.telemedicine.video.repository.VideoConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@Service
public class VideoConsultationService {

    @Autowired
    private VideoConsultationRepository consultationRepository;

    @Autowired
    private PrescriptionRepository prescriptionRepository;
    private final String CONSULTATION_SERVICE_URL = "http://APPOINTMENT-SERVICE/api/v1/appointment/history";

    private final RestTemplate restTemplate;

    public VideoConsultationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Consultation getConsultationData(String consultationId) {
        String url = CONSULTATION_SERVICE_URL + "/" + consultationId;

        return restTemplate.getForObject(url, Consultation.class);
    }
    public Consultation getVideoConsultation(String patientId) {
        return consultationRepository.findByPatientId(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Video consultation not found for patient: " + patientId));
    }

    public Prescription createPrescription(String patientId, String doctorId, PrescriptionRequest prescriptionRequest) {
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

    public List<Prescription> getPrescriptions(String patientId) {
        return prescriptionRepository.findByPatientId(patientId);
    }
}

