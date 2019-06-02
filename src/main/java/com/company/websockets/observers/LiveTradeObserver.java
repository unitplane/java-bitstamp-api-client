package com.company.websockets.observers;

import com.company.websockets.messages.livetrades.LiveTrade;

public interface LiveTradeObserver {

    void receive(LiveTrade liveTrade);

}
