package com.microminds.medicalhistory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prescription {
    String patientId;
    String doctorId;

    LocalDate date;

    String medicines;
}
