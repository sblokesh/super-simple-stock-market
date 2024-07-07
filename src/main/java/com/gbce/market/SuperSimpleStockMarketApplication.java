package com.gbce.market;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gbce.market.model.Stock;
import com.gbce.market.model.Trade;
import com.gbce.market.model.enums.TradeType;
import com.gbce.market.service.StockServiceImpl;
import com.gbce.market.service.TradeService;

/**
 * Entry point for the Super Simple Stock Market application.
 */
@SpringBootApplication
public class SuperSimpleStockMarketApplication implements CommandLineRunner {

	private final StockServiceImpl stockService;
	private final TradeService tradeService;

	public SuperSimpleStockMarketApplication(StockServiceImpl stockService, TradeService tradeService) {
		this.stockService = stockService;
		this.tradeService = tradeService;
	}

	public static void main(String[] args) {
		SpringApplication.run(SuperSimpleStockMarketApplication.class, args);
	}

	/**
	 * Executes the application.
	 *
	 * @param args Command-line arguments.
	 */
	@Override
	public void run(String... args) {
		Stock pop = stockService.findBySymbol("POP");
		Stock tea = stockService.findBySymbol("TEA");

		BigDecimal price = BigDecimal.valueOf(100);

		System.out.println("Dividend Yield for POP: " + stockService.calculateDividendYield(pop, price));
		System.out.println("P/E Ratio for POP: " + stockService.calculatePERatio(pop, price));

		tradeService.recordTrade(new Trade(tea, 100, TradeType.BUY.name(), price, System.currentTimeMillis()));
		tradeService.recordTrade(new Trade(pop, 200, TradeType.SELL.name(), price, System.currentTimeMillis()));

		System.out.println("Volume Weighted Stock Price for POP: "
				+ stockService.calculateVolumeWeightedStockPrice(pop.getSymbol()));

		System.out.println("GBCE All Share Index: " + stockService.calculateGBCEAllShareIndex());
	}
}
