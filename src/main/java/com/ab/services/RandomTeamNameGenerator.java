package com.ab.services;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class RandomTeamNameGenerator {

	private Random random = new Random();
	private int lastIndex = -1;
	private int counter = 0;

	private final List<String> teamNameList = Arrays.asList("Java Traders", "Byte Bulls", "Code Crusaders",
			"Syntax Savvy", "Loop Legends", "Stock & Roll", "Trade Winds", "The Bull Runners", "Stock Hounds",
			"Buy or Cry", "Sell-O-Maniacs", "The Blue Chip Squad", "Trader's Delight", "Order Bookworms",
			"Margin Mavericks", "Orderly Conduct", "The Trading Tribe", "Market Movers", "Trade Nation",
			"Stock Savants", "The Limit Orderlies", "The Bulls & Bears", "The Bid & Ask Brigade", "The Stock Slingers",
			"The Trading Troopers", "The Market Mavericks", "The Orderly Traders", "The Trading Titans",
			"The Candlestick Crusaders", "The Swing Traders", "The Market Magicians", "The Trading Thinkers",
			"The Bullish Battalion", "The Orderly Conductors", "The Stock Surgeons", "The Market Masters",
			"The Trade-o-holics", "The Orderly Mess", "The Bullish Bunch", "The Trading Titans", "Stock Shockers",
			"The Risk Takers", "The Trading Post", "The Stock Stars", "The Trade Wizards", "Orderly Chaos",
			"The Margin Monsters", "The Orderly Minds", "The Stock Stalkers", "The Trading Troubadours",
			"The Java Jedis of Trading", "The Buy-and-Sell Byte-Busters", "The Java Jockeys", "The Byte Busters",
			"The Code Crushers", "The Classy Coders", "The Syntax Savants", "The Loop Lovers", "The Code Commanders",
			"The Java Geniuses", "The Object Orientals", "The Code Crafters", "The Debugging Demons",
			"The Exceptionalists", "The Thread Titans", "The Inheritance Inquisitors", "The Polymorphism Pros",
			"The Limit Orderlies", "The Bulls & Bears", "The Bid & Ask Brigade", "The Stock Slingers",
			"The Trading Troopers", "The Market Mavericks", "The Orderly Traders", "The Trading Titans",
			"The Candlestick Crusaders", "The Swing Traders", "The Market Magicians", "The Trading Thinkers",
			"The Bullish Battalion", "The Orderly Conductors", "The Stock Surgeons");

	public String generateRandomTeamName() {
		int index;
		do {
			index = random.nextInt(teamNameList.size());
		} while (index == lastIndex && counter < 5);

		if (index != lastIndex) {
			lastIndex = index;
			counter = 0;
		} else {
			counter++;
		}

		return teamNameList.get(index);
	}

}
