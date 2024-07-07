package com.gbce.market.model;

import java.math.BigDecimal;

/**
 * Represents a preferred stock in the exchange.
 */
public class PreferredStock extends Stock {

	public PreferredStock(String symbol, String type, BigDecimal lastDividend, BigDecimal fixedDividend,
			BigDecimal parValue) {
		super(symbol, type, lastDividend, fixedDividend, parValue);
	}

	/**
	 * Calculates the dividend yield for a preferred stock.
	 *
	 * @param price The price of the stock.
	 * @return The dividend yield.
	 */
	@Override
	public BigDecimal calculateDividendYield(BigDecimal price) {
		return getFixedDividend().multiply(getParValue()).divide(price);
	}
}