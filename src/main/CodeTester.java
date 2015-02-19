/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import businessobjects.SettingsCommunicator;
import jNetworking.jNetworkInterface.jNetworkInterface;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import static java.nio.file.Files.readAllBytes;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.swing.JFileChooser;

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
                // Browse for file
                final JFileChooser fc = new JFileChooser();
                fc.showOpenDialog(null);
                // Read file
                String program = new String(readAllBytes(Paths.get(fc.getSelectedFile().toString())));
                // Add command data
                commandData.add(jNetworkInterface.base64Encode("1234"));
                commandData.add(jNetworkInterface.base64Encode("1"));
                commandData.add(jNetworkInterface.base64Encode("cpp"));
                commandData.add(jNetworkInterface.base64Encode(program));
                // Get response
                String response = client.sendCommand("testsolution", commandData);
                System.out.println("COMMAND RESULT: " + response);
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
    }
}
