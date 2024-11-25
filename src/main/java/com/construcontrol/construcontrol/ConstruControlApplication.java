package com.construcontrol.construcontrol;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients
@SpringBootApplication
public class ConstruControlApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConstruControlApplication.class, args);

	}

}
