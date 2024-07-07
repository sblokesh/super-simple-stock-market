package com.gbce.market.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.gbce.market.exception.InvalidPriceException;
import com.gbce.market.exception.ZeroDividendException;
import com.gbce.market.model.CommonStock;
import com.gbce.market.model.enums.StockType;
import com.gbce.market.repository.StockRepository;
import com.gbce.market.repository.TradeRepository;

@SpringBootTest
public class StockServiceImplTest {
	@Mock
	private StockRepository stockRepository;

	@Mock
	private TradeRepository tradeRepository;

	@InjectMocks
	private StockServiceImpl stockService;

	@Test
	public void testCalculateDividendYield() {
		CommonStock stock = new CommonStock("TEA", StockType.COMMON.getDisplayName(), BigDecimal.ZERO, null,
				BigDecimal.valueOf(100));
		BigDecimal price = BigDecimal.valueOf(100);

		when(stockRepository.findBySymbol("TEA")).thenReturn(stock);

		BigDecimal dividendYield = stockService.calculateDividendYield(stock, price);
		assertEquals(BigDecimal.ZERO, dividendYield);
	}

	@Test
	public void testCalculatePERatio() {
		CommonStock stock = new CommonStock("TEA", StockType.COMMON.getDisplayName(), BigDecimal.valueOf(8), null,
				BigDecimal.valueOf(100));
		BigDecimal price = BigDecimal.valueOf(100);

		when(stockRepository.findBySymbol("TEA")).thenReturn(stock);

		BigDecimal peRatio = stockService.calculatePERatio(stock, price);
		assertEquals(BigDecimal.valueOf(12.5), peRatio);
	}

	@Test
	public void testCalculatePERatio_ZeroDividendException() {
		CommonStock stock = new CommonStock("TEA", StockType.COMMON.getDisplayName(), BigDecimal.ZERO, null,
				BigDecimal.valueOf(100));
		BigDecimal price = BigDecimal.valueOf(100);

		when(stockRepository.findBySymbol("TEA")).thenReturn(stock);

		Exception exception = assertThrows(ZeroDividendException.class, () -> {
			stockService.calculatePERatio(stock, price);
		});

		String expectedMessage = "Last dividend is zero, cannot calculate P/E ratio";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	public void testInvalidPriceException() {
		CommonStock stock = new CommonStock("TEA", StockType.COMMON.getDisplayName(), BigDecimal.valueOf(8), null,
				BigDecimal.valueOf(100));
		BigDecimal price = BigDecimal.ZERO;

		when(stockRepository.findBySymbol("TEA")).thenReturn(stock);

		Exception exception = assertThrows(InvalidPriceException.class, () -> {
			stockService.calculateDividendYield(stock, price);
		});

		String expectedMessage = "Price must be provided and greater than zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	public void testCalculateDividendYieldInvalidPrice() {
		CommonStock stock = new CommonStock("POP", StockType.COMMON.getDisplayName(), BigDecimal.valueOf(8), null,
				BigDecimal.valueOf(100));
		BigDecimal price = BigDecimal.ZERO;

		when(stockRepository.findBySymbol("POP")).thenReturn(stock);

		assertThrows(InvalidPriceException.class, () -> {
			stockService.calculateDividendYield(stock, price);
		});
	}

	@Test
	public void testCalculatePERatioZeroDividend() {
		CommonStock stock = new CommonStock("POP", StockType.COMMON.getDisplayName(), BigDecimal.ZERO, null,
				BigDecimal.valueOf(100));
		BigDecimal price = BigDecimal.valueOf(100);

		when(stockRepository.findBySymbol("POP")).thenReturn(stock);

		assertThrows(ZeroDividendException.class, () -> {
			stockService.calculatePERatio(stock, price);
		});
	}
}
