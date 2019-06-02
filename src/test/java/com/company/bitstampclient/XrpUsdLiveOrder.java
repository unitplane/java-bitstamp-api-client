package com.company.bitstampclient;

import com.company.bitstampclient.messages.liveorders.LiveOrder;
import com.company.bitstampclient.messages.liveorders.LiveOrderData;
import com.company.bitstampclient.observers.LiveOrderObserver;
import org.slf4j.LoggerFactory;

public class XrpUsdLiveOrder implements LiveOrderObserver {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(XrpUsdLiveOrder.class);

    @Override
    public void receive(LiveOrder liveOrder) {

        if (liveOrder.getEvent().equals("order_created")) {

            LiveOrderData data = liveOrder.getData();

            if (data.getAmount() > 5000) {
                LOG.warn("Large order entered: {} {} XRP", data.getOrder_type() == 0 ? "Buy" : "Sell", (int) data.getAmount());
            }

        }

    }
}
