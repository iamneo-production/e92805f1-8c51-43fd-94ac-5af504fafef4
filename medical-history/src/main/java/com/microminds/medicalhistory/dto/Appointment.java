package com.microminds.medicalhistory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    String appointmentId;
    String bookedBy;
    String patientId;
    String patientEmail;
    String appointmentType; // inperson or oncall
    String doctorId;
    LocalDate appointmentDate;
    LocalTime appointmentTime;

}
