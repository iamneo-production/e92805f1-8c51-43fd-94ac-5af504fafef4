package com.billing.payment.hackthon.service.impl;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.billing.payment.hackthon.dto.BillingDTO;
import com.billing.payment.hackthon.service.BillingPaymentService;
import com.billing.payment.hackthon.util.InvoicePdfGenerator;

@Service
public class BillingPaymentServiceImpl implements BillingPaymentService{

	@Override
	public ByteArrayInputStream getInvoice() {
		// TODO Auto-generated method stub
		ByteArrayInputStream stream = InvoicePdfGenerator.invoicePDFReport(getBill());
		return stream;
	}

	private List<BillingDTO> getBill() {
		List<BillingDTO> dtos = new ArrayList<>();
		
		BillingDTO dto = new BillingDTO();
		dto.setPatientId("123");
		dto.setInvoiceId("invoice11132");
		dto.setAmount("5000.00");
		dto.setItem("Full Check Up");
		dto.setPatientName("Ram");
		dto.setDescription("Full body checkup ");
		
		dtos.add(dto);
		
		dto = new BillingDTO();
		dto.setPatientId("32111");
		dto.setInvoiceId("invoice2231");
		dto.setAmount("2500.00");
		dto.setItem("General Check Up");
		dto.setPatientName("Rahul");
		dto.setDescription("General checkup");
		
		dtos.add(dto);
		return dtos;
	}
}
