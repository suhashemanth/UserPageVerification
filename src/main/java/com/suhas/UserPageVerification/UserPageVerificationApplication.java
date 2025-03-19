package com.suhas.UserPageVerification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

@SpringBootApplication
public class UserPageVerificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserPageVerificationApplication.class, args);

	}

}
