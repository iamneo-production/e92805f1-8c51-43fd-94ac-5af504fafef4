package com.hackathon.appointments.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import  com.hackathon.appointments.model.*;
import java.util.*;

@FeignClient("USER-MANAGEMENT")
public interface UserManagementRepository {
    @GetMapping("/api/v1/actor/doctors")
    public DoctorResponse getDoctors();
    
}
