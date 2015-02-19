package main;

import jNetworking.jNetworkInterface.*;

/**
 * Scrap server and methods.
 */
public class Server {

    private jNetworkInterfaceServer server;

    public static void main(String[] args) {
        new Server();
    }

    public Server() {
        // Not setting the location of the log file will default to the root drive.
        // Ensure this program is executed with appropriate filesystem permissions.
        LogLocation.setLocation("/home/jacob/Desktop/log.txt");
        // Create the server
        server = new jNetworkInterfaceServer(8888, 50, false);
        server.setServerName("Scrap Competition Server");
        server.run();
    }
}