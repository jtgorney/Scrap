/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jNetworking.jNetworkInterface.Commands;

import com.google.gson.Gson;
import db.DBMgr;
import jNetworking.jNetworkInterface.Command;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author jtgorney
 */
public class GetProblemSet implements Command {

    @Override
    public void setup(ArrayList<String> input, Socket client) {
        // Nothing needed.
    }

    @Override
    public String run() {
        DBMgr dbmgr = new DBMgr();
        if (!DBMgr.build("mysql.rentalsbyjb.com", "cs421_scrap",
                "cs421_scrap", "cs421#scrap")) {
            System.out.println("Error connecting to database.");
            System.exit(0);
        }
        // Return
        return new Gson().toJson(dbmgr.getProblemSet());
    }
    
}
