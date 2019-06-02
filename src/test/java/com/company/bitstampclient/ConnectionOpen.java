package com.company.bitstampclient;

import com.company.bitstampclient.observers.ConnectionOpenObserver;

public class ConnectionOpen implements ConnectionOpenObserver {

    @Override
    public void receive() {

        BitstampConnectorEndpoint.onLiveTrade("xrpusd", new XrpUsdLiveTrade());

        BitstampConnectorEndpoint.onLiveOrder("xrpusd", new XrpUsdLiveOrder());

    }
}
