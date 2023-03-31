package com.ab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ab.entities.User;
import com.ab.services.UserService;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
        User existingUser = userService.getUserByUsername(username);
        if (existingUser != null) {
            model.addAttribute("message", "Username already exists. Please choose a different one.");
            return "register";
        }
        userService.registerUser(username, password);
        model.addAttribute("message", "Registration successful. Please login.");
        return "login";
    }
}