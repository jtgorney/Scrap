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
package ui;


import javax.swing.JFrame;


import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.JProgressBar;

public class SubmitSolutionFrame extends JFrame {
    /**
     * Problem number label.
     */
    public JLabel lblProblemNumber;
    /**
     * Submit solution button.
     */
    public JButton btnSubmitSolution;
    /**
     * Cancel button.
     */
    public JButton btnCancel;
    /**
     * Submitting progress bar.
     */
    public JProgressBar pbSubmitting;
    /**
     * Submitting label.
     */
    public JLabel lblSubmitting;
    
    /**
     * Create the application.
     */
    public SubmitSolutionFrame() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        this.setAlwaysOnTop(false);
        this.setTitle("Submit Your Solution");
        this.setResizable(false);
        this.setBounds(100, 100, 402, 282);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(204, 0, 0));
        panel.setBounds(0, 0, 402, 64);
        this.getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblHeader = new JLabel("Submit Solution");
        lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
        lblHeader.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblHeader.setForeground(Color.WHITE);
        lblHeader.setBounds(0, 11, 392, 24);
        panel.add(lblHeader);

        lblProblemNumber = new JLabel("Problem Number: 1");
        lblProblemNumber.setHorizontalAlignment(SwingConstants.CENTER);
        lblProblemNumber.setForeground(Color.WHITE);
        lblProblemNumber.setFont(new Font("Tahoma", Font.BOLD, 10));
        lblProblemNumber.setBounds(0, 35, 392, 24);
        panel.add(lblProblemNumber);

        btnSubmitSolution = new JButton("Submit Solution");
        btnSubmitSolution.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnSubmitSolution.setBounds(176, 215, 129, 30);
        this.getContentPane().add(btnSubmitSolution);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(307, 215, 79, 30);
        this.getContentPane().add(btnCancel);

        JTextArea txtrYouAreAbout = new JTextArea();
        txtrYouAreAbout.setBackground(UIManager.getColor("Panel.background"));
        txtrYouAreAbout.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtrYouAreAbout.setLineWrap(true);
        txtrYouAreAbout.setEditable(false);
        txtrYouAreAbout.setWrapStyleWord(true);
        txtrYouAreAbout.setText("You are about to submit your final solution to the server. Failed runs WILL count against your overall score. Results might not be immediate. Click the 'Submit Solution' button to continue.");
        txtrYouAreAbout.setBounds(10, 75, 376, 85);
        this.getContentPane().add(txtrYouAreAbout);

        pbSubmitting = new JProgressBar();
        pbSubmitting.setIndeterminate(true);
        pbSubmitting.setValue(50);
        pbSubmitting.setBounds(10, 219, 156, 23);
        pbSubmitting.setVisible(false);
        this.getContentPane().add(pbSubmitting);

        lblSubmitting = new JLabel("Submitting, Please Wait...");
        lblSubmitting.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblSubmitting.setHorizontalAlignment(SwingConstants.CENTER);
        lblSubmitting.setBounds(10, 205, 156, 14);
        lblSubmitting.setVisible(false);
        this.getContentPane().add(lblSubmitting);
    }
}
