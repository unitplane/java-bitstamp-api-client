package com.company.bitstampclient.observers;

import com.company.bitstampclient.messages.livetrades.LiveTrade;

public interface LiveTradeObserver {

    void receive(LiveTrade liveTrade);

}
