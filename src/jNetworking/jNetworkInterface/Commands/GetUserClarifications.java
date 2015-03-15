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
    private long lastUpdate;
    
    @Override
    public void setup(ArrayList<String> input, Socket client) {
        userId = Integer.parseInt(jNetworkInterface.base64Decode(input.get(0)));
        lastUpdate = Integer.parseInt(jNetworkInterface.base64Decode(input.get(1)));
    }

    @Override
    public String run() {
        DBMgr dbmgr = new DBMgr();
        ArrayList<Clarification> clarifications = dbmgr.getUserClarifications(userId, lastUpdate);
        return new Gson().toJson(clarifications);
    }
    
}