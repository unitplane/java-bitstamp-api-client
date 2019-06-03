package com.company.bitstampclient;

import com.company.bitstampclient.observers.ConnectionOpenObserver;

public class ConnectionOpen implements ConnectionOpenObserver {

    @Override
    public void receive() {

        // Connection is open, now we can start subscribing to channels:

        BitstampConnectorEndpoint.onLiveTrade("xrpusd", new XrpUsdLiveTrade());

        BitstampConnectorEndpoint.onLiveOrder("xrpusd", new XrpUsdLiveOrder());

    }
}
