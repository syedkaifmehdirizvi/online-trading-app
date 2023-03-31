package com.ab.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ab.entities.Trade;
import com.ab.entities.User;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Integer> 
{
	List<Trade> findByUser(User user);
}
