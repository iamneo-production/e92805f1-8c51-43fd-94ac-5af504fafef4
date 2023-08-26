package com.billing.payment.hackthon.controller;

import java.io.ByteArrayInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.billing.payment.hackthon.service.BillingPaymentService;

@RestController
@RequestMapping("/api/v1")
public class BillingPaymentController {

	@RequestMapping("/billing")
	public String getBilling() {
		return "Billing in progress.....";
	}

	@Autowired
	private BillingPaymentService service;

	@GetMapping(value = "/invoices/{patientId}",
            produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> generateInvoice(@PathVariable("patientId") String patientIds) {

		ByteArrayInputStream bis = service.getInvoice();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=invoices.pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));

	}
}
