package ru.petrov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class ExecutionTrackingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExecutionTrackingApplication.class, args);
	}


}
