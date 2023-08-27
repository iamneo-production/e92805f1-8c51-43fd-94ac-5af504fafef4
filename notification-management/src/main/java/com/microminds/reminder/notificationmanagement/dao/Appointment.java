package com.microminds.reminder.notificationmanagement.dao;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    String AppointmentId;
    String bookedBy;
    String patientId;
    String patientEmail;
    String appointmentType; // VIRTUAL or PHYSICAL
    String doctorId;
    LocalDate appointmentDate;
    LocalTime appointmentTime;

}
