package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
({"com.example.demo.controller",
	"com.example.demo.db",
	"com.example.demo.entity",
	"com.example.demo.model"})
public class SalesManagerSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalesManagerSpringApplication.class, args);
	}

}
