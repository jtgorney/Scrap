/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import businessobjects.CompetitionUser;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.SwingUtilities;
import ui.IDEFrame;
import ui.TestSolutionFrame;

/**
 *
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
    private final CompetitionUser user;
    private final int problemId;

    /**
     * Construct a test solution controller.
     * @param frame GUI
     * @param user Competition User
     * @param problemId Problem set ID
     * @param code Source code
     */
    public TestSolutionController(final TestSolutionFrame frame, CompetitionUser user,
            int problemId, String code) {
        // Assign the GUI and data
        this.testSolutionFrame = frame;
        this.user = user;
        this.problemId = problemId;
        this.sourceCode = code;
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
    }
    
    /**
     * Set the parent IDE frame.
     * @param frame IDE frame
     */
    public void setParentIDEFrame(IDEFrame frame) {
        parent = frame;
    }
    
    /**
     * Hide the parent JFrame.
     */
    public void hideParent() {
        if (parent != null)
            parent.setVisible(false);
    }
    
    /**
     * Show the parent JFrame.
     */
    public void showParent() {
        if (parent != null)
            parent.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent ev) {
        if (ev.getSource() == testSolutionFrame.btnCancel)
            btnCancelClick();
        else if (ev.getSource() == testSolutionFrame.btnTest)
            btnTestClick();
    }
    
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
                try {
                    Thread.sleep(7000);
                } catch (Exception ex) {
                    
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
    
    private void btnCancelClick() {
        this.testSolutionFrame.dispatchEvent(new WindowEvent(
            testSolutionFrame, WindowEvent.WINDOW_CLOSING));
        this.showParent();
    }
}
