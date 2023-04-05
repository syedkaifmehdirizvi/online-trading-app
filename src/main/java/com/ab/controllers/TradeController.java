package com.ab.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ab.entities.Trade;
import com.ab.entities.User;
import com.ab.services.TradeService;

@Controller
public class TradeController {
    @Autowired
    private TradeService tradeService;

    @GetMapping("/trades/history")
    public String getTradeHistory(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login"; // Redirect to login page if 'user' session attribute is not set
        }

        List<Trade> tradingHistory = tradeService.getTradeHistoryForUser(user);
        model.addAttribute("tradingHistory", tradingHistory);
        return "tradingHistory";
    }
}