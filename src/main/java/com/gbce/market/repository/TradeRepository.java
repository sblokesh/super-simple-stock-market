package com.gbce.market.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.gbce.market.model.Trade;

/**
 * Repository class for accessing trade data.
 */
@Repository
public class TradeRepository {

	private final List<Trade> trades = new ArrayList<>();

	/**
	 * Saves a trade.
	 *
	 * @param trade The trade to save.
	 */
	public void save(Trade trade) {
		trades.add(trade);
	}

	/**
	 * Finds recent trades for a given stock symbol since a specified timestamp.
	 *
	 * @param symbol         The symbol of the stock.
	 * @param sinceTimestamp The timestamp since which to find trades.
	 * @return A list of recent trades.
	 */
	public List<Trade> findRecentTrades(String symbol, long sinceTimestamp) {
		return trades.stream()
				.filter(trade -> trade.getStock().getSymbol().equals(symbol) && trade.getTimestamp() >= sinceTimestamp)
				.collect(Collectors.toList());
	}

}
