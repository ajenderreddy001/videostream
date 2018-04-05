package com.example.videostream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class VideostreamApplication {

	public static void main(String[] args) {
	    SpringApplication.run(VideostreamApplication.class, args);
	}
}
