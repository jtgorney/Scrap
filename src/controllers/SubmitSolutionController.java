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
import jNetworking.jNetworkInterface.jNetworkInterface;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import ui.IDEFrame;
import ui.SubmitSolutionFrame;

/**
 * Test Solution Controller.
 * @author Jacob Gorney
 */
public class SubmitSolutionController implements ActionListener {
    /**
     * The GUI reference to LoginView.
     */
    private final SubmitSolutionFrame submitSolutionFrame;
    /**
     * The parent IDE View.
     */
    private IDEFrame parent;
    /**
     * Source code builder.
     */
    private final String sourceCode;
    /**
     * User reference.
     */
    private final CompetitionUser user;
    /**
     * Problem ID
     */
    private final int problemId;
    /**
     * The compiler type.
     */
    private final String compiler;

    /**
     * Construct a test solution controller.
     *
     * @param frame GUI
     * @param user Competition User
     * @param problemId Problem set ID
     * @param code Source code
     * @param compiler Compiler type
     */
    public SubmitSolutionController(final SubmitSolutionFrame frame, CompetitionUser user,
            int problemId, String code, String compiler) {
        // Assign the GUI and data
        this.submitSolutionFrame = frame;
        this.user = user;
        this.problemId = problemId;
        this.sourceCode = code;
        this.compiler = compiler;
        // Open the GUI via EventQueue
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    submitSolutionFrame.setLocationRelativeTo(null);
                    submitSolutionFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        submitSolutionFrame.btnCancel.addActionListener(this);
        submitSolutionFrame.btnSubmitSolution.addActionListener(this);
        // Add the closing operation to show parent frame
        submitSolutionFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent ev) {
                showParent();
            }
        });
        // Set the header label with the problem Id
        submitSolutionFrame.lblProblemNumber.setText("Problem Number: " +
                String.valueOf(problemId));
    }

    /**
     * Set the parent IDE frame.
     *
     * @param frame IDE frame
     */
    public void setParentIDEFrame(IDEFrame frame) {
        parent = frame;
    }

    /**
     * Hide the parent JFrame.
     */
    public void hideParent() {
        if (parent != null) {
            parent.setVisible(false);
        }
    }

    /**
     * Show the parent JFrame.
     */
    public void showParent() {
        if (parent != null) {
            parent.setVisible(true);
        }
    }
    
    /**
     * Remove a problem from the tab controller.
     * @param problemNumber Problem number.
     */
    public void removeProblemFromIDE(int problemNumber) {
        if (parent != null)
            parent.tpProblemSet.remove(problemNumber-1);
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        if (ev.getSource() == submitSolutionFrame.btnCancel) {
            btnCancelClick();
        } else if (ev.getSource() == submitSolutionFrame.btnSubmitSolution) {
            btnSubmitSolutionClick();
        }
    }
    
    /**
     * Test solution button click event.
     */
    private void btnSubmitSolutionClick() {
        // Build a new thread and run the operation.
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Setup GUI
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        // setup GUI
                        submitSolutionFrame.lblSubmitting.setVisible(true);
                        submitSolutionFrame.pbSubmitting.setVisible(true);
                        submitSolutionFrame.btnCancel.setEnabled(false);
                        submitSolutionFrame.btnSubmitSolution.setEnabled(false);

                    }
                });
                // Send the stuff to the server.
                jNetworkInterface client = new jNetworkInterface(
                    SettingsCommunicator.getServerAddr(),
                    SettingsCommunicator.getServerPort(), false);
                try {
                    // Add command data
                    ArrayList<String> commandData = new ArrayList<>();
                    commandData.add(jNetworkInterface.base64Encode(String.valueOf(user.getId())));
                    commandData.add(jNetworkInterface.base64Encode(String.valueOf(problemId)));
                    commandData.add(jNetworkInterface.base64Encode(compiler));
                    commandData.add(jNetworkInterface.base64Encode(sourceCode));
                    // Get response
                    Thread.sleep(5000);
                    String response = client.sendCommand("submitsolution", commandData);
                    long compilerToken = Long.parseLong(response);
                    // Wait while we get the response from the server.
                    boolean isReturned = false;
                    int runs = 0;
                    while (!isReturned && runs < 10) {
                        Thread.sleep(5000);
                        ArrayList<String> compilerData = new ArrayList<>();
                        compilerData.add(String.valueOf(user.getId()));
                        compilerData.add(String.valueOf(compilerToken));
                        response = client.sendCommand("getsubmissionresult", compilerData);
                        if (!response.equals("PROCESSING")) {
                            isReturned = true;
                        } else
                            runs++;
                    }
                    // Show submission message to user
                    if (response.equals("INVALID"))
                        JOptionPane.showMessageDialog(parent, "Your solution was incorrect! Please revise and resubmit. "
                                + "Your score has been updated.", "Incorrect Score", JOptionPane.WARNING_MESSAGE);
                    else if (response.equals("ACCEPTED")) {
                        JOptionPane.showMessageDialog(parent, "Solution accepted! Your score will be updated momentarily.",
                                "Accepted", JOptionPane.INFORMATION_MESSAGE);
                        removeProblemFromIDE(problemId);
                    }

                    // Close
                    submitSolutionFrame.dispatchEvent(new WindowEvent(
                    submitSolutionFrame, WindowEvent.WINDOW_CLOSING));
                    showParent();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                // Revert GUI
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        // Revert GUI
                        submitSolutionFrame.lblSubmitting.setVisible(false);
                        submitSolutionFrame.pbSubmitting.setVisible(false);
                        submitSolutionFrame.btnCancel.setEnabled(true);
                        submitSolutionFrame.btnSubmitSolution.setEnabled(true);

                    }
                });
            }
        }).start();
    }

    /**
     * Cancel button click event.
     */
    private void btnCancelClick() {
        this.submitSolutionFrame.dispatchEvent(new WindowEvent(
                submitSolutionFrame, WindowEvent.WINDOW_CLOSING));
        this.showParent();
    }
}
