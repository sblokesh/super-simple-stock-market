package com.gbce.market.service;

import java.math.BigDecimal;

public interface IStockIndexService {
	/**
	 * Calculates the GBCE All Share Index.
	 *
	 * @return The GBCE All Share Index.
	 */
	BigDecimal calculateGBCEAllShareIndex();
}
