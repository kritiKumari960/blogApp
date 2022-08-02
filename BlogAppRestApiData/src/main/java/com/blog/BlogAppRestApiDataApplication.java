package com.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class BlogAppRestApiDataApplication {

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				// TODO Auto-generated method stub
				registry.addMapping("/*").allowedHeaders("*").allowedOriginPatterns("*").allowCredentials(true);
				WebMvcConfigurer.super.addCorsMappings(registry);
			}
		};
		
	} 
	
	public static void main(String[] args) {
		SpringApplication.run(BlogAppRestApiDataApplication.class, args);
	}

}
