package com.telemedicine.video.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Prescription {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String patientId;
    private String doctorId;
    private LocalDate date;
    private String medicines;
}