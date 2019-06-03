package com.company.bitstampclient;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import javax.websocket.DeploymentException;
import java.io.IOException;
import java.util.Scanner;

public class ConnectionTest {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ConnectionTest.class);

    @Test
    public void connect() {

        BitstampConnectorEndpoint.onOpen(new ConnectionOpen());

        try {
            BitstampConnector.connect();

        } catch (InterruptedException | DeploymentException | IOException e) {
            Assert.fail("Exception while connecting");
        }

        new Scanner(System.in).nextLine();
    }

}
