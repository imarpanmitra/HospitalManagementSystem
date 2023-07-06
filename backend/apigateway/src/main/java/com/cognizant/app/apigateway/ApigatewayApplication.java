package com.cognizant.app.apigateway;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@SpringBootApplication
public class ApigatewayApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ApigatewayApplication.class);
        app.setDefaultProperties(Collections
          .singletonMap("server.port", "8083"));
        app.run(args);
	}

	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder) {
		return routeLocatorBuilder.routes()
				.route(r -> r.path("/hospitals/**", "/authenticate/**", "/employees/**").uri("http://localhost:8080"))
				.route(r -> r.path("/bloodbank/**").uri("http://localhost:8082")).build();
	}
	
	 @Bean
	    public CorsWebFilter corsWebFilter() {

	        final CorsConfiguration corsConfig = new CorsConfiguration();
	        corsConfig.setAllowedOrigins(Collections.singletonList("*"));
	        corsConfig.setMaxAge(3600L);
	        corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT"));
	        corsConfig.addAllowedHeader("*");

	        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", corsConfig);

	        return new CorsWebFilter(source);
	    }  


}
