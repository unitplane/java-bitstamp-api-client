package com.company.bitstampclient.observers;

import com.company.bitstampclient.messages.livetrade.LiveTrade;

/**
 * Live ticker
 */
public interface LiveTradeObserver {

    void receive(LiveTrade liveTrade);

}
