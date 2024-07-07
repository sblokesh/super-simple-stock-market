package com.gbce.market.service;

import com.gbce.market.model.Trade;

/**
 * Service interface for trade operations.
 */
public interface ITradeService {
	/**
	 * Records a trade.
	 *
	 * @param trade The trade to record.
	 */
	void recordTrade(Trade trade);

}
