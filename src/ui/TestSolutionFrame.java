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

public class TestSolutionFrame extends JFrame {
    /**
     * Test solution button.
     */
    public JButton btnTest;
    /**
     * Cancel button.
     */
    public JButton btnCancel;
    /**
     * Testing label.
     */
    public JLabel jlblTesting;
    /**
     * Loading bar.
     */
    public JProgressBar jpbLoading;
    /**
     * Create the application.
     */
    public TestSolutionFrame() {
        initialize();
    }
    
    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        this.setAlwaysOnTop(true);
        this.setTitle("Test Your Solution");
        this.setAlwaysOnTop(true);
        this.setResizable(false);
        this.setBounds(100, 100, 402, 282);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.getContentPane().setLayout(null);
        this.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setBounds(0, 0, 402, 64);
        this.getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("Test Solution");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setBounds(0, 11, 392, 24);
        panel.add(lblNewLabel);

        JLabel lblProblemNumber = new JLabel("Problem Number: 1");
        lblProblemNumber.setHorizontalAlignment(SwingConstants.CENTER);
        lblProblemNumber.setForeground(Color.WHITE);
        lblProblemNumber.setFont(new Font("Tahoma", Font.BOLD, 10));
        lblProblemNumber.setBounds(0, 35, 392, 24);
        panel.add(lblProblemNumber);

        btnTest = new JButton("Test Solution");
        btnTest.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnTest.setBounds(186, 215, 110, 30);
        this.getContentPane().add(btnTest);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(297, 215, 89, 30);
        this.getContentPane().add(btnCancel);

        JTextArea txtrYouAreAbout = new JTextArea();
        txtrYouAreAbout.setBackground(UIManager.getColor("Panel.background"));
        txtrYouAreAbout.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtrYouAreAbout.setLineWrap(true);
        txtrYouAreAbout.setEditable(false);
        txtrYouAreAbout.setWrapStyleWord(true);
        txtrYouAreAbout.setText("You are about to submit a test run. Test runs do not hurt your score. Your result may not be immediate. Once you submit a test, please wait for the process to complete to continue. When you are ready to begin this test, click the 'Test Solution' button.");
        txtrYouAreAbout.setBounds(10, 75, 376, 85);
        this.getContentPane().add(txtrYouAreAbout);

        jpbLoading = new JProgressBar();
        jpbLoading.setIndeterminate(true);
        jpbLoading.setValue(50);
        jpbLoading.setBounds(10, 219, 156, 23);
        this.getContentPane().add(jpbLoading);

        jlblTesting = new JLabel("Testing, Please Wait...");
        jlblTesting.setHorizontalAlignment(SwingConstants.CENTER);
        jlblTesting.setBounds(10, 204, 156, 14);
        this.getContentPane().add(jlblTesting);
        
        jpbLoading.setVisible(false);
        jlblTesting.setVisible(false);
    }
}
