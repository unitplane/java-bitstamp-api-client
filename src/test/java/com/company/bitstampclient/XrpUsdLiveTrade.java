package com.company.bitstampclient;

import com.company.bitstampclient.messages.livetrade.LiveTrade;
import com.company.bitstampclient.messages.livetrade.LiveTradeData;
import com.company.bitstampclient.observers.LiveTradeObserver;
import org.slf4j.LoggerFactory;

public class XrpUsdLiveTrade implements LiveTradeObserver {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(XrpUsdLiveTrade.class);

    @Override
    public void receive(LiveTrade liveTrade) {

        // A live trade has been received, now we can do anything with it:

        LiveTradeData data = liveTrade.getData();

        if (data.getAmount() > 5000) {
            LOG.info("Live Trade: {}", liveTrade);

        } else {
            LOG.info("Received trade {} but it was ignored.", data.getId());
        }

    }
}
