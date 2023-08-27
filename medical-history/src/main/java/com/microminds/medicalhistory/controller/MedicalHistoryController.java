package com.microminds.medicalhistory.controller;

import com.microminds.medicalhistory.dto.MedicalRecord;
import com.microminds.medicalhistory.service.MedicalHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/medical")
public class MedicalHistoryController {

    @Autowired
    private MedicalHistoryService medicalHistoryService;
    @GetMapping("/record/{patientId}")
    ResponseEntity<MedicalRecord> getMedicalRecordsByPatientId(@PathVariable("patientId") String patientId){
        return this.medicalHistoryService.getMedicalRecords(patientId);
    }


}
