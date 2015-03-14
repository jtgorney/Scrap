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
        
        // Redirect System.out to text field.
        try {
            redirectOutput();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
    private void redirectOutput() throws IOException {
        PipedOutputStream pOut = new PipedOutputStream();
        System.setOut(new PrintStream(pOut));
        PipedInputStream pIn = new PipedInputStream(pOut);
        BufferedReader reader = new BufferedReader(new InputStreamReader(pIn));
        while (true) {
            String line = reader.readLine();
            if (line != null)
                serverFrame.jtaLog.append(line);
        }
    }
}