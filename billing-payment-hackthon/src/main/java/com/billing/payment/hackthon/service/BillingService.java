package com.billing.payment.hackthon.service;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface BillingService {

	public ByteArrayInputStream generateInvoices();

	public ByteArrayInputStream generateInvoiceByPatientId(List<String> patientIds);

}
