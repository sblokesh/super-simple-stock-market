package com.gbce.market.exception;

public class ZeroDividendException extends StockMarketException {

	private static final long serialVersionUID = 1L;

	public ZeroDividendException(String message) {
		super(message);
	}

	public ZeroDividendException(String message, Throwable cause) {
		super(message, cause);
	}
}
