package com.emazon.transaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class EmazonTransactionApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmazonTransactionApplication.class, args);
	}

}
