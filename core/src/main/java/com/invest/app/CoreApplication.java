package com.invest.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.invest.app.user_operator.repository.UserRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@SpringBootApplication
@ComponentScan(basePackages = {"com.invest.app.core", "com.invest.app.user_operator"})
@EnableJpaRepositories(basePackages = {"com.invest.app.user_operator.repository"})
public class CoreApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
	}

}
