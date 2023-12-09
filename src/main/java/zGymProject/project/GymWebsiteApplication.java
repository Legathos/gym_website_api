package zGymProject.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;



@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class  GymWebsiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(GymWebsiteApplication.class, args);
	}

}
