package com.telemedicine.video.repository;

import com.telemedicine.video.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, String> {
	
	List<Prescription> findByPatientIdAndDoctorId(String patientId, String doctorId);
}