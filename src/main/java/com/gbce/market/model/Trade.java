package com.gbce.market.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Represents a trade in the market.
 */
public final class Trade implements Serializable {

	private static final long serialVersionUID = 1L;

	private final Stock stock;
	private final long timestamp;
	private final int quantity;
	private final String indicator;
	private final BigDecimal price;

	public Trade(Stock stock, int quantity, String indicator, BigDecimal price, long timestamp) {
		this.stock = stock;
		this.quantity = quantity;
		this.indicator = indicator;
		this.price = price;
		this.timestamp = timestamp;
	}

	/**
	 * Retrieves the stock involved in the trade.
	 *
	 * @return The stock.
	 */
	public Stock getStock() {
		return stock;
	}

	/**
	 * Retrieves the timestamp of the trade.
	 *
	 * @return The timestamp.
	 */
	public long getTimestamp() {
		return timestamp;
	}

	/**
	 * Retrieves the quantity of the stock traded.
	 *
	 * @return The quantity.
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Retrieves the indicator (buy/sell) of the trade.
	 *
	 * @return The indicator.
	 */
	public String getIndicator() {
		return indicator;
	}

	/**
	 * Retrieves the price per unit of the stock traded.
	 *
	 * @return The price.
	 */
	public BigDecimal getPrice() {
		return price;
	}

}
