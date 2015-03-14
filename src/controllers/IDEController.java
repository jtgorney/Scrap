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
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
        // Action listeners
        ideFrame.jbtnTestSolution.addActionListener(this);
        ideFrame.jbtnSubmitSolution.addActionListener(this);
        ideFrame.mntmExitSystem.addActionListener(this);
        ideFrame.mntmLogout.addActionListener(this);
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
        }
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
