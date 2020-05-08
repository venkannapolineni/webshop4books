package com.genpact.webshop;

import com.genpact.webshop.config.RestConfig;
import com.genpact.webshop.controller.BookController;
import com.genpact.webshop.controller.LibraryController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@ComponentScan(basePackages = {"com.genpact.webshop.service",
				"com.genpact.webshop.controller",
				"com.genpact.webshop.repository"}
				)
@SpringBootApplication
@RestController
public class WebshopApplication { //extends SpringBootServletInitializer {

	public static void main(String[] args) {
       SpringApplication.run(WebshopApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/*").allowedOrigins("http://localhost:8080");
			}
		};
	}
}
