package com.mshackathon.patientprofileservice.dao;

import com.mshackathon.patientprofileservice.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document,String> {
}
