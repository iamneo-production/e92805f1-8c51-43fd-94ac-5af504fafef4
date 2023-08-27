package com.telemedicine.video.proxy;

import java.util.List;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.telemedicine.video.config.LoadBalancerConfiguration;
import com.telemedicine.video.dto.Appointment;

@FeignClient(name = "AppointmentProxy", url = "${appointment.feign.url}")
@LoadBalancerClient(name = "AppointmentProxy", configuration = LoadBalancerConfiguration.class)
public interface AppointmentProxy {

	@GetMapping(value = "/history/{patientId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Appointment>> getAppointmentByPatientId(@PathVariable("patientId") String patientId);

}

