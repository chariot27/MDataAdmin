package com.chariot.nm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class NmApplication {

	public static void main(String[] args) {
		SpringApplication.run(NmApplication.class, args);
	}

}
