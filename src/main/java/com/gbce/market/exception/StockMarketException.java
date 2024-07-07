package com.gbce.market.exception;

public class StockMarketException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public StockMarketException(String message) {
		super(message);
	}

	public StockMarketException(String message, Throwable cause) {
		super(message, cause);
	}
}
