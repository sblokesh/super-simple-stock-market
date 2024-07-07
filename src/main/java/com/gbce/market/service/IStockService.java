package com.gbce.market.service;

import java.math.BigDecimal;

import com.gbce.market.model.Stock;

/**
 * Service interface for stock operations.
 */
public interface IStockService extends IStockCalculationService {

	/**
	 * Finds a stock by its symbol.
	 *
	 * @param symbol The symbol of the stock to find.
	 * @return The stock with the specified symbol, or null if not found.
	 */
	Stock findBySymbol(String symbol);

	/**
	 * Calculates the volume weighted stock price for a stock.
	 *
	 * @param symbol The symbol of the stock for which to calculate the volume
	 *               weighted stock price.
	 * @return The volume weighted stock price.
	 * @throws ArithmeticException If no trades are found or total quantity is zero.
	 */
	BigDecimal calculateVolumeWeightedStockPrice(String symbol);

}
