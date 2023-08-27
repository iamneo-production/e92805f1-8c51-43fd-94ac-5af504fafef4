package com.billing.payment.hackthon.service.impl;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.billing.payment.hackthon.dto.BillingDTO;
import com.billing.payment.hackthon.entity.Invoice;
import com.billing.payment.hackthon.repository.InvoiceRepository;
import com.billing.payment.hackthon.service.BillingService;
import com.billing.payment.hackthon.util.BillingPymentUtil;
import com.billing.payment.hackthon.util.InvoicePdfGenerator;

@Service
public class BillingServiceImpl implements BillingService {
	
	private static Logger LOG = LoggerFactory.getLogger(BillingServiceImpl.class);

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Override
	public ByteArrayInputStream generateInvoiceByPatientId(List<String> patientIds) {
		List<Invoice> invoices = invoiceRepository.findByPatientIdIn(patientIds);
		List<BillingDTO> dtos = new ArrayList<>();
		invoices.stream().forEach(invoice -> {
			BillingDTO dto = new BillingDTO();
			dto.setInvoiceId(invoice.getId());
			dto.setAmount(String.valueOf(invoice.getAmount()));
			dto.setDate(BillingPymentUtil.getDateToString(invoice.getInvoiceDate()));
			dto.setDescription(invoice.getItem());
			dto.setPatientId(invoice.getPatientId());
			dto.setPatientName(invoice.getPatientName());
			dto.setItem(invoice.getItem());

			dtos.add(dto);

		});

		ByteArrayInputStream stream = InvoicePdfGenerator.invoicePDFReport(dtos);
		LOG.debug("generateInvoiceByPatientId for {0} patients", dtos.size());
		return stream;
	}

	@Override
	public ByteArrayInputStream generateInvoices() {
		List<Invoice> invoices = invoiceRepository.findAll();
		List<BillingDTO> dtos = new ArrayList<>();
		invoices.stream().forEach(invoice -> {
			BillingDTO dto = new BillingDTO();
			dto.setInvoiceId(invoice.getId());
			dto.setAmount(String.valueOf(invoice.getAmount()));
			dto.setDate(BillingPymentUtil.getDateToString(invoice.getInvoiceDate()));
			dto.setDescription(invoice.getItem());
			dto.setPatientId(invoice.getPatientId());
			dto.setPatientName(invoice.getPatientName());
			dto.setItem(invoice.getItem());

			dtos.add(dto);

		});
		
		LOG.debug("generateInvoices for {0} patients", dtos.size());
		ByteArrayInputStream stream = InvoicePdfGenerator.invoicePDFReport(dtos);
		return stream;
	}

}
