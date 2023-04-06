package com.ab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ab.services.RandomTeamNameGenerator;
import com.ab.services.UserService;

@Controller
public class HomeController {

    @Autowired
    private RandomTeamNameGenerator randomTeamNameGenerator;
	
    @GetMapping("/")
    public String showHomePage(Model model) {
    	model.addAttribute("teamName", randomTeamNameGenerator.generateRandomTeamName());
        return "home";
    }
}