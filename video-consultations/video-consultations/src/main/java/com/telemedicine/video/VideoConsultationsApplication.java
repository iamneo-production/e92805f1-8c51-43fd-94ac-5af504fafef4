package com.telemedicine.video;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class VideoConsultationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(VideoConsultationsApplication.class, args);
	}

}
