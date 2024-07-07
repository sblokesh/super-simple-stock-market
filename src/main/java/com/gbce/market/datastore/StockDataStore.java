package com.gbce.market.datastore;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.gbce.market.model.CommonStock;
import com.gbce.market.model.PreferredStock;
import com.gbce.market.model.Stock;
import com.gbce.market.model.enums.StockType;

/**
 * Data store for stock information.
 */
@Configuration
public class StockDataStore {

	/**
	 * Provides a list of stocks.
	 *
	 * @return The list of stocks.
	 */
	@Bean
	@Scope("singleton")
	public List<Stock> stockList() {

		return Arrays.asList(
				new CommonStock("TEA", StockType.COMMON.getDisplayName(), BigDecimal.valueOf(0), null,
						BigDecimal.valueOf(100)),
				new CommonStock("POP", StockType.COMMON.getDisplayName(), BigDecimal.valueOf(8), null,
						BigDecimal.valueOf(100)),
				new CommonStock("ALE", StockType.COMMON.getDisplayName(), BigDecimal.valueOf(23), null,
						BigDecimal.valueOf(60)),
				new PreferredStock("GIN", StockType.PREFERRED.getDisplayName(), BigDecimal.valueOf(8),
						BigDecimal.valueOf(0.02), BigDecimal.valueOf(100)),
				new CommonStock("JOE", StockType.COMMON.getDisplayName(), BigDecimal.valueOf(13), null,
						BigDecimal.valueOf(250)));
	}
}