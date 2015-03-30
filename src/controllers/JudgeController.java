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
import businessobjects.Submission;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jNetworking.jNetworkInterface.jNetworkInterface;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import ui.JudgeFrame;

/**
 * Administrative GUI controller.
 *
 * @author Jacob Gorney
 */
public class JudgeController implements ActionListener {

    /**
     * The GUI reference to IDEFrame.
     */
    private final JudgeFrame judgeFrame;

    /**
     * Constructor for judge controller.
     *
     * @param judgeFrame Judge GUI Frame
     */
    public JudgeController(final JudgeFrame judgeFrame) {
        // Nothing for now. Just show GUI
        this.judgeFrame = judgeFrame;
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // judgeFrame.jtblSubmissions.getRowSorter().toggleSortOrder(0);
                    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                    centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
                    judgeFrame.jtblSubmissions.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
                    judgeFrame.jtblSubmissions.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
                    judgeFrame.jtblSubmissions.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
                    judgeFrame.jtblSubmissions.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
                    judgeFrame.jtblSubmissions.setRowHeight(26);
                    judgeFrame.setLocationRelativeTo(null);
                    judgeFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        judgeFrame.jbtnClarifications.addActionListener(this);
        judgeFrame.jbtnExit.addActionListener(this);
        judgeFrame.jbtnRefresh.addActionListener(this);
        judgeFrame.jbtnScoreboard.addActionListener(this);
        // Load user list
        loadSubmissions();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == judgeFrame.jbtnRefresh) {
            loadSubmissions();
        } else if (e.getSource() == judgeFrame.jbtnExit) {
            jbtnExitClick();
        }
    }

    /**
     * Exit Button
     */
    private void jbtnExitClick() {
        if (JOptionPane.showConfirmDialog(judgeFrame, "Are you sure you wish to quit?",
                "Exit", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    /**
     * Load users into the select box.
     */
    private void loadSubmissions() {
        // List model
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Setup GUI
                DefaultTableModel model = (DefaultTableModel) judgeFrame.jtblSubmissions.getModel();
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        // setup GUI
                        model.setRowCount(0);
                        judgeFrame.jbtnClarifications.setEnabled(false);
                        judgeFrame.jbtnRefresh.setEnabled(false);
                        judgeFrame.jbtnScoreboard.setEnabled(false);
                    }
                });
                // Send the stuff to the server.
                jNetworkInterface client = new jNetworkInterface(
                        SettingsCommunicator.getServerAddr(),
                        SettingsCommunicator.getServerPort(), false);
                try {
                    // Get response
                    String response = client.sendCommand("getsubmissions", null);
                    ArrayList<Submission> data = new Gson().fromJson(response, new TypeToken<ArrayList<Submission>>() {
                    }.getType());
                    // Iterate and add

                    // Add row to model here
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            // setup GUI
                            for (Submission s : data) {
                                Object[] row = {s.getSubmissionId(), s.getProblemId(), s.getTeamName(), s.isAccepted(), s.getScore()};
                                model.addRow(row);
                            }
                        }
                    });

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                // Revert GUI
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        // Revert GUI
                        judgeFrame.jbtnClarifications.setEnabled(true);
                        judgeFrame.jbtnRefresh.setEnabled(true);
                        judgeFrame.jbtnScoreboard.setEnabled(true);
                    }
                });
            }
        }).start();
    }
}
