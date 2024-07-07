package com.gbce.market.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gbce.market.model.Stock;

/**
 * Repository class for accessing stock data.
 */
@Repository
public class StockRepository {

	private List<Stock> stocks;

	@Autowired
	public StockRepository(List<Stock> stockList) {
		this.stocks = stockList;
	}

	/**
	 * Finds a stock by its symbol.
	 *
	 * @param symbol The symbol of the stock to find.
	 * @return The stock with the specified symbol, or null if not found.
	 */
	public Stock findBySymbol(String symbol) {
		return stocks.stream().filter(stock -> stock.getSymbol().equals(symbol)).findFirst().orElse(null);
	}

	/**
	 * Retrieves all stocks.
	 *
	 * @return A list of all stocks.
	 */
	public List<Stock> findAll() {
		return new ArrayList<>(stocks);
	}

}
