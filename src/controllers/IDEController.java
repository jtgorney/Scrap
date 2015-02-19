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
import businessobjects.User;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ui.IDEFrame;
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
    }
    
    @Override
    public void actionPerformed(ActionEvent ev) {
        if (ev.getSource() == ideFrame.jbtnSubmitSolution) {
            jbtnSubmitSolutionClick();
        } else if (ev.getSource() == ideFrame.jbtnTestSolution) {
            jbtnTestSolutionClick();
        }
    }
    
    /**
     * Submit Solution button click.
     */
    private void jbtnSubmitSolutionClick() {
        
    }
    
    /**
     * Test Solution button click.
     */
    private void jbtnTestSolutionClick() {
        TestSolutionController controller = new TestSolutionController(
            new TestSolutionFrame());
        // Hide windows
        controller.setParentIDEFrame(ideFrame);
        controller.hideParent();
    }
}
