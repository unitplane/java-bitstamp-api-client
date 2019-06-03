package com.company.bitstampclient.messages.livetrade;

@lombok.Data
public class LiveTrade {

    private LiveTradeData data;
    private String event;
    private String channel;

}


