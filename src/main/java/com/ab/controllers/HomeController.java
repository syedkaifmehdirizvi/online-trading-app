package com.ab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.ab.services.UserService;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;
	
    @GetMapping("/")
    public String showHomePage() {
        return "home";
    }
}
