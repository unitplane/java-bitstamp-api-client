package com.company.bitstampclient;

import com.company.bitstampclient.messages.Message;
import com.company.bitstampclient.messages.liveorder.LiveOrder;
import com.company.bitstampclient.messages.livetrade.LiveTrade;
import com.company.bitstampclient.messages.subscription.SubscriptionMessage;
import com.company.bitstampclient.observers.*;
import com.google.gson.Gson;
import org.slf4j.LoggerFactory;

import javax.websocket.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@ClientEndpoint
public class BitstampConnectorEndpoint {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(BitstampConnectorEndpoint.class);

    private Gson gson = new Gson();

    private static Set<String> liveTradePairSubscriptions = new HashSet<>();
    private static Set<String> liveOrderPairSubscriptions = new HashSet<>();

    private static List<ConnectionOpenObserver> openObservers = new ArrayList<>();
    private static List<MessageObserver> messageObservers = new ArrayList<>();
    private static List<CloseObserver> closeObservers = new ArrayList<>();
    private static List<LiveTradeObserver> liveTradeObservers = new ArrayList<>();
    private static List<LiveOrderObserver> liveOrderObservers = new ArrayList<>();

    public static void onOpen(ConnectionOpenObserver observer) {
        openObservers.add(observer);
    }

    public static void onMessage(MessageObserver observer) {
        messageObservers.add(observer);
    }

    public static void onLiveTrade(String pair, LiveTradeObserver observer) {

        if (!liveTradePairSubscriptions.contains(pair)) {

            SubscriptionMessage message = new SubscriptionMessage();
            try {
                message.subscribeChannel("live_trades_" + pair.toLowerCase().trim());
            } catch (Exception e) {
                LOG.error("Error", e);
            }

            liveTradePairSubscriptions.add(pair);
            liveTradeObservers.add(observer);
        }
    }

    public static void onLiveOrder(String pair, LiveOrderObserver observer) {

        if (!liveOrderPairSubscriptions.contains(pair)) {

            SubscriptionMessage message = new SubscriptionMessage();
            try {
                message.subscribeChannel("live_orders_" + pair.toLowerCase().trim());

            } catch (Exception e) {
                LOG.error("Error", e);
            }

            liveOrderPairSubscriptions.add(pair);
            liveOrderObservers.add(observer);
        }
    }

    public static void onClose(CloseObserver observer) {
        closeObservers.add(observer);
    }

    @OnOpen
    public void eventOpen(Session session) {

        LOG.debug("Connected to endpoint: {}", session.getBasicRemote());

        BitstampConnector.setSession(session);

        for (ConnectionOpenObserver observer : openObservers) {
            observer.receive();
        }
    }

    @OnMessage
    public void eventMessage(String jsonMessage) {

        LOG.debug("Received: {}", jsonMessage);

        for (MessageObserver observer : messageObservers) {
            observer.receive(jsonMessage);
        }

        Message message = gson.fromJson(jsonMessage, Message.class);

        switch (message.getEvent()) {

            case "trade":

                LiveTrade trade = gson.fromJson(jsonMessage, LiveTrade.class);
                for (LiveTradeObserver observer : liveTradeObservers) {
                    observer.receive(trade);
                }
                break;

            case "order_created":
            case "order_changed":
            case "order_deleted":

                LiveOrder order = gson.fromJson(jsonMessage, LiveOrder.class);
                for (LiveOrderObserver observer : liveOrderObservers) {
                    observer.receive(order);
                }
                break;

            case "bts:subscription_succeeded":
                break;

            case "bts:request_reconnect":
                BitstampConnector.reconnect();
                break;

            default:
                LOG.warn("Unknown event: {}", message.getEvent());
        }

        BitstampConnector.messageLatch.countDown();
    }

    @OnError
    public void eventError(Throwable t) {
        LOG.error("Error", t);
    }

    @OnClose
    public void eventClose() {

        LOG.info("onClose event");

        for (CloseObserver observer : closeObservers) {
            observer.receive();
        }
    }


}