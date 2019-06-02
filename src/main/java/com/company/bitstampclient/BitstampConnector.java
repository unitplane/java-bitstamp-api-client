package com.company.bitstampclient;

import org.slf4j.LoggerFactory;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.WebSocketContainer;
import java.io.IOException;
import java.net.URI;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


public class BitstampConnector {

    private BitstampConnector() { }

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(BitstampConnector.class);

    static final CountDownLatch messageLatch = new CountDownLatch(1);

    public static void connect() throws DeploymentException, InterruptedException, IOException {

        String uri = "wss://ws.bitstamp.net";

        LOG.info("Connecting to {}", uri);

        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        container.connectToServer(BitstampConnectorEndpoint.class, URI.create(uri));

        messageLatch.await(100, TimeUnit.SECONDS);
    }
}
