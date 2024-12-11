package com.isaiasmalvar.career_portal_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class CareerPortalServiceApplication {

	@Value("${spring.application.name}")
	private String serviceName;

	@Autowired
	private Environment environment;

	@GetMapping("/test")
	public String getTest(){
		return "testing " + serviceName + "at port: " + environment.getProperty("local.server.port");
	}

	public static void main(String[] args) {
		SpringApplication.run(CareerPortalServiceApplication.class, args);
	}

}
