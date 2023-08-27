package com.billing.payment.hackthon.proxy;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.billing.payment.hackthon.config.LoadBalancerConfiguration;
import com.billing.payment.hackthon.constants.BillingPaymentContants;
import com.billing.payment.hackthon.response.PatientResponse;

@FeignClient("USER-MANAGEMENT")
@LoadBalancerClient(name = "FeignUserManagementProxy", configuration = LoadBalancerConfiguration.class)
public interface FeignUserManagementProxy {

	@GetMapping(value = "/api/v1/actor/patient/{patientId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PatientResponse> getPatientById(@PathVariable("patientId") String patientId,
			@RequestHeader(BillingPaymentContants.AUTHORIZATION) String bearerToken);

}
