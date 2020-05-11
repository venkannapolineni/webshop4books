package com.genpact.webshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@ComponentScan(basePackages = {"com.genpact.webshop.service",
				"com.genpact.webshop.controller",
				"com.genpact.webshop.repository"}
				)
@SpringBootApplication
//@EnableWebMvc
public class WebshopApplication {

	public static void main(String[] args) {
       SpringApplication.run(WebshopApplication.class, args);
	}

	/*@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*");
			}
		};
	}*/
}
