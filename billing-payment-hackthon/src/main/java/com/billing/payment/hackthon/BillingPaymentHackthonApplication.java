package com.billing.payment.hackthon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BillingPaymentHackthonApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingPaymentHackthonApplication.class, args);
	}

}