package com.feignclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FeignClientConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeignClientConfigApplication.class, args);
	}

}
