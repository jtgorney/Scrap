package main;

import jNetworking.jNetworkInterface.*;

/**
 * Scrap server and methods.
 */
public class ScrapServer {

    private jNetworkInterfaceServer server;

    public static void main(String[] args) {
        new ScrapServer();
    }

    public ScrapServer() {
        server = new jNetworkInterfaceServer(8888, 50, false);
        server.setServerName("Scrap Competition Server");
        server.run();
    }
}