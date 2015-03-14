package jNetworking.jNetworkInterface.Commands;

import businessobjects.Clarification;
import com.google.gson.Gson;
import db.DBMgr;
import jNetworking.jNetworkInterface.Command;
import jNetworking.jNetworkInterface.jNetworkInterface;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Get clarifications initiated by a particular user.
 * @author Matthew Mossner
 */
public class GetUserClarifications implements Command {

    private int userId;
    
    @Override
    public void setup(ArrayList<String> input, Socket client) {
        userId = Integer.parseInt(jNetworkInterface.base64Decode(input.get(0)));
    }

    @Override
    public String run() {
        DBMgr dbmgr = new DBMgr();
        if (!DBMgr.build("mysql.rentalsbyjb.com", "cs421_scrap",
                "cs421_scrap", "cs421#scrap")) {
            System.out.println("Error connecting to database.");
            return null;
        }
        ArrayList<Clarification> clarifications = dbmgr.getUserClarifications(userId);
        return new Gson().toJson(clarifications);
    }
    
}
