package com.company.bitstampclient.messages.liveorders;

import lombok.Data;

@Data
public class LiveOrder {

    private LiveOrderData data;
    private String event;
    private String channel;

}
