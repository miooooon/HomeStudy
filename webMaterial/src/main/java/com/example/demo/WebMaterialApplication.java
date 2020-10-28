package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
@SpringBootApplication
@EnableJdbcRepositories
//@EntityScan("com.example.demo.entity") 
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class WebMaterialApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebMaterialApplication.class, args);
	}

}
