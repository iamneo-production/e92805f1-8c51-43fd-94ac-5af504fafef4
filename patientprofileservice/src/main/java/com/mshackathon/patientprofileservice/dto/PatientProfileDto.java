package com.mshackathon.patientprofileservice.dto;

import com.mshackathon.patientprofileservice.entity.Document;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientProfileDto {
    private String patientName;
    private String patientEmail;
    private String patientNumber;
    private int patientAge;
    private LocalDate dob;
    private List<String> history;
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Document> documents;
}
