package com.company;

import com.company.websockets.messages.livetrades.LiveTrade;
import com.company.websockets.observers.LiveTradeObserver;
import org.slf4j.LoggerFactory;

public class XrpUsdLiveTrade implements LiveTradeObserver {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(XrpUsdLiveTrade.class);

    @Override
    public void receive(LiveTrade liveTrade) {

        if (liveTrade.getData().getAmount() > 3000) {
            LOG.warn("WOW, large amount {}", liveTrade.getData().getAmount());
        } else {
            LOG.info("Meh, small amount {}", liveTrade.getData().getAmount());
        }

    }
}
