package com.cavisson.ata.main;

/**
 * @author Vishal Singh

 *
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.web.WebApplicationInitializer;

@ServletComponentScan("com.cavisson.ata.services")
@SpringBootApplication(scanBasePackages = "com.cavisson.ata")
@EnableReactiveMongoRepositories("com.cavisson.ata.utils")
public class Application extends SpringBootServletInitializer implements WebApplicationInitializer {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(Application.class);
	}
}