package com.company.bitstampclient;

import com.company.bitstampclient.messages.liveorder.LiveOrder;
import com.company.bitstampclient.messages.liveorder.LiveOrderData;
import com.company.bitstampclient.observers.LiveOrderObserver;
import org.slf4j.LoggerFactory;

public class XrpUsdLiveOrder implements LiveOrderObserver {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(XrpUsdLiveOrder.class);

    @Override
    public void receive(LiveOrder liveOrder) {

        // A live order has been received, now we can do anything with it:

        if (liveOrder.getEvent().equals("order_created")) {

            LiveOrderData data = liveOrder.getData();

            if (data.getAmount() > 5000) {
                LOG.info("Live Order: {}", liveOrder);
            } else {
                LOG.info("Received order {} but it was ignored.", data.getId());
            }

        }

    }
}
