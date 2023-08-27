package com.telemedicine.video.repository;

import com.telemedicine.video.entity.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VideoConsultationRepository extends JpaRepository<Consultation, String> {
    Optional<Consultation> findByPatientId(String patientId);
}
