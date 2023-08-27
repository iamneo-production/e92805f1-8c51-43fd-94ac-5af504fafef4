package com.hackathon.appointments.model;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
@Data @Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class Doctor {
    private String id;
    private String doctorName;
    private String specialization;
    private String gender;
}
