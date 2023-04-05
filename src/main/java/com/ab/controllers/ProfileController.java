package com.ab.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ab.entities.Order;
import com.ab.entities.Trade;
import com.ab.entities.User;
import com.ab.services.OrderService;
import com.ab.services.TradeService;

@Controller
public class ProfileController 
{
	@Autowired
    private OrderService orderService;
    
    @Autowired
    private TradeService tradeService;

    @GetMapping("/profile")
    public String userProfile(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        List<Order> orders = orderService.getOrdersForUser(user);
        model.addAttribute("orders", orders);
        
        List<Trade> tradingHistory = tradeService.getTradeHistoryForUser(user);
        model.addAttribute("tradingHistory", tradingHistory);

        return "userProfile";
    }
}
