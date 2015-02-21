/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import ui.TestSolutionFrame;

/**
 * Test Solution Controller.
 * @author Jacob Gorney
 */
public class TestSolutionController implements ActionListener {

    /**
     * The GUI reference to LoginView.
     */
    private final TestSolutionFrame testSolutionFrame;
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
    public TestSolutionController(final TestSolutionFrame frame, CompetitionUser user,
            int problemId, String code, String compiler) {
        // Assign the GUI and data
        this.testSolutionFrame = frame;
        this.user = user;
        this.problemId = problemId;
        this.sourceCode = code;
        this.compiler = compiler;
        // Open the GUI via EventQueue
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    testSolutionFrame.setLocationRelativeTo(null);
                    testSolutionFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        testSolutionFrame.btnCancel.addActionListener(this);
        testSolutionFrame.btnTest.addActionListener(this);
        // Add the closing operation to show parent frame
        testSolutionFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent ev) {
                showParent();
            }
        });
        // Set the header label with the problem Id
        testSolutionFrame.lblProblemNumber.setText("Problem Number: " +
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

    @Override
    public void actionPerformed(ActionEvent ev) {
        if (ev.getSource() == testSolutionFrame.btnCancel) {
            btnCancelClick();
        } else if (ev.getSource() == testSolutionFrame.btnTest) {
            btnTestClick();
        }
    }
    
    /**
     * Test solution button click event.
     */
    private void btnTestClick() {
        // Build a new thread and run the operation.
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Setup GUI
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        // setup GUI
                        testSolutionFrame.jlblTesting.setVisible(true);
                        testSolutionFrame.jpbLoading.setVisible(true);
                        testSolutionFrame.btnCancel.setEnabled(false);
                        testSolutionFrame.btnTest.setEnabled(false);

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
                    String response = client.sendCommand("testsolution", commandData);
                    long compilerToken = Long.parseLong(response);
                    // Wait while we get the response from the server.
                    boolean isReturned = false;
                    int runs = 0;
                    while (!isReturned && runs < 10) {
                        Thread.sleep(5000);
                        ArrayList<String> compilerData = new ArrayList<>();
                        compilerData.add(String.valueOf(user.getId()));
                        compilerData.add(String.valueOf(compilerToken));
                        response = client.sendCommand("getresult", compilerData);
                        if (!response.equals("PROCESSING")) {
                            isReturned = true;
                        } else
                            runs++;
                    }
                    // Decode the stream
                    response = jNetworkInterface.base64Decode(response);
                    // Display the result to the user
                    System.out.println(response);
                    JOptionPane.showMessageDialog(testSolutionFrame, response);
                    // Close
                    testSolutionFrame.dispatchEvent(new WindowEvent(
                    testSolutionFrame, WindowEvent.WINDOW_CLOSING));
                    showParent();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                // Revert GUI
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        // Revert GUI
                        testSolutionFrame.jlblTesting.setVisible(false);
                        testSolutionFrame.jpbLoading.setVisible(false);
                        testSolutionFrame.btnCancel.setEnabled(true);
                        testSolutionFrame.btnTest.setEnabled(true);

                    }
                });
            }
        }).start();
    }

    /**
     * Cancel button click event.
     */
    private void btnCancelClick() {
        this.testSolutionFrame.dispatchEvent(new WindowEvent(
                testSolutionFrame, WindowEvent.WINDOW_CLOSING));
        this.showParent();
    }
}
