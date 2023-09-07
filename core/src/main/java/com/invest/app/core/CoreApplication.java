package com.invest.app.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.invest.app.user_operator.repository.UserRepository;

@SpringBootApplication
@ComponentScan(basePackages = {"com.invest.app", "com.invest.app.user_operator"})
public class CoreApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
	}

}
