package com.company.websockets.messages.livetrades;

@lombok.Data
public class LiveTrade {

    private LiveTradeData data;
    private String event;
    private String channel;

}


