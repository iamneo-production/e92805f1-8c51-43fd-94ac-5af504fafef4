package com.billing.payment.hackthon.service;

import com.billing.payment.hackthon.dto.PaymentPatientDTO;
import com.billing.payment.hackthon.dto.PaymentDTO;

public interface PaymentService {

	public PaymentDTO paymentByPatintId(PaymentPatientDTO patientDTO);

	public PaymentDTO trackPayment(String paymentId);

}
