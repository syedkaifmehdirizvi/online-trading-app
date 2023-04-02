package com.ab.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

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
	public ModelAndView getTradeHistoryForUser(@PathVariable int userId) {
	    User user = userService.getUserById(userId);
	    List<Trade> tradeHistory = tradeService.getTradeHistoryForUser(user);
	    ModelAndView mav = new ModelAndView("trade-history");
	    mav.addObject("user", user);
	    mav.addObject("tradeHistory", tradeHistory);
	    return mav;
	}

    
    @PostMapping("/users/{userId}/trades")
    public String addTradeForUser(@PathVariable int userId, @ModelAttribute Trade trade) 
    {
        User user = userService.getUserById(userId);
        tradeService.addTradeForUser(user, trade);
        return "redirect:/users/" + userId + "/trades";
    }
    
    
}
