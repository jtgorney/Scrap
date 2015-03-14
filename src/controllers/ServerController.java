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

import jNetworking.jNetworkInterface.jNetworkInterfaceServer;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
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
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        // Add action listeners
        serverFrame.jbtnStart.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == serverFrame.jbtnStart) {
            jbtnStartClick();
        }
    }

    public void jbtnStartClick() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Not setting the location of the log file will default to the root drive.
                // Ensure this program is executed with appropriate filesystem permissions.
                // LogLocation.setLocation("/home/jacob/Desktop/log.txt");
                // Create the server
                // Get the server input stuff
                server = new jNetworkInterfaceServer(8888, 100, false);
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

    private void redirectOutput() throws IOException, InterruptedException {
        PipedOutputStream pOut = new PipedOutputStream();
        System.setOut(new PrintStream(pOut));
        PipedInputStream pIn = new PipedInputStream(pOut);
        BufferedReader reader = new BufferedReader(new InputStreamReader(pIn));
        while (true) {
            String line = reader.readLine();
            if (line != null) {
                serverFrame.jtaLog.append(line + System.getProperty("line.separator"));
                serverFrame.jtaLog.setCaretPosition(serverFrame.jtaLog.getDocument().getLength());
            }
            Thread.sleep(2000);
        }
    }
}
