package com.billing.payment.hackthon.controller;

import java.io.ByteArrayInputStream;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.billing.payment.hackthon.request.InvoiceRequest;
import com.billing.payment.hackthon.service.BillingService;

@RestController
@RequestMapping("/api/v1/billing")
public class BillingController {

	@Autowired
	private BillingService billingService;
	
	@RequestMapping("/health")
	public String health() {
		return "Billing service is up, "+ new Date();
	}

	@GetMapping(value = "/invoices",
            produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> generateInvoices() {

		ByteArrayInputStream bis = billingService.generateInvoices();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=invoices.pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));

	}
	
	@PostMapping(value = "/patients/invoice",
            produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> generateInvoiceByPatientIds(@RequestBody InvoiceRequest request) {

		ByteArrayInputStream bis = billingService.generateInvoiceByPatientId(request.getPatientIds());

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=invoices.pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));

	}
}
