package main;

import javax.swing.JOptionPane;

import db.DBMgr;
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
		// Initialize the database connection
    	// Eventually these will be moved out as constants or config
		if (!DBMgr.build("mysql.rentalsbyjb.com", "cs421_scrap", 
				"cs421_scrap", "cs421#scrap")) {
			System.out.println("Error connecting to database.");
			System.exit(0);
		}
        // Build and execute the server.
        server = new jNetworkInterfaceServer(8888, 50, false);
        server.setServerName("Scrap Competition Server");
        server.run();
    }
}