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

import businessobjects.CompetitionUser;
import businessobjects.SettingsCommunicator;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import ui.IDEFrame;
import ui.LoginFrame;

/**
 * Login controller controls the login GUI and its logic.
 *
 * @author Jacob Gorney
 *
 */
public class LoginController implements ActionListener {

    /**
     * The GUI reference to LoginView.
     */
    private final LoginFrame loginView;

    /**
     * Constructor that takes the GUI for login.
     *
     * @param loginView Login JFrame GUI
     */
    public LoginController(final LoginFrame loginView) {
        // Assign the GUI
        this.loginView = loginView;
        // Open the GUI via EventQueue
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    loginView.setLocationRelativeTo(null);
                    loginView.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        // Set the name of the competition. This will
        // eventually be retrieved from the server.
        loginView.jlblHeader.setText(SettingsCommunicator.getCompetitionName());
        loginView.jlblSchool.setText(SettingsCommunicator.getCompetitionSchool());
        loginView.repaint();
        // Build listeners
        loginView.jbtnEnter.addActionListener(this);
        loginView.jbtnExit.addActionListener(this);
        loginView.jtfUsername.addActionListener(this);
        loginView.jpfPassword.addActionListener(this);
        // Focus the username field
        loginView.jtfUsername.requestFocus();
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        if (ev.getSource() == loginView.jbtnEnter) {
            jbtnEnterClick();
        } else if (ev.getSource() == loginView.jbtnExit) {
            jbtnExitClick();
        } else if (ev.getSource() == loginView.jtfUsername) {
            jtfUsernameEnter();
        } else if (ev.getSource() == loginView.jpfPassword) {
            jtfPasswordEnter();
        } else if (ev.getSource() == loginView.jbtnExit) {
            jbtnExitClick();
        }
    }

    /**
     * jtfUsername enter key press.
     */
    public void jtfUsernameEnter() {
        // Focus password field
        loginView.jpfPassword.requestFocus();
    }

    /**
     * jtfPassword enter key press.
     */
    public void jtfPasswordEnter() {
        // Perform login
        // @todo run in thread
        doLogin();
    }

    /**
     * Event method for jbtnEnter object.
     */
    private void jbtnEnterClick() {
        // Perform login
        // @todo run in thread
        doLogin();
    }

    /**
     * Event for jbtnExit object.
     */
    private void jbtnExitClick() {
        // Ask to exit the system
        if (JOptionPane.showConfirmDialog(loginView,
                "Are you sure you wish to exit?", "Exit System",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
            System.exit(0);
        }
    }

    /**
     * Perform the login operation.
     */
    private void doLogin() {
        // Run in new thread to update GUI.
        new Thread() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        // Show progress
                        loginView.jpbLoading.setVisible(true);
                        // Disable fields
                        loginView.jtfUsername.setEnabled(false);
                        loginView.jpfPassword.setEnabled(false);
                        loginView.jbtnEnter.setEnabled(false);
                        loginView.jbtnExit.setEnabled(false);
                    }
                });

                // Get credentials
                String username = loginView.jtfUsername.getText();
                String password = String.copyValueOf(loginView.jpfPassword.getPassword());
                // Check for invalid username or password (blank)
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(loginView,
                            "Username and password cannot be empty.",
                            "Login", JOptionPane.WARNING_MESSAGE);
                } else {
                    // Make the call
                    CompetitionUser user = new CompetitionUser(username, password);
                    // Authenticate them
                    user.authenticate();
                    // Apply the logic
                    if (user.isAuthenticated()) {
                        // Show the IDE view.
                        loginView.setVisible(false);
                        // Run the IDE controller
                        JOptionPane.showMessageDialog(loginView, "You have successfully authenticated.",
                                "Authentication Successful", JOptionPane.INFORMATION_MESSAGE);
                        IDEController controller = new IDEController(new IDEFrame(), user);
                    } else {
                        JOptionPane.showMessageDialog(loginView,
                                "Invalid username or password.", "Login",
                                JOptionPane.WARNING_MESSAGE);
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                // Reset password field and focus username
                                loginView.jpfPassword.setText("");
                                loginView.jtfUsername.requestFocus();
                                loginView.jtfUsername.selectAll();
                            }
                        });
                    }
                }
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        // Hide progress
                        loginView.jpbLoading.setVisible(false);
                        // Enable fields
                        loginView.jtfUsername.setEnabled(true);
                        loginView.jpfPassword.setEnabled(true);
                        loginView.jbtnEnter.setEnabled(true);
                        loginView.jbtnExit.setEnabled(true);
                    }
                });
            }
        }.start();
    }
}
