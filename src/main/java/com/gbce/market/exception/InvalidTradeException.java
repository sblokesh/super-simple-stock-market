package com.gbce.market.exception;

/**
 * Custom exception class for invalid trades.
 */
public class InvalidTradeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidTradeException(String message) {
		super(message);
	}

	public InvalidTradeException(String message, Throwable cause) {
		super(message, cause);
	}
}
