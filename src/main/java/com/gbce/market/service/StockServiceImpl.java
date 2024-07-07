package com.gbce.market.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbce.market.exception.InvalidPriceException;
import com.gbce.market.exception.ZeroDividendException;
import com.gbce.market.model.Stock;
import com.gbce.market.model.Trade;
import com.gbce.market.repository.StockRepository;
import com.gbce.market.repository.TradeRepository;

/**
 * Service class for handling operations related to stocks.
 */
@Service
public class StockServiceImpl implements IStockService, IStockIndexService {

	private static final Logger logger = LoggerFactory.getLogger(StockServiceImpl.class);
	private static final long FIVE_MINUTES_IN_MILLIS = 300000L;

	private final TradeRepository tradeRepository;
	private final StockRepository stockRepository;

	@Autowired
	public StockServiceImpl(TradeRepository tradeRepository, StockRepository stockRepository) {
		this.tradeRepository = tradeRepository;
		this.stockRepository = stockRepository;
	}

	/**
	 * Retrieves a stock by its symbol.
	 *
	 * @param symbol The symbol of the stock to retrieve.
	 * @return The stock with the specified symbol, or null if not found.
	 */
	@Override
	public Stock findBySymbol(String symbol) {
		logger.debug("Finding stock by symbol: {}", symbol);
		return stockRepository.findBySymbol(symbol);
	}

	/**
	 * Calculates the dividend yield for a stock.
	 *
	 * @param stock The stock for which to calculate the dividend yield.
	 * @param price The price of the stock.
	 * @return The dividend yield.
	 */
	@Override
	public BigDecimal calculateDividendYield(Stock stock, BigDecimal price) {
		logger.debug("Calculating dividend yield for stock: {}", stock.getSymbol());
		validatePrice(price); // Validation
		return stock.calculateDividendYield(price);
	}

	/**
	 * Calculates the P/E Ratio for a stock.
	 *
	 * @param stock The stock for which to calculate the P/E Ratio.
	 * @param price The price of the stock.
	 * @return The P/E Ratio.
	 * @throws ArithmeticException If the last dividend is zero.
	 */
	@Override
	public BigDecimal calculatePERatio(Stock stock, BigDecimal price) {
		logger.debug("Calculating P/E ratio for stock: {}", stock.getSymbol());
		// Validation
		validatePrice(price);

		BigDecimal lastDividend = stock.getLastDividend();
		if (lastDividend.compareTo(BigDecimal.ZERO) == 0) {
			String errorMessage = "Last dividend is zero, cannot calculate P/E ratio";
			logger.error(errorMessage);
			throw new ZeroDividendException(errorMessage);
		}

		return price.divide(lastDividend);
	}

	/**
	 * Calculates the volume weighted stock price for a stock.
	 *
	 * @param symbol The symbol of the stock for which to calculate the volume
	 *               weighted stock price.
	 * @return The volume weighted stock price.
	 * @throws ArithmeticException If no trades are found or total quantity is zero.
	 */
	@Override
	public BigDecimal calculateVolumeWeightedStockPrice(String symbol) {
		logger.debug("Calculating volume weighted stock price for symbol: {}", symbol);

		long currentTimeMillis = System.currentTimeMillis();
		List<Trade> trades = tradeRepository.findRecentTrades(symbol, currentTimeMillis - FIVE_MINUTES_IN_MILLIS);

		BigDecimal totalPriceQuantity = BigDecimal.ZERO;
		BigDecimal totalQuantity = BigDecimal.ZERO;

		for (Trade trade : trades) {
			BigDecimal tradeQuantity = BigDecimal.valueOf(trade.getQuantity());
			totalPriceQuantity = totalPriceQuantity.add(trade.getPrice().multiply(tradeQuantity));
			totalQuantity = totalQuantity.add(tradeQuantity);
		}

		if (totalQuantity.compareTo(BigDecimal.ZERO) == 0) {
			String errorMessage = "Total quantity is zero, cannot calculate volume weighted stock price";
			logger.error(errorMessage);
			throw new ArithmeticException(errorMessage);
		}

		BigDecimal volumeWeightedStockPrice = totalPriceQuantity.divide(totalQuantity, RoundingMode.HALF_UP);
		logger.debug("Volume weighted stock price calculated: {}", volumeWeightedStockPrice);
		return volumeWeightedStockPrice;
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
		logger.debug("Calculating GBCE All Share Index");

		List<Stock> stocks = stockRepository.findAll();
		int count = stocks.size();
		if (count == 0) {
			String errorMessage = "No stocks found, cannot calculate GBCE All Share Index";
			logger.error(errorMessage);
			throw new ArithmeticException(errorMessage);
		}

		BigDecimal product = stocks.stream().map(Stock::getParValue).reduce(BigDecimal.ONE, BigDecimal::multiply);

		BigDecimal gbceAllShareIndex = BigDecimal.valueOf(Math.pow(product.doubleValue(), 1.0 / count));
		return gbceAllShareIndex;
	}

	private void validatePrice(BigDecimal price) {
		if (price.compareTo(BigDecimal.ZERO) <= 0) {
			String errorMessage = "Price must be provided and greater than zero";
			logger.error(errorMessage);
			throw new InvalidPriceException(errorMessage);
		}
	}
}