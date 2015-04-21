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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jNetworking.jNetworkInterface.jNetworkInterface;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import ui.JudgeViewFrame;

/**
 * Login controller controls the login GUI and its logic.
 *
 * @author Jacob Gorney
 *
 */
public class JudgeViewController implements ActionListener {

    /**
     * The GUI reference to JudgeViewFrame.
     */
    private final JudgeViewFrame judgeViewFrame;
    /**
     * ID of submission.
     */
    private final int id;

    /**
     * Constructor that takes the GUI for login.
     *
     * @param judgeViewFrame Login JFrame GUI
     */
    public JudgeViewController(final JudgeViewFrame judgeViewFrame, final int id) {
        // Assign the GUI
        this.judgeViewFrame = judgeViewFrame;
        // Open the GUI via EventQueue
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    judgeViewFrame.setLocationRelativeTo(null);
                    judgeViewFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        // Set id
        this.id = id;
        judgeViewFrame.setTitle("View Solution for ID: " + id);
        judgeViewFrame.jbtnClose.addActionListener(this);
        // Load the data
        jNetworkInterface client = new jNetworkInterface(
                        SettingsCommunicator.getServerAddr(),
                        SettingsCommunicator.getServerPort(), false);
        ArrayList<String> clientData = new ArrayList<>();
        clientData.add(String.valueOf(id));
        String response = client.sendCommand("getresultfromdb", clientData);
        ArrayList<String> data = new Gson().fromJson(response, new TypeToken<ArrayList<String>>() {
            }.getType());
        // Set the data
        judgeViewFrame.rstaCode.setText(jNetworkInterface.base64Decode(data.get(0)));
        judgeViewFrame.jtaOutput.setText(jNetworkInterface.base64Decode(data.get(1)));
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        if (ev.getSource() == judgeViewFrame.jbtnClose) {
            judgeViewFrame.dispose();
        }
    }
}
