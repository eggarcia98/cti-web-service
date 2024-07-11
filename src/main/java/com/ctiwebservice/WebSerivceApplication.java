package com.ctiwebservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.ctiwebservice.model")
public class WebSerivceApplication {
	  public static void main(String[] args) {
		    SpringApplication.run(WebSerivceApplication.class);
		  }

}
