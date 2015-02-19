/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import businessobjects.SettingsCommunicator;
import jNetworking.jNetworkInterface.jNetworkInterface;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Throw code at the server.
 * @author Jacob Gorney
 */
public class CodeTester {
    public static void main(String[] args) {
        // Build the server connection
            jNetworkInterface client = new jNetworkInterface(
                    SettingsCommunicator.getServerAddr(),
                    SettingsCommunicator.getServerPort(), false);
            ArrayList<String> commandData = new ArrayList<>();
            try {
            commandData.add(jNetworkInterface.base64Encode("1234"));
            commandData.add(jNetworkInterface.base64Encode("1"));
            commandData.add(jNetworkInterface.base64Encode("cpp"));
            commandData.add(jNetworkInterface.base64Encode("#include <iostream>\n#include <iomanip>\n\nusing namespace std;\n\nint main() { cout << \"testing\" << endl; return 0; }"));
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
            String response = client.sendCommand("testsolution", commandData);
            System.out.println("RESULT: " + response);
    }
}
