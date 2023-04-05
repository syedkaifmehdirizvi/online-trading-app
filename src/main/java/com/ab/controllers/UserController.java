package com.ab.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ab.entities.Trade;
import com.ab.entities.User;
import com.ab.services.TradeService;
import com.ab.services.UserService;


@Controller
public class UserController 
{
	@Autowired
    private TradeService tradeService;
	
	@Autowired
    private UserService userService;

    @GetMapping("/users/{userId}/trades")
    public String getTradeHistoryForUser(@PathVariable int userId, Model model) 
    {
        User user = userService.getUserById(userId);
        List<Trade> tradeHistory = tradeService.getTradeHistoryForUser(user);
        model.addAttribute("user", user);
        model.addAttribute("tradeHistory", tradeHistory);
        return "trade-history"; // return the name of your view (trade-history.html)
    }
    
    @PostMapping("/users/{userId}/trades")
    public String addTradeForUser(@PathVariable int userId, @ModelAttribute Trade trade) 
    {
        User user = userService.getUserById(userId);
        tradeService.addTradeForUser(user, trade);
        return "redirect:/users/" + userId + "/trades";
    }
    
    
}
