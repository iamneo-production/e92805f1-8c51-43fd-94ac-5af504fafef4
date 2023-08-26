package com.hackathon.appointments.service;

import com.hackathon.appointments.entity.Appointment;
import com.hackathon.appointments.exception.DuplicateEntryException;
import com.hackathon.appointments.exception.ResourceNotFoundException;
import com.hackathon.appointments.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${notification-service.base-url}")
    private String urlOfNotificationManagementService;

    public Map<LocalDate, Map<LocalTime,String>> availableSlots(LocalDate fromDate, LocalDate toDate) {
        Map<LocalDate, Map<LocalTime,String>> allSlots = new TreeMap<>();

        for (LocalDate date = fromDate; !date.isAfter(toDate); date = date.plusDays(1)) {
            List<LocalTime> bookedSlots = bookedSlotForAParticularDate(date);

            LocalTime startTime = LocalTime.of(9,0);
            LocalTime endTime = LocalTime.of(16,0);
            int duration = 30;

            LocalTime currentTime = startTime;
            TreeMap<LocalTime,String> slotWithAvailability = new TreeMap<>();
            while (currentTime.isBefore(endTime)){
                slotWithAvailability.put(currentTime,"available");
                currentTime = currentTime.plusMinutes(duration);
            }
            // traverse the map and set already available date in slot to booked
            for(LocalTime slot : bookedSlots){
                if(slotWithAvailability.containsKey(slot)) slotWithAvailability.put(slot,"booked");
            }
            allSlots.put(date,slotWithAvailability);

        }
        return allSlots;

    }


    public List<Appointment> fetchForAParticularPatient(String patientId) {
        List<Appointment> appointmentList = appointmentRepository.findByPatientId(patientId);
        if(appointmentList.isEmpty()) throw new ResourceNotFoundException("Patient with id: "+patientId+" not found!");
        return appointmentList;
    }

    public Appointment book(Appointment appointment) {
        if(appointmentRepository.existsByAppointmentDateAndAppointmentTime(appointment.getAppointmentDate(),appointment.getAppointmentTime()))
            throw new DuplicateEntryException("Slot: "+appointment.getAppointmentTime()+" not available for date: "+appointment.getAppointmentDate());
        Appointment savedAppointment = appointmentRepository.save(appointment);
        makeACallToNotificationService(savedAppointment);
        return savedAppointment;
    }


    private List<LocalTime> bookedSlotForAParticularDate(LocalDate date){
        List<LocalTime> bookedSlots = new ArrayList<>();
        List<Appointment> bookedAppointmentListForProvidedDate = appointmentRepository.findByAppointmentDate(date);
        for (Appointment appointment: bookedAppointmentListForProvidedDate){
            bookedSlots.add(appointment.getAppointmentTime());
        }
        return bookedSlots;
    }
    private void makeACallToNotificationService(Appointment appointment){
        // make a http call to entity-manager
        String uri = urlOfNotificationManagementService+"/api/v1/schedule/notify";
        String addedEmployeeFromEntityManagerService =
                restTemplate.postForEntity(uri,appointment,String.class).getBody();
    }
}
