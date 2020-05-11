package com.cg.dd.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
/**
 * @author Atal_kumar
 * May 05, 2020
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
public class RawMaterialOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(RawMaterialOrderApplication.class, args);
	}

}
