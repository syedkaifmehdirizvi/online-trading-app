package com.ab.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController 
{
	@GetMapping("/")
	public String hello() 
	{
		return "Welcome to Spring Boot App running on AWS EC2 instance";
	}
}
