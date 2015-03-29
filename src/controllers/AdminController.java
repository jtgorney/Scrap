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
import businessobjects.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jNetworking.jNetworkInterface.jNetworkInterface;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashSet;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import ui.AddUserFrame;
import ui.AdminFrame;

/**
 * Administrative GUI controller.
 *
 * @author Jacob Gorney
 */
public class AdminController implements ActionListener {

    /**
     * The GUI reference to IDEFrame.
     */
    private final AdminFrame adminFrame;
    private final DefaultListModel listModel;

    /**
     * Constructor for admin controller.
     *
     * @param adminFrame Admin GUI Frame
     */
    public AdminController(final AdminFrame adminFrame) {
        // Nothing for now. Just show GUI
        this.adminFrame = adminFrame;
        this.listModel = new DefaultListModel();
        // For now, just display the GUI until we build the IDEFrame controller
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    adminFrame.jlstUsers.setModel(listModel);
                    adminFrame.setLocationRelativeTo(null);
                    adminFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        // Add action listeners
        this.adminFrame.jbtnAddUser.addActionListener(this);
        this.adminFrame.jbtnExit.addActionListener(this);
        this.adminFrame.jbtnSave.addActionListener(this);
        // Load user list
        loadUsers();
        // Load other settings
        loadMisc();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == adminFrame.jbtnAddUser) {
            jbtnAddUserClick();
        } else if (e.getSource() == adminFrame.jbtnExit) {
            jbtnExitClick();
        } else if (e.getSource() == adminFrame.jbtnSave) {
            jbtnSaveClick();
        }
    }

    /**
     * Load users into the select box.
     */
    private void loadUsers() {
        // List model
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Setup GUI
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        // setup GUI
                        listModel.clear();
                        adminFrame.jlstUsers.setEnabled(false);
                        adminFrame.jbtnAddUser.setEnabled(false);

                    }
                });
                // Send the stuff to the server.
                jNetworkInterface client = new jNetworkInterface(
                        SettingsCommunicator.getServerAddr(),
                        SettingsCommunicator.getServerPort(), false);
                try {
                    // Get response
                    String response = client.sendCommand("getusers", null);
                    ArrayList<User> data = new Gson().fromJson(response, new TypeToken<ArrayList<User>>() {
                    }.getType());
                    // Iterate and add
                    for (User u : data) {
                        listModel.addElement(u.getId() + " - " + u.getUsername());
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                // Revert GUI
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        // Revert GUI
                        adminFrame.jlstUsers.setEnabled(true);
                        adminFrame.jbtnAddUser.setEnabled(true);

                    }
                });
            }
        }).start();
    }

    /**
     * Loads the other settings boxes
     */
    private void loadMisc() {
       String strContestName = SettingsCommunicator.getCompetitionName();
       String strSchoolName = SettingsCommunicator.getSchoolName();
       adminFrame.jtfContestName.setText(strContestName);
       adminFrame.jtfSchoolName.setText(strSchoolName);
    }
    
    /**
     * Click event for jbtnAddUser.
     */
    private void jbtnAddUserClick() {
        // Run the controller
        // Attach reload event for users when closed.
        AddUserFrame frame = new AddUserFrame();
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent ev) {
                loadUsers();
            }
        });
        new AddUserController(frame);
    }

    /**
     * Save Contest Name and School Name 
     */
    private void jbtnSaveClick() {
        if (JOptionPane.showConfirmDialog(adminFrame, "Are you "
                + "sure you want to save changes?", "Save Changes", 
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
        String strCompName = adminFrame.jtfContestName.getText();
        String strSchoolName = adminFrame.jtfSchoolName.getText();
        SettingsCommunicator.setCompetitionName(strCompName);
        SettingsCommunicator.setSchoolName(strSchoolName);
        }        
    }
    
    /**
     * Exit button click.
     */
    private void jbtnExitClick() {
        adminFrame.dispatchEvent(new WindowEvent(
                adminFrame, WindowEvent.WINDOW_CLOSING));
    }
}
