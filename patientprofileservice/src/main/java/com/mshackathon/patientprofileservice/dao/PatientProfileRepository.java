package com.mshackathon.patientprofileservice.dao;

import com.mshackathon.patientprofileservice.entity.PatientProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientProfileRepository extends JpaRepository<PatientProfile,String> {
}
