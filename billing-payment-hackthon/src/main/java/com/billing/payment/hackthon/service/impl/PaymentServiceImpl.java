package com.billing.payment.hackthon.service.impl;

import java.util.Date;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.billing.payment.hackthon.constants.BillingPaymentContants;
import com.billing.payment.hackthon.dto.PatientDTO;
import com.billing.payment.hackthon.dto.PaymentDTO;
import com.billing.payment.hackthon.dto.PaymentPatientDTO;
import com.billing.payment.hackthon.entity.Invoice;
import com.billing.payment.hackthon.entity.Payment;
import com.billing.payment.hackthon.proxy.FeignUserManagementProxy;
import com.billing.payment.hackthon.repository.InvoiceRepository;
import com.billing.payment.hackthon.repository.PaymentRepository;
import com.billing.payment.hackthon.response.PatientResponse;
import com.billing.payment.hackthon.service.PaymentService;
import com.billing.payment.hackthon.util.BillingPymentUtil;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class PaymentServiceImpl implements PaymentService {

	private static Logger LOG = LoggerFactory.getLogger(PaymentServiceImpl.class);

	@Autowired
	private FeignUserManagementProxy userManagementProxy;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Override
	public PaymentDTO paymentByPatintId(PaymentPatientDTO patientDTO) {

		ResponseEntity<PatientResponse> patientResponse = getPatientsFromUserManageentById(patientDTO.getPatientId(),
				patientDTO.getToken());

		if (Objects.nonNull(patientResponse) && Objects.nonNull(patientResponse.getBody())) {
			PatientDTO userPatientDTO = patientResponse.getBody().getPatient();
			if (Objects.nonNull(userPatientDTO) && Objects.nonNull(userPatientDTO.getPatientId())) {
				LOG.debug("Received response from external client patientId={0}, patientName={1}",
						userPatientDTO.getPatientId(), userPatientDTO.getPatientName());
				var persistPayment = mapDTOToEntity(patientDTO, userPatientDTO.getPatientName());
				Payment savedPayment = paymentRepository.save(persistPayment);
				var invoice = mapInvoiceEntity(savedPayment);
				Invoice savedInvoice = invoiceRepository.save(invoice);
				LOG.debug("Invoice is created invoiceId={0}, patientId={1}", savedInvoice.getId(),
						savedInvoice.getPatientId());
				var payment = mapEntityToDTO(savedPayment);
				return payment;
			}
		}

		return new PaymentDTO();
	}

	@Override
	public PaymentDTO trackPayment(String paymentId) {
		Payment entityPymt = paymentRepository.findById(paymentId).orElse(new Payment());
		if (Objects.nonNull(entityPymt) && Objects.nonNull(entityPymt.getPatientId())) {
			PaymentDTO payment = new PaymentDTO();
			payment.setPymtId(entityPymt.getId());
			payment.setPymtDate(BillingPymentUtil.getDateToString(entityPymt.getPymtDate()));
			payment.setPymtAmount(String.valueOf(entityPymt.getAmount()));
			payment.setStatus(entityPymt.getStatus());
			LOG.debug("trackPayment paymentId={0}, status={1}", payment.getPymtId(), payment.getStatus());
			return payment;
		}

		return new PaymentDTO();
	}

	@CircuitBreaker(name = "userMangementServiceCircuitBreaker", fallbackMethod = "patientByPatientIdFromUserMnagement")
	private ResponseEntity<PatientResponse> getPatientsFromUserManageentById(String patientId, String token) {
		ResponseEntity<PatientResponse> patientResponse = userManagementProxy.getPatientById(patientId,
				BillingPaymentContants.BEARER + token);
		return patientResponse;
	}

	public ResponseEntity<PatientResponse> patientByPatientIdFromUserMnagement(String patientId, Exception ex) {
		return ResponseEntity.ok().body(new PatientResponse());
	}

	private Invoice mapInvoiceEntity(Payment savedPayment) {
		var invoice = new Invoice();
		invoice.setId(savedPayment.getId());
		invoice.setPatientId(savedPayment.getPatientId());
		invoice.setAmount(savedPayment.getAmount());
		invoice.setItem(savedPayment.getItem());
		invoice.setInvoiceDate(savedPayment.getPymtDate());
		invoice.setPatientName(savedPayment.getPatientName());
		return invoice;
	}

	private PaymentDTO mapEntityToDTO(Payment savedPayment) {
		var payment = new PaymentDTO();
		payment.setPymtId(savedPayment.getId());
		payment.setPymtAmount(String.valueOf(savedPayment.getAmount()));
		payment.setPymtDate(BillingPymentUtil.getDateToString(savedPayment.getPymtDate()));
		payment.setItem(savedPayment.getItem());
		payment.setStatus(BillingPaymentContants._PROCESSING);
		return payment;
	}

	private Payment mapDTOToEntity(PaymentPatientDTO patientDTO, String userName) {
		var payment = new Payment();
		payment.setId(BillingPymentUtil.generatePymtId());
		payment.setPatientId(patientDTO.getPatientId());
		payment.setPatientName(userName);
		payment.setItem(patientDTO.getItem());
		payment.setAmount(patientDTO.getAmount() != null ? Double.parseDouble(patientDTO.getAmount()) : 0.0);
		payment.setPymtDate(BillingPymentUtil.toDate(new Date()));
		payment.setStatus(BillingPaymentContants._SUCCESS);
		return payment;
	}

}
