package com.mshackathon.patientprofileservice.serviceimpl;

import com.mshackathon.patientprofileservice.dao.PatientProfileRepository;
import com.mshackathon.patientprofileservice.dto.PatientProfileDto;
import com.mshackathon.patientprofileservice.entity.Document;
import com.mshackathon.patientprofileservice.entity.PatientProfile;
import com.mshackathon.patientprofileservice.exception.PatientNotFoundException;
import com.mshackathon.patientprofileservice.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientProfileRepository patientProfileRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public void savePatientProfile(PatientProfileDto patientProfileDto) {
        PatientProfile patientProfile = this.modelMapper.map(patientProfileDto,PatientProfile.class);
        List<Document> documents = patientProfileDto.getDocuments().stream()
                .map(documentDto -> {
                    Document document = this.modelMapper.map(documentDto, Document.class);
                    document.setPatient(patientProfile);
                    return document;
                })
                .collect(Collectors.toList());

        patientProfile.setDocuments(documents);
        log.info("Patient profile details = {}",patientProfile);
        this.patientProfileRepository.save(patientProfile);
        log.info("Patient profile was saved successfully");
    }
    @Override
    public PatientProfileDto viewPatientProfile(String patientId) {
        PatientProfile patientProfile =  this.patientProfileRepository.findById(patientId).orElseThrow(()-> new PatientNotFoundException("No patient found with given id"));
        PatientProfileDto patientProfileDto = this.modelMapper.map(patientProfile,PatientProfileDto.class);
        return patientProfileDto;
    }
    @Override
public void updatePatientProfile(PatientProfileDto patientProfileDto,String patientId) {
        PatientProfile patientProfile = this.modelMapper.map(patientProfileDto,PatientProfile.class);
        log.info("Patient profile details = {}",patientProfile);
        PatientProfile localPatientProfile = this.patientProfileRepository.findById(patientId).orElseThrow(()->new PatientNotFoundException("No patient found with given id"));
        localPatientProfile.setPatientName(patientProfile.getPatientName());
        localPatientProfile.setPatientEmail(patientProfile.getPatientEmail());
        localPatientProfile.setPatientAge(patientProfile.getPatientAge());
        localPatientProfile.setPatientNumber(patientProfile.getPatientNumber());
        localPatientProfile.setDob(patientProfile.getDob());
        localPatientProfile.setDocuments(patientProfile.getDocuments());
        localPatientProfile.setHistory(patientProfile.getHistory());
        this.patientProfileRepository.save(localPatientProfile);
    }
}
