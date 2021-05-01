package org.perom.trainercalculator.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class TrainerCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrainerCalculatorApplication.class, args);
	}

}