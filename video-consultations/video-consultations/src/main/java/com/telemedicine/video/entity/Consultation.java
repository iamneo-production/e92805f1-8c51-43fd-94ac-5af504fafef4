package com.telemedicine.video.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="consultations",uniqueConstraints = {@UniqueConstraint(columnNames = {"patientName"})})
public class Consultation {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientId;
@Column(name="patientName",nullable = false)
    private String patientName;
    @Column(name="doctorName",nullable = false)
    private String doctorName;
    @Column(name="date",nullable = false)
    private LocalDate date;
    @Column(name="time",nullable = false)
    private LocalTime time;
    @Column(name="videoUrl",nullable = false)
    private String videoUrl;

}
