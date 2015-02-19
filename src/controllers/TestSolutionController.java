/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
     * Constructor that takes the GUI for login.
     *
     * @param frame Test Solution JFrame GUI
     */
    public TestSolutionController(final TestSolutionFrame frame) {
        // Assign the GUI
        this.testSolutionFrame = frame;
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
        
    }
}
