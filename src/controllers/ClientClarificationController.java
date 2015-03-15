package controllers;

import businessobjects.Clarification;
import businessobjects.SettingsCommunicator;
import businessobjects.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jNetworking.jNetworkInterface.jNetworkInterface;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import ui.ClientClarificationFrame;

/**
 *
 * @author Matthew Mossner
 */
public class ClientClarificationController implements ActionListener {
    
    private User user;
    private ClientClarificationFrame clarificationView;
    private RequestClarificationController requestClarificationController;
    private long lastUpdated;
    
    public ClientClarificationController(User user) {
        lastUpdated = 0;
        this.user = user;
        clarificationView = new ClientClarificationFrame();
        clarificationView.jbtnNewClarification.addActionListener(this);
        requestClarificationController = new RequestClarificationController(clarificationView, user);
        
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {                
                clarificationView.setVisible(true);
                EventQueue.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        while (clarificationView != null) {
                            updateClarifications();
                            try {
                                Thread.sleep(5000);
                            }
                            catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                });
            }
        });
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clarificationView.jbtnNewClarification)
            jbtnNewClarificationClick();
    }
    
    public static void main(String[] args) {
        businessobjects.CompetitionUser user = new businessobjects.CompetitionUser(null, null);
        ClientClarificationController controller = new ClientClarificationController(user);
    }
    
    private void jbtnNewClarificationClick() {
        Clarification clarification = requestClarificationController.requestClarification();
        if (clarification != null) {
            
        }
    }
    
    private void updateClarifications() {
        jNetworkInterface client = new jNetworkInterface(
            SettingsCommunicator.getServerAddr(),
            SettingsCommunicator.getServerPort(), false);
        ArrayList<String> commandData = new ArrayList<String>();
        long tempLastUpdated = lastUpdated;
        lastUpdated = new Date().getTime();
        try {
            commandData.add(jNetworkInterface.base64Encode(String.valueOf(user.getId())));
            commandData.add(jNetworkInterface.base64Decode(String.valueOf(tempLastUpdated)));
        }
        catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
        String response = client.sendCommand("getuserclarifications", commandData);
        ArrayList<Clarification> clarifications = new Gson().fromJson(response, new TypeToken<ArrayList<Clarification>>() {
                    }.getType());
        clarificationView.updateTable(clarifications);
    }
}
