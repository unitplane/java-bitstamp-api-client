package com.company.bitstampclient.observers;

import com.company.bitstampclient.messages.liveorders.LiveOrder;

public interface LiveOrderObserver {

    void receive(LiveOrder liveOrder);

}
