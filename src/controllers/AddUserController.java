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

import businessobjects.SettingsCommunicator;
import jNetworking.jNetworkInterface.jNetworkInterface;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import ui.AddUserFrame;

/**
 * Controller class for add user.
 *
 * @author Jacob Gorney
 */
public class AddUserController implements ActionListener {

    /**
     * The GUI reference to IDEFrame.
     */
    private final AddUserFrame addUserFrame;

    /**
     * Add user controller constructor.
     *
     * @param addUserFrame Add user frame GUI
     */
    public AddUserController(final AddUserFrame addUserFrame) {
        // Nothing for now. Just show GUI
        this.addUserFrame = addUserFrame;
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    addUserFrame.setLocationRelativeTo(null);
                    addUserFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        // Action listeners
        this.addUserFrame.jbtnAdd.addActionListener(this);
        this.addUserFrame.jbtnCancel.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        if (ev.getSource() == addUserFrame.jbtnAdd) {
            jbtnAddClick();
        } else if (ev.getSource() == addUserFrame.jbtnCancel) {
            jbtnCancelClick();
        }
    }

    /**
     * jbtnAdd button click.
     */
    private void jbtnAddClick() {
        // Empty username check
        if (addUserFrame.jtfUsername.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(addUserFrame, "Username cannot be empty.", "Validation", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Empty password check
        if (String.valueOf(addUserFrame.jpfPassword.getPassword()).equals("") || 
                String.valueOf(addUserFrame.jpfConfirm.getPassword()).equals("")) {
            JOptionPane.showMessageDialog(addUserFrame, "Password fields cannot be empty.", "Validation", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Password check
        if (!String.valueOf(addUserFrame.jpfPassword.getPassword()).equals(
            String.valueOf(addUserFrame.jpfConfirm.getPassword()))) {
            JOptionPane.showMessageDialog(addUserFrame, "Passwords do not match!", "Validation", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // Run new thread and add the user to the system.
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Setup GUI
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        // setup GUI
                        addUserFrame.jbtnAdd.setEnabled(false);
                        addUserFrame.jbtnCancel.setEnabled(false);
                        addUserFrame.jtfUsername.setEnabled(false);
                        addUserFrame.jpfPassword.setEnabled(false);
                        addUserFrame.jpfConfirm.setEnabled(false);

                    }
                });
                // Send the stuff to the server.
                jNetworkInterface client = new jNetworkInterface(
                        SettingsCommunicator.getServerAddr(),
                        SettingsCommunicator.getServerPort(), false);
                String checkResponse = "";
                boolean addUserError = false;
                try {
                    // Check if this user exists already
                    ArrayList<String> checkData = new ArrayList<>();
                    checkData.add(jNetworkInterface.base64Encode(addUserFrame.jtfUsername.getText().trim()));
                    checkResponse = client.sendCommand("checkuserexists", checkData);
                    if (checkResponse.equals("OK")) {
                        // Add command data
                        ArrayList<String> commandData = new ArrayList<>();
                        commandData.add(jNetworkInterface.base64Encode(addUserFrame.jtfUsername.getText().trim()));
                        commandData.add(jNetworkInterface.base64Encode(String.valueOf(addUserFrame.jpfPassword.getPassword())));
                        // Get response
                        String response = client.sendCommand("adduser", commandData);
                        addUserError = response.equals("ERROR");
                    } else {
                        JOptionPane.showMessageDialog(addUserFrame, "Username already exists in system.", "Error", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                if (!checkResponse.equals("OK") || addUserError) {
                    // Revert GUI
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            // Reset GUI
                            addUserFrame.jbtnAdd.setEnabled(true);
                            addUserFrame.jbtnCancel.setEnabled(true);
                            addUserFrame.jtfUsername.setEnabled(true);
                            addUserFrame.jpfPassword.setEnabled(true);
                            addUserFrame.jpfConfirm.setEnabled(true);
                        }
                    });
                    // Show pane for error.
                    if (addUserError)
                        JOptionPane.showMessageDialog(addUserFrame, "Error adding user.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Close window
                    addUserFrame.dispatchEvent(new WindowEvent(
                            addUserFrame, WindowEvent.WINDOW_CLOSING));
                }
            }
        }).start();
    }

    /**
     * jbtnCancel button click.
     */
    private void jbtnCancelClick() {
        // Close window
        addUserFrame.dispatchEvent(new WindowEvent(
                addUserFrame, WindowEvent.WINDOW_CLOSING));
    }
}
