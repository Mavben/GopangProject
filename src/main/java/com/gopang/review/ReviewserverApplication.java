package com.gopang.review;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.gopang.review.service")
public class ReviewserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReviewserverApplication.class, args);
	}

}
