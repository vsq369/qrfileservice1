package com.cecurs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.cecurs"})
@MapperScan("com.cecurs.dao")
@EnableEurekaClient
@EnableCircuitBreaker
@EnableHystrix
public class QrfileserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(QrfileserviceApplication.class, args);
	}

}
