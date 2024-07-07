package com.gbce.market.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbce.market.model.Stock;
import com.gbce.market.repository.StockRepository;

/**
 * Service class for calculating GBCE All Share Index.
 */
@Service
public class GBCEStockIndexServiceImpl implements IStockIndexService {

	private final StockRepository stockRepository;

	@Autowired
	public GBCEStockIndexServiceImpl(StockRepository stockRepository) {
		this.stockRepository = stockRepository;
	}

	/**
	 * Calculates the GBCE All Share Index using the geometric mean of the Volume
	 * Weighted Stock Price for all stocks.
	 *
	 * @return The GBCE All Share Index.
	 * @throws ArithmeticException If no stocks are found.
	 */
	@Override
	public BigDecimal calculateGBCEAllShareIndex() {
		List<Stock> stocks = stockRepository.findAll();
		int count = stocks.size();
		if (count == 0) {
			throw new ArithmeticException("No stocks found, cannot calculate GBCE All Share Index");
		}

		BigDecimal product = stocks.stream().map(Stock::getParValue).reduce(BigDecimal.ONE, BigDecimal::multiply);

		return BigDecimal.valueOf(Math.pow(product.doubleValue(), 1.0 / count));
	}
}
