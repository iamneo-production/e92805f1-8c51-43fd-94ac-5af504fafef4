package com.billing.payment.hackthon.controller;

import java.util.Date;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.billing.payment.hackthon.dto.PaymentPatientDTO;
import com.billing.payment.hackthon.dto.PaymentDTO;
import com.billing.payment.hackthon.response.PaymentResponse;
import com.billing.payment.hackthon.service.PaymentService;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;

	@RequestMapping("/health")
	public String health() {
		return "Payment service is up, "+new Date();
	}

	@PostMapping(value = "/patient",
            produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PaymentDTO> paymentByPatintId(@RequestBody PaymentPatientDTO patientDTO) {
		PaymentDTO paymentDTO = new PaymentDTO();
		if(Objects.nonNull(patientDTO))
			paymentDTO = paymentService.paymentByPatintId(patientDTO);
		
		return ResponseEntity.ok().body(paymentDTO);
	}
	
	@GetMapping(value = "/track/{paymentId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PaymentResponse> trackPaymentByPaymentId(@PathVariable("paymentId") String paymentId) {
		PaymentResponse response = new PaymentResponse();
		PaymentDTO paymentDTO = paymentService.trackPayment(paymentId);
		response.setPayment(paymentDTO);
		return ResponseEntity.ok().body(response);
	}
}
