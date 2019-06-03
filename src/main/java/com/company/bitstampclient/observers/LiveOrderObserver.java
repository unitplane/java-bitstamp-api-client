package com.company.bitstampclient.observers;

import com.company.bitstampclient.messages.liveorder.LiveOrder;

public interface LiveOrderObserver {

    /**
     * Receive an order that was created, changed or deleted.
     */
    void receive(LiveOrder liveOrder);

}
