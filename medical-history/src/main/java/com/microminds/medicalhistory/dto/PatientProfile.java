package com.microminds.medicalhistory.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientProfile {

        private String patientId;
        private String patientName;
        private String patientEmail;
        private String patientNumber;
        private int patientAge;
        private LocalDate dob;
        private List<String> history;
        private List<Document> documents;

}
