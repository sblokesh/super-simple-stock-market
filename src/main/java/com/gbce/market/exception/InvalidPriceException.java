package com.gbce.market.exception;

public class InvalidPriceException extends StockMarketException {

	private static final long serialVersionUID = 1L;

	public InvalidPriceException(String message) {
		super(message);
	}

	public InvalidPriceException(String message, Throwable cause) {
		super(message, cause);
	}
}
