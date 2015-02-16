package jNetworking.jNetworkInterface.Commands;

import db.DBMgr;
import jNetworking.jNetworkInterface.Command;
import jNetworking.jNetworkInterface.jNetworkInterface;

import java.net.Socket;
import java.util.ArrayList;

/**
 * ClientLogin command class for jNetworkInterfaceServer.
 */
public class ClientLogin implements Command {

    private String username, password;

    @Override
    public void setup(ArrayList<String> input, Socket client) {
        // Accept a username and password.
        username = jNetworkInterface.base64Decode(input.get(0));
        password = jNetworkInterface.base64Decode(input.get(1));
    }

    @Override
    public String run() {
        DBMgr dbmgr = new DBMgr();
        if (!DBMgr.build("mysql.rentalsbyjb.com", "cs421_scrap",
                "cs421_scrap", "cs421#scrap")) {
            System.out.println("Error connecting to database.");
            System.exit(0);
        }
        // Return the resulting ID
        return String.valueOf(dbmgr.getUserIdForCredentials(username, password));
    }
}
