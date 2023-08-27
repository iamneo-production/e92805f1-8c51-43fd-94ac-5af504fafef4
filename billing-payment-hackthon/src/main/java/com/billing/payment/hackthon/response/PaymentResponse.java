package com.billing.payment.hackthon.response;

import com.billing.payment.hackthon.dto.PaymentDTO;

public class PaymentResponse {

	private PaymentDTO payment;

	public PaymentDTO getPayment() {
		return payment;
	}

	public void setPayment(PaymentDTO payment) {
		this.payment = payment;
	}
	
}
