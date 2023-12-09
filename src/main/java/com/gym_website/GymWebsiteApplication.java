package com.gym_website;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;



@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class  GymWebsiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(GymWebsiteApplication.class, args);
	}

}
