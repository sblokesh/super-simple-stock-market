package com.gbce.market.model;

import java.math.BigDecimal;

/**
 * Represents a common stock in the exchange.
 */
public class CommonStock extends Stock {

	public CommonStock(String symbol, String type, BigDecimal lastDividend, BigDecimal fixedDividend,
			BigDecimal parValue) {
		super(symbol, type, lastDividend, fixedDividend, parValue);
	}

	/**
	 * Calculates the dividend yield for a common stock.
	 *
	 * @param price The price of the stock.
	 * @return The dividend yield.
	 */
	@Override
	public BigDecimal calculateDividendYield(BigDecimal price) {
		return getLastDividend().divide(price);
	}

}
