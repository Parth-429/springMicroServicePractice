package com.example.springMicroservices.productService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ProductServiceAppliaction {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceAppliaction.class, args);
	}

}
