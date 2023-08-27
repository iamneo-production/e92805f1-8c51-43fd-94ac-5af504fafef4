package com.microminds.medicalhistory.externalrepo;

import com.microminds.medicalhistory.dto.Appointment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "APPOINTMENT-SERVICE")
public interface AppointmentRepo {
    @GetMapping("/api/v1/appointment/history/{patientId}")
    List<Appointment> getAppointmentsByPatientId(@PathVariable("patientId") String patientId);
}
