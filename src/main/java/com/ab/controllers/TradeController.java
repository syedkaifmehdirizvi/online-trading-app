package com.ab.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ab.entities.Trade;
import com.ab.entities.User;
import com.ab.services.TradeService;

@Controller
public class TradeController {
    @Autowired
    private TradeService tradeService;

    @GetMapping("/trades/history")
    public ModelAndView getTradeHistory(HttpSession session) {
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return new ModelAndView("redirect:/login"); // Redirect to login page if 'user' session attribute is not set
        }

        List<Trade> tradingHistory = tradeService.getTradeHistoryForUser(user);
        ModelAndView mav = new ModelAndView("tradingHistory");
        mav.addObject("tradingHistory", tradingHistory);
        return mav;
    }

}