package com.course.jpa.hibernate;


import com.course.jpa.hibernate.sections.section9.entity.FullTimeEmployee;
import com.course.jpa.hibernate.sections.section9.entity.PartTimeEmployee;
import com.course.jpa.hibernate.sections.section9.repository.EmployeeRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.util.Arrays;

@SpringBootApplication
@Slf4j
public class Application implements CommandLineRunner {

	@Autowired
	private CurrencyConfiguration currencyConfiguration;

	@Autowired
	private Environment env;

	@Autowired
	private EmployeeRepo employeeRepo;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// This gets called when application context is ready
		// meaning we have the spring bean and dependencies available
		if(Arrays.toString(env.getActiveProfiles()).contains("dev")) {

			String swaggerUrl = env.getProperty("server.port") + "/api/v1/swagger-ui/index.html";
			String actuatorUrl = env.getProperty("server.port") + "/api/v1/actuator";


			System.out.println("Swagger Url : "+swaggerUrl);
			System.out.println("Actuator Url : "+actuatorUrl);

			// ----- Testing configuration properties ----------
			log.info("Currency configuration : {} ",currencyConfiguration);

//			employeeRepo.insert(new FullTimeEmployee("John","20000"));
//			employeeRepo.insert(new PartTimeEmployee("Lamim", "40"));
//
//			log.info("Employee Information : {} ",employeeRepo.getAllEmployee());
		}
	}
}
