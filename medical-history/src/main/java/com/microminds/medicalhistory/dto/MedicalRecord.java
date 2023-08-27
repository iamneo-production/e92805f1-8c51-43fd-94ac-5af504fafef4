package com.microminds.medicalhistory.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedicalRecord {
    List<Appointment> appointmentList;
    List<Prescription> prescriptions;

    private List<String> history;
    private List<Document> documents;
}
