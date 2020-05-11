package com.cg.dd.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cg.dd.app.service.EmailService;
import com.cg.dd.app.service.EmailServiceImp;
import com.cg.dd.app.service.UserService;
import com.cg.dd.app.service.UserServiceImp;

@SpringBootApplication
public class AuthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthServiceApplication.class, args);
	}

	@Bean
	public UserService getUserService() {
		return new UserServiceImp();
	}

	@Bean
	public EmailService getEmailService() {
		return new EmailServiceImp();
	}

	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
