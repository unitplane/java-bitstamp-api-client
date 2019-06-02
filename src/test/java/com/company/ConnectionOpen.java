package com.company;

import com.company.websockets.BitstampConnectorEndpoint;
import com.company.websockets.observers.ConnectionOpenObserver;

public class ConnectionOpen implements ConnectionOpenObserver {

    @Override
    public void receive() {

        BitstampConnectorEndpoint.onLiveTrade("xrpusd", new XrpUsdLiveTrade());

    }
}
