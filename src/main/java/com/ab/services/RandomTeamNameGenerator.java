package com.ab.services;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class RandomTeamNameGenerator {

   private final List<String> teamNameList = Arrays.asList(
		   "Java Traders", 
			"Byte Bulls", 
			"Code Crusaders", 
			"Syntax Savvy", 
			"Loop Legends", 
			"Stock & Roll",
			"Trade Winds",
			"The Bull Runners",
			"Stock Hounds",
			"Buy or Cry",
			"Sell-O-Maniacs",
			"The Blue Chip Squad",
			"Trader's Delight",
			"Order Bookworms",
			"Margin Mavericks",
			"Orderly Conduct",
			"The Trading Tribe",
			"Market Movers",
			"Trade Nation",
			"Stock Savants");

   public String generateRandomTeamName() {
      Random random = new Random();
      int index = random.nextInt(teamNameList.size());
      return teamNameList.get(index);
   }
}
