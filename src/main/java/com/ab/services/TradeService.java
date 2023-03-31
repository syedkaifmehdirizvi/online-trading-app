package com.ab.services;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ab.entities.Trade;
import com.ab.entities.User;
import com.ab.repositories.TradeRepository;

@Service
public class TradeService 
{
	@Autowired
    private TradeRepository tradeRepository;

    public Optional<Trade> getTradeHistoryForUser(User user) 
    {
        return tradeRepository.findByUser(user);
    }
    
    public Trade addTradeForUser(User user, Trade trade) 
    {
        trade.setUser(user);
        trade.setCreatedOn(LocalDate.now());
        return tradeRepository.save(trade);
    }
    
}
