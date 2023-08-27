package com.mshackathon.patientprofileservice.controller;


import com.mshackathon.patientprofileservice.dto.PatientProfileDto;
import com.mshackathon.patientprofileservice.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/profile")
public class PatientProfileController {
    @Autowired
    private PatientService patientService;

    @PostMapping("/create")
    public ResponseEntity<String> createPatientProfile(@RequestBody PatientProfileDto patientProfileDto){
        patientService.savePatientProfile(patientProfileDto);
        return new ResponseEntity<>("Patient profile was saved successfully.", HttpStatus.CREATED);
    }

    @GetMapping("/view/{patientId}")
    public ResponseEntity<PatientProfileDto> viewPatientProfile(@PathVariable("patientId") String patientId){
        return new ResponseEntity<>(patientService.viewPatientProfile(patientId),HttpStatus.OK);
    }

    @PutMapping("/update/{patientId}")
    public ResponseEntity<String> editPatientProfile(@PathVariable("patientId")String patientId,@RequestBody PatientProfileDto patientProfileDto){
        this.patientService.updatePatientProfile(patientProfileDto,patientId);
        return new ResponseEntity<>("Patient profile was saved successfully.", HttpStatus.OK);
    }
}
