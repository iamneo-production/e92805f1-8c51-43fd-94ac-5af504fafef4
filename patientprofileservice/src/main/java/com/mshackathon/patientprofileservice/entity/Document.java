package com.mshackathon.patientprofileservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String documentId;
    private String documentName;
    private String description;
    @ManyToOne
    @JoinColumn(name = "patientId")
    @JsonIgnore
    @ToString.Exclude
    private PatientProfile patient;
}
