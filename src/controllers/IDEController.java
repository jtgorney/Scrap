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
import businessobjects.Problem;
import businessobjects.SettingsCommunicator;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jNetworking.jNetworkInterface.jNetworkInterface;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import ui.IDEFrame;
import ui.SubmitSolutionFrame;
import ui.TestSolutionFrame;

/**
 * IDEFrame Controller for programming interaction.
 *
 * @author Jacob Gorney
 *
 */
public class IDEController implements ActionListener {
    /**
     * The GUI reference to IDEFrame.
     */
    private final IDEFrame ideFrame;
    /**
     * GUI Ref to login frame.
     */
    private JFrame parent;
    /**
     * User object.
     */
    private final CompetitionUser user;

    /**
     * Constructor for IDEFrame GUI.
     *
     * @param ideView IDEFrame class
     * @param user User object
     */
    public IDEController(final IDEFrame ideView, CompetitionUser user) {
        // Nothing for now. Just show GUI
        this.ideFrame = ideView;
        this.user = user;
        
        // Setting The Top Bar JLabel
        ideFrame.jlblTopContent.setText(SettingsCommunicator.getCompetitionSchool() 
                + " | " + SettingsCommunicator.getCompetitionName() + 
                " | Time Remaining: 03:00:00 | Team: " + user.getUsername());
        
        // For now, just display the GUI until we build the IDEFrame controller
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ideView.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        // Build the problem set
        buildProblemSet();
        // Action listeners
        ideFrame.jbtnTestSolution.addActionListener(this);
        ideFrame.jbtnSubmitSolution.addActionListener(this);
        ideFrame.mntmExitSystem.addActionListener(this);
        ideFrame.mntmLogout.addActionListener(this);
        ideFrame.mntmAboutScrap.addActionListener(this);
        ideFrame.mntmCalculator.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent ev) {
        if (ev.getSource() == ideFrame.jbtnSubmitSolution) {
            jbtnSubmitSolutionClick();
        } else if (ev.getSource() == ideFrame.jbtnTestSolution) {
            jbtnTestSolutionClick();
        } else if (ev.getSource() == ideFrame.mntmExitSystem) {
            mntmExitSystemClick();
        } else if (ev.getSource() == ideFrame.mntmLogout) {
            mntmLogoutClick();
        } else if (ev.getSource() == ideFrame.mntmAboutScrap) {
            mntmAboutScrapClick();
        } else if (ev.getSource() == ideFrame.mntmCalculator) {
            mntmCalculatorClick();
        }
    }
    
    private void buildProblemSet() {
        // Build a server connection
        jNetworkInterface client = new jNetworkInterface(
                    SettingsCommunicator.getServerAddr(),
                    SettingsCommunicator.getServerPort(), false);
        // Get the problem set
        ArrayList<Problem> data = new Gson().fromJson(client.sendCommand(
                "getproblemset", null), new TypeToken<ArrayList<Problem>>() {}
                .getType());
        // Build the tabs
        JPanel problemPanel;
        JTextArea problemDescription;
        // Loop and build
        for (Problem p : data) {
            problemPanel = new JPanel();
            problemPanel.setBackground(Color.WHITE);
            ideFrame.tpProblemSet.addTab("Problem " + p.getProblemNumber(), null, problemPanel, null);
            problemPanel.setLayout(new BorderLayout(0, 0));
            
            problemDescription = new JTextArea();
            problemDescription.setEditable(false);
            problemDescription.setText("Title: " + p.getProblemTitle() +
                    System.getProperty("line.separator") + 
                    System.getProperty("line.separator") + p.getProblemText());
            problemDescription.setFont(new Font("Tahoma", Font.PLAIN, 12));
            problemDescription.setLineWrap(true);
            problemDescription.setMargin(new Insets(10, 10, 10, 10));
            problemPanel.add(problemDescription, BorderLayout.CENTER);
        }
        ideFrame.repaint();
    }
    
    /**
     * Set the parent frame.
     * @param p Parent
     */
    public void setParent(JFrame p) {
        this.parent = p;
    }
    
    /**
     * Logout system event handle.
     */
    public void mntmLogoutClick() {
        if (JOptionPane.showConfirmDialog(ideFrame, "Are you "
                + "sure you want to logout?", "Logout System",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            parent.setVisible(true);
            ideFrame.setVisible(false);
            ideFrame.dispose();
        }
    }
    
    /**
     * Exit system event handle.
     */
    public void mntmExitSystemClick() {
        if (JOptionPane.showConfirmDialog(ideFrame, "Are you "
                + "sure you want to exit?", "Exit System", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
            System.exit(0);
    }
    
    /**
     * Calculator handle
     * Only checks for windows or linux
     */
    public void mntmCalculatorClick() {
        //Getting the OS
        String strOS = System.getProperty("os.name").toLowerCase();
        
        //Checking Windows
        if (strOS.indexOf("win") >= 0) {
            try {
                Runtime.getRuntime().exec("calc");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else { // Checking Linux
            try {
                Process process = Runtime.getRuntime().exec("gnome-calculator");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * About Scrap event handle.
     */
    public void mntmAboutScrapClick() {
        JOptionPane.showMessageDialog(ideFrame, 
                "Scrap is an ambitious open source project geared at replacing or\n"
                + "complementing the popular programming competition software\n" 
                + "PC2. Scrap is geared towards teachers and institutions that want\n"
                + "to provide and easy to use competition/testing environment for\n"
                + "programming problems in which students can write code in an IDE\n"
                + "like environment and submit to a server for processing and judging.",
                "About Scrap", JOptionPane.PLAIN_MESSAGE);
    }
    
    /**
     * Submit Solution button click.
     */
    private void jbtnSubmitSolutionClick() {
        if (!ideFrame.rstaCode.getText().trim().equals("")) {
            // Run the controller
            SubmitSolutionController controller = new SubmitSolutionController(
                new SubmitSolutionFrame(), user,
                    (ideFrame.tpProblemSet.getSelectedIndex() + 1),
                    ideFrame.rstaCode.getText(), getCompilerType());
            // Hide windows
            controller.setParentIDEFrame(ideFrame);
            controller.hideParent();
        } else {
            JOptionPane.showMessageDialog(ideFrame, "Pleae input a program to submit.",
                    "No Program", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    /**
     * Test Solution button click.
     */
    private void jbtnTestSolutionClick() {
        if (!ideFrame.rstaCode.getText().trim().equals("")) {
            // Run the controller
            TestSolutionController controller = new TestSolutionController(
                new TestSolutionFrame(), user,
                    (ideFrame.tpProblemSet.getSelectedIndex() + 1),
                    ideFrame.rstaCode.getText(), getCompilerType());
            // Hide windows
            controller.setParentIDEFrame(ideFrame);
            controller.hideParent();
        } else {
            JOptionPane.showMessageDialog(ideFrame, "Pleae input a program to test.",
                    "No Program", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    /**
     * Return the compiler type.
     * @return Compiler type/ext
     */
    private String getCompilerType() {
        switch (ideFrame.jcmbLanguage.getSelectedIndex()) {
            case 0:
                return "cpp";
            case 1:
                return "java";
            default:
                return "cpp";
        }
    }
}
