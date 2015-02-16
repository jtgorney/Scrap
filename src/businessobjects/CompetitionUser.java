package businessobjects;

import jNetworking.jNetworkInterface.jNetworkInterface;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

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
