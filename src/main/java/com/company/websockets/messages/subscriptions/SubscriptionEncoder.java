package com.company.websockets.messages.subscriptions;

import com.google.gson.Gson;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class SubscriptionEncoder implements Encoder.Text<Subscription> {

    private static Gson gson = new Gson();

    @Override
    public String encode(Subscription message) throws EncodeException {
        return gson.toJson(message);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
        // Custom initialization logic
    }

    @Override
    public void destroy() {
        // Close resources
    }
}