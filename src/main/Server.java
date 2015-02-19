package main;

import jNetworking.jNetworkInterface.*;
import java.util.Scanner;

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
        // LogLocation.setLocation("/home/jacob/Desktop/log.txt");
        // Create the server
        // Get the server input stuff
        Scanner sc = new Scanner(System.in);
        System.out.println("Server Config Params");
        System.out.println();
        System.out.print("Enter TCP Port [DEFAULT=8888]: ");
        int port = sc.nextInt();
        System.out.print("Enter Max Connected Clients[DEFAULT=100]: ");
        int max = sc.nextInt();
        System.out.println();
        System.out.println("Attempting to start server.");
        System.out.println();
        server = new jNetworkInterfaceServer(port, max, false);
        server.setServerName("Scrap Competition Server");
        server.run();
    }
}