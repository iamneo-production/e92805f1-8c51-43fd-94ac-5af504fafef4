package com.telemedicine.video.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//import com.telemedicine.video.repository.VideoConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.telemedicine.video.dto.Appointment;
import com.telemedicine.video.dto.ConsultationDTO;
//import com.telemedicine.video.entity.Consultation;
import com.telemedicine.video.entity.Prescription;
import com.telemedicine.video.payload.PrescriptionRequest;
import com.telemedicine.video.proxy.AppointmentProxy;
import com.telemedicine.video.repository.PrescriptionRepository;

@Service
public class VideoConsultationService {

	@Autowired
	private AppointmentProxy appointmentProxy;

	@Autowired
	private PrescriptionRepository prescriptionRepository;

	public ConsultationDTO getVideoConsultation(String patientId) {
		ResponseEntity<List<Appointment>> appointmentResponse = appointmentProxy.getAppointmentByPatientId(patientId);
		ConsultationDTO dto = new ConsultationDTO();
		if (Objects.nonNull(appointmentResponse) && Objects.nonNull(appointmentResponse.getBody())) {
			for (Appointment appoint : appointmentResponse.getBody()) {
				dto = new ConsultationDTO();
				dto.setPatientName(appoint.getPatientEmail());
				dto.setDate(appoint.getAppointmentDate());
				dto.setTime(appoint.getAppointmentTime());
				dto.setVideoUrl("https://us05web.zoom.us/j/81005889339?pwd=Jsr6q5V0XVsebQuGOHPWJl9WCOzbRF.1");
			}

			return dto;
		}

		return dto;
	}

	public Prescription createPrescription(String patientId, String doctorId, PrescriptionRequest prescriptionRequest) {
		Prescription prescription = new Prescription();
		prescription.setPatientId(patientId);
		prescription.setDoctorId(doctorId);
		prescription.setDate(LocalDate.now());
		prescription.setMedicines(prescriptionRequest.getMedicines());
		return prescriptionRepository.save(prescription);
	}

	public List<Prescription> getPrescriptions(String patientId) {
		List<String> patientIds = new ArrayList<>();
		patientIds.add(patientId);
		List<Prescription> prescription = prescriptionRepository.findAllById(patientIds);
		return prescription;
	}
}
