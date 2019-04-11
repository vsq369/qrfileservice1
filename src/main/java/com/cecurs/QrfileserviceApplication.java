package com.cecurs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.cecurs"})
@MapperScan("com.cecurs.dao")
public class QrfileserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(QrfileserviceApplication.class, args);
	}

}
