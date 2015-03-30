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

import db.DBMgr;
import jNetworking.jNetworkInterface.jNetworkInterfaceServer;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import ui.ServerFrame;

/**
 * Administrative GUI controller.
 *
 * @author Jacob Gorney
 */
public class ServerController implements ActionListener {

    /**
     * The GUI reference to IDEFrame.
     */
    private final ServerFrame serverFrame;
    /**
     * Server object.
     */
    private jNetworkInterfaceServer server;
    /**
     * Boolean to determine start and stop of server.
     */
    private boolean isRunning = false;
    /**
     * Server port
     */
    private int serverPort = 8888;

    /**
     * Constructor for server controller.
     *
     * @param serverFrame Server GUI Frame
     */
    public ServerController(final ServerFrame serverFrame) {
        // Nothing for now. Just show GUI
        this.serverFrame = serverFrame;
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    serverFrame.setLocationRelativeTo(null);
                    serverFrame.setVisible(true);
                    serverFrame.jbtnStop.setEnabled(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        // Add action listeners
        serverFrame.jbtnStart.addActionListener(this);
        serverFrame.jbtnStop.addActionListener(this);
        serverFrame.jbtnExit.addActionListener(this);

        serverFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        serverFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                if (!isRunning) {
                    if (JOptionPane.showConfirmDialog(serverFrame, "Are you "
                            + "sure you want to exit?", "Close Server",
                            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        serverFrame.dispose();
                        System.exit(0);
                    }
                } else {
                    if (JOptionPane.showConfirmDialog(serverFrame, "Your server is still running."
                            + " Are you sure you wish to exit?", "Close Server",
                            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        serverFrame.dispose();
                        System.exit(0);
                    }
                }
            }
        });
        
        // Append a dummy log
        serverFrame.jtaLog.append("NOTICE\tApplication started." + System.getProperty("line.separator"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == serverFrame.jbtnStart) {
            jbtnStartClick();
        } else if (e.getSource() == serverFrame.jbtnStop) {
            jbtnStopClick();
        } else if (e.getSource() == serverFrame.jbtnExit) {
            jbtnExitClick();
        }
    }

    /**
     * Exit button click.
     */
    public void jbtnExitClick() {
        if (!isRunning) {
            if (JOptionPane.showConfirmDialog(serverFrame, "Are you "
                    + "sure you want to exit?", "Close Server",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                serverFrame.dispose();
                System.exit(0);
            }
        } else {
            if (JOptionPane.showConfirmDialog(serverFrame, "Your server is still running."
                    + " Are you sure you wish to exit?", "Close Server",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                serverFrame.dispose();
                System.exit(0);
            }
        }
    }

    /**
     * Stop server button.
     */
    public void jbtnStopClick() {
        isRunning = false;
        serverFrame.jbtnStart.setEnabled(true);
        serverFrame.jbtnStop.setEnabled(false);
        
        serverFrame.jbtnStart.setEnabled(true);
        serverFrame.jbtnStop.setEnabled(true);
        serverFrame.jtfServerPort.setEnabled(true);
        serverFrame.jtfMySQLHost.setEnabled(true);
        serverFrame.jtfMySQLPort.setEnabled(true);
        serverFrame.jtfMySQLDatabase.setEnabled(true);
        serverFrame.jtfMySQLUsername.setEnabled(true);
        serverFrame.jpfMySQLPassword.setEnabled(true);
        
        server.stop();
    }

    /**
     * Start button click.
     */
    public void jbtnStartClick() {
        // Set the new settings.
        refreshSettings();
        // Set vars
        isRunning = true;
        
        serverFrame.jbtnStart.setEnabled(false);
        serverFrame.jbtnStop.setEnabled(true);
        
        serverFrame.jtfServerPort.setEnabled(false);
        serverFrame.jtfMySQLHost.setEnabled(false);
        serverFrame.jtfMySQLPort.setEnabled(false);
        serverFrame.jtfMySQLDatabase.setEnabled(false);
        serverFrame.jtfMySQLUsername.setEnabled(false);
        serverFrame.jpfMySQLPassword.setEnabled(false);
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Not setting the location of the log file will default to the root drive.
                // Ensure this program is executed with appropriate filesystem permissions.
                // LogLocation.setLocation("/home/jacob/Desktop/log.txt");
                // Create the server
                // Get the server input stuff
                server = new jNetworkInterfaceServer(serverPort, 1024, false);
                server.setServerName("Scrap Competition Server");
                server.run();
            }
        }).start();
        // Redirect output
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Redirect System.out to text field.
                try {
                    redirectOutput();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * Refresh the server settings.
     */
    private void refreshSettings() {
        // Set MySQL settings
        DBMgr.setDBDatabase(serverFrame.jtfMySQLDatabase.getText().trim());
        DBMgr.setDBHost(serverFrame.jtfMySQLHost.getText().trim());
        DBMgr.setDBUsername(serverFrame.jtfMySQLUsername.getText().trim());
        DBMgr.setDBPassword(String.valueOf(serverFrame.jpfMySQLPassword.getPassword()));
        // Set server settings
        serverPort = Integer.valueOf(serverFrame.jtfServerPort.getText().trim());
    }

    /**
     * Redirect STDOUT to the log window. Stops when killed.
     *
     * @throws IOException
     * @throws InterruptedException
     */
    private void redirectOutput() {
        PipedOutputStream pOut = new PipedOutputStream();
        PipedInputStream pIn = null;
        try {
            pIn = new PipedInputStream(pOut);
        } catch (Exception ex) {
            
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(pIn));
        while (isRunning) {
            System.setOut(new PrintStream(pOut));
            String line = "";
            try {
                line = reader.readLine();
            } catch (Exception ex) {
                
            }
            if (line != null && !line.trim().equals("")) {
                serverFrame.jtaLog.append(line + System.getProperty("line.separator"));
                serverFrame.jtaLog.setCaretPosition(serverFrame.jtaLog.getDocument().getLength());
            }
        }
        // Reset
        System.setOut(System.out);
    }
}
