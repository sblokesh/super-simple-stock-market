package com.gbce.market.service;

import org.springframework.stereotype.Service;

import com.gbce.market.exception.InvalidTradeException;
import com.gbce.market.model.Trade;
import com.gbce.market.repository.TradeRepository;

/**
 * Service class for handling trade operations.
 */
@Service
public class TradeService implements ITradeService {

    private final TradeRepository tradeRepository;

    public TradeService(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    /**
     * Records a trade.
     *
     * @param trade The trade to record.
     * @throws InvalidTradeException If the trade quantity is zero or negative.
     */
    @Override
    public void recordTrade(Trade trade) {
        validateTrade(trade);
        tradeRepository.save(trade);
    }

    /**
     * Validates a trade to ensure the quantity is positive.
     *
     * @param trade The trade to validate.
     * @throws InvalidTradeException If the trade quantity is zero or negative.
     */
    private void validateTrade(Trade trade) {
        if (trade.getQuantity() <= 0) {
            throw new InvalidTradeException("Trade quantity must be greater than zero");
        }
    }
}
