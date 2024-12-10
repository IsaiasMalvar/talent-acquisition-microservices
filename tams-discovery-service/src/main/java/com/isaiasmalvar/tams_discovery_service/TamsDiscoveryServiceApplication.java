package com.isaiasmalvar.tams_discovery_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class TamsDiscoveryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TamsDiscoveryServiceApplication.class, args);
	}

}
