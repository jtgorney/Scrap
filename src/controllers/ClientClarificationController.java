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
import java.sql.Timestamp;
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
    private Timestamp lastUpdated;
    
    public ClientClarificationController(User user) {
        lastUpdated = new Timestamp(new Date().getTime());
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
        Timestamp tempLastUpdated = lastUpdated;
        lastUpdated = new Timestamp(new Date().getTime());
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
