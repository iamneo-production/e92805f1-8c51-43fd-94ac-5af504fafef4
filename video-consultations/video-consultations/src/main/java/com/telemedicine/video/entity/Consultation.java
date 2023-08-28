package com.telemedicine.video.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
@Entity
@Data
public class Consultation {
    @Id
    private String patientId;
    private String patientName;
    private String doctorId;
    private String date;
    private String time;
    private String url;
}

