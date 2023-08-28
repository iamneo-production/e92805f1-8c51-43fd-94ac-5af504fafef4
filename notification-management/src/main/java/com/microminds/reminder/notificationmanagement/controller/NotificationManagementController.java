package com.microminds.reminder.notificationmanagement.controller;

import com.microminds.reminder.notificationmanagement.dao.Appointment;
import com.microminds.reminder.notificationmanagement.dao.EmailDto;
import com.microminds.reminder.notificationmanagement.service.NotificationManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/schedule")
public class NotificationManagementController {

    @Autowired
    private NotificationManagementService notificationManagementService;
    @PostMapping("/notify")
    ResponseEntity<String> createDynamicMessage(@RequestBody Appointment appointment){
        String subject = "Appointment scheduled on "+ appointment.getAppointmentDate()+ " at " + appointment.getAppointmentTime();
        String message = appointment.getAppointmentType() +" appointment for Patient ID " + appointment.getPatientId()+ " was successfully created by "+ appointment.getBookedBy()+ " with doctor ID "+appointment.getDoctorId()+ " on "+appointment.getAppointmentDate() + " at "+appointment.getAppointmentTime();
        String to = appointment.getPatientEmail();
        EmailDto emailDto = EmailDto.builder().subject(subject).message(message).to(to).build();
        notifyCreatedSchedule(emailDto);
        return  ResponseEntity.ok("user notified successfully");

    }

   private boolean notifyCreatedSchedule(EmailDto emailDto){
        return notificationManagementService.sendEmail(emailDto.getSubject(),emailDto.getMessage(),emailDto.getTo());
    }
}
