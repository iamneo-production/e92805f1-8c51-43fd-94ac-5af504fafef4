package com.hackathon.appointments.controller;

import com.hackathon.appointments.entity.Appointment;
import com.hackathon.appointments.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/appointment")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;


    @GetMapping("/view")
    public Map<LocalDate, Map<LocalTime,String>> viewSlots(){
        LocalDate fromDate = LocalDate.now();
        LocalDate toDate = fromDate.plusDays(1);
        return appointmentService.availableSlots(fromDate,toDate);

    }

    @GetMapping("/view/{mode}")
    public Map<LocalDate, Map<LocalTime,String>> viewSlots(@PathVariable String mode){
        System.out.println("Mode: "+mode);
        LocalDate fromDate = LocalDate.now();
        LocalDate toDate = fromDate.plusDays(1);
        switch (mode){
            case "w":
                toDate = fromDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY));
                break;
            case "m":
                toDate = fromDate.with(TemporalAdjusters.lastDayOfMonth());
                break;
        }
        System.out.println(fromDate+" "+toDate);
        return appointmentService.availableSlots(fromDate,toDate);
    }

    @PostMapping("/schedule")
    public Appointment bookAppointment(@RequestBody Appointment appointment){
        String generateAppointmentId = UUID.randomUUID().toString().substring(0,7);
        appointment.setAppointmentId(generateAppointmentId);
        return appointmentService.book(appointment);

    }

    @GetMapping("/history/{patientId}")
    public List<Appointment> bookingHistoryOfAPatient(@PathVariable String patientId){
        return  appointmentService.fetchForAParticularPatient(patientId);
    }
}
