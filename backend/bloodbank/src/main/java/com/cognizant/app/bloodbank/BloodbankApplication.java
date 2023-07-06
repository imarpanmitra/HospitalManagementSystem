package com.cognizant.app.bloodbank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class BloodbankApplication {

	public static void main(String[] args) {
		SpringApplication.run(BloodbankApplication.class, args);
	}
	
//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurer() {
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/**").allowedMethods("*").allowedOrigins("*");
//			}
//		};
//	}
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
