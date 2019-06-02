package com.company.bitstampclient;

import com.company.bitstampclient.messages.livetrades.LiveTrade;
import com.company.bitstampclient.observers.LiveTradeObserver;
import org.slf4j.LoggerFactory;

public class XrpUsdLiveTrade implements LiveTradeObserver {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(XrpUsdLiveTrade.class);

    @Override
    public void receive(LiveTrade liveTrade) {

        if (liveTrade.getData().getAmount() > 4000) {
            LOG.warn("LARGE TRADE DETECTED: {}", (int) liveTrade.getData().getAmount());
        }

    }
}
