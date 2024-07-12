package com.ctiwebservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.ctiwebservice")
@EntityScan(basePackages = "com.ctiwebservice.model")
@EnableJpaRepositories(basePackages = "com.ctiwebservice.repository")
public class WebSerivceApplication {
	  public static void main(String[] args) {
		    SpringApplication.run(WebSerivceApplication.class);
		  }

}
