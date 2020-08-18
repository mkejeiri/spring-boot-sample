package com.mkejeiri.appjokes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.mkejeiri.appjokes"}) //this is default no need to specify this, kept for training purpose only
public class AppjokesApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppjokesApplication.class, args);
	}

}
