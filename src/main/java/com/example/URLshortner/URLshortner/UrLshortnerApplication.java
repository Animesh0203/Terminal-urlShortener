package com.example.URLshortner.URLshortner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class UrLshortnerApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrLshortnerApplication.class, args);
	}

}
