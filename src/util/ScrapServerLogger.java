/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import jNetworking.jNetworkInterface.ServerLogger;

/**
 *
 * @author Jacob Gorney
 */
public class ScrapServerLogger {
    private static ServerLogger serverLogger;
    
    private ScrapServerLogger() {
        // Do nothing here
    }
    
    public static ServerLogger getLogger() {
        if (serverLogger == null) {
            serverLogger = new ServerLogger();
            return serverLogger;
        } else return serverLogger;
    }
}
