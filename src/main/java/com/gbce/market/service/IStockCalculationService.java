package com.gbce.market.service;

import java.math.BigDecimal;

import com.gbce.market.model.Stock;

/**
 * Interface for calculating financial metrics for stocks.
 */
public interface IStockCalculationService {
	/**
	 * Calculates the dividend yield for a stock.
	 *
	 * @param stock The stock for which to calculate the dividend yield.
	 * @param price The price of the stock.
	 * @return The dividend yield.
	 */
	BigDecimal calculateDividendYield(Stock stock, BigDecimal price);

	/**
	 * Calculates the P/E Ratio for a stock.
	 *
	 * @param stock The stock for which to calculate the P/E Ratio.
	 * @param price The price of the stock.
	 * @return The P/E Ratio.
	 * @throws ArithmeticException If the last dividend is zero.
	 */
	BigDecimal calculatePERatio(Stock stock, BigDecimal price);

}
