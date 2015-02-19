/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Jacob Gorney, Max Savard, Matt Mossner, Spencer Kokaly
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package businessobjects;

import jNetworking.jNetworkInterface.jNetworkInterface;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Competition specific user.
 *
 * @author Jacob Gorney
 *
 */
public class CompetitionUser extends User {
    /**
     * Authentication flag.
     */
    private boolean authenticated = false;
    
    /**
     * Constructor
     * @param username Username
     * @param password Password
     */
    public CompetitionUser(String username, String password) {
        super(username, password);
    }

    public boolean authenticate() {
        // @todo make this work
        if (username.isEmpty() || password.isEmpty()) {
            authenticated = false;
            return authenticated;
        } else {
            // Build the server connection
            jNetworkInterface client = new jNetworkInterface(
                    SettingsCommunicator.getServerAddr(),
                    SettingsCommunicator.getServerPort(), false);
            ArrayList<String> commandData = new ArrayList<>();
            // Add the data as base64 encoded string
            try {
                commandData.add(jNetworkInterface.base64Encode(username));
                commandData.add(jNetworkInterface.base64Encode(password));
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
            // Response holder
            // Will retain -1 if we fail to connect as well
            int response = -1;
            try {
                response = Integer.parseInt(client.sendCommand("clientlogin", commandData));
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null,
                        "Error: Server cound not be contacted.",
                        "Communication Error",
                        JOptionPane.WARNING_MESSAGE);
            }
            // Process the response.
            authenticated = (response > -1);
            return authenticated;
        }
    }
    
    public boolean isAuthenticated() {
        return authenticated;
    }
    
    private int getUserIdFromDB() {
        return 1;
    }
}
