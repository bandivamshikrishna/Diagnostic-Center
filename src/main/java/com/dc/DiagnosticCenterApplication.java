package com.dc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DiagnosticCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiagnosticCenterApplication.class, args);
	}

}
