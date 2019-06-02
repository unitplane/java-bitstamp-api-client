package com.company;

import com.company.websockets.BitstampConnector;
import com.company.websockets.BitstampConnectorEndpoint;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import javax.websocket.DeploymentException;
import java.io.IOException;
import java.util.Scanner;

public class ConnectionTest {

    @Test
    public void connect() {

        BitstampConnectorEndpoint.onOpen(new ConnectionOpen());

        try {
            BitstampConnector.connect();

        } catch (InterruptedException | DeploymentException | IOException e) {
            Assert.fail("Exception");
        }

        new Scanner(System.in).nextLine();
    }

}
