package com.gbce.market.model;

import java.math.BigDecimal;

/**
 * Represents a stock in the exchange.
 */
public abstract class Stock {
	private final String symbol;
	private final String type;
	private final BigDecimal lastDividend;
	private final BigDecimal fixedDividend;
	private final BigDecimal parValue;

	public Stock(String symbol, String type, BigDecimal lastDividend, BigDecimal fixedDividend, BigDecimal parValue) {
		this.symbol = symbol;
		this.type = type;
		this.lastDividend = lastDividend;
		this.fixedDividend = fixedDividend;
		this.parValue = parValue;
	}

	/**
	 * Retrieves the symbol of the stock.
	 *
	 * @return The symbol of the stock.
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * Retrieves the type of the stock.
	 *
	 * @return The type of the stock.
	 */
	public String getType() {
		return type;
	}

	/**
	 * Retrieves the last dividend of the stock.
	 *
	 * @return The last dividend of the stock.
	 */
	public BigDecimal getLastDividend() {
		return lastDividend;
	}

	/**
	 * Retrieves the fixed dividend of the stock.
	 *
	 * @return The fixed dividend of the stock.
	 */
	public BigDecimal getFixedDividend() {
		return fixedDividend;
	}

	/**
	 * Retrieves the par value of the stock.
	 *
	 * @return The par value of the stock.
	 */
	public BigDecimal getParValue() {
		return parValue;
	}

	/**
	 * Calculates the dividend yield of the stock.
	 *
	 * @param price The price of the stock.
	 * @return The dividend yield.
	 */
	public abstract BigDecimal calculateDividendYield(BigDecimal price);
}