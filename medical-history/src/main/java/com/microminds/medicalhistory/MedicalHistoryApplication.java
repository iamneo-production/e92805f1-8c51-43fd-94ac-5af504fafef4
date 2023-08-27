package com.microminds.medicalhistory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MedicalHistoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicalHistoryApplication.class, args);
	}

}
