package com.mshackathon.patientprofileservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class PatientProfile {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private String patientId;
    private String patientName;
    private String patientEmail;
    private String patientNumber;
    private int patientAge;
    private LocalDate dob;
    private List<String> history;
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Document> documents;

}
