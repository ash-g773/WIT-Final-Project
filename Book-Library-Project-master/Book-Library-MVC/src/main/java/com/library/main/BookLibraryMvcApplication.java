package com.library.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages = "com.library")
@EntityScan(basePackages = "com.library.entity")
@EnableJpaRepositories(basePackages = "com.library.persistence")
public class BookLibraryMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookLibraryMvcApplication.class, args);
	}
	
	@Bean
	public RestTemplate gettemplate() {
		return new RestTemplate();
	}
}
