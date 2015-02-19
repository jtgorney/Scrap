package ui;

import javax.swing.JFrame;

import java.awt.Window.Type;

import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.JProgressBar;

public class TestSolutionFrame extends JFrame {
    /**
     * Parent frame reference.
     */
    private IDEFrame parent;
    
    /**
     * Create the application.
     */
    public TestSolutionFrame() {
        initialize();
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
    
    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        this.setAlwaysOnTop(true);
        this.setTitle("Test Your Solution");
        this.setAlwaysOnTop(true);
        this.setResizable(false);
        this.setBounds(100, 100, 402, 282);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        JButton btnNewButton = new JButton("Test Solution");
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnNewButton.setBounds(186, 215, 110, 30);
        this.getContentPane().add(btnNewButton);

        JButton btnCancel = new JButton("Cancel");
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

        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        progressBar.setValue(50);
        progressBar.setBounds(10, 219, 156, 23);
        this.getContentPane().add(progressBar);

        JLabel lblTestingPleaseWait = new JLabel("Testing, Please Wait...");
        lblTestingPleaseWait.setHorizontalAlignment(SwingConstants.CENTER);
        lblTestingPleaseWait.setBounds(10, 204, 156, 14);
        this.getContentPane().add(lblTestingPleaseWait);

        // Show JOptionPane
        JOptionPane.showMessageDialog(this, "Your Test Results:\n\nCompile Errors: None\nRuntime Errors: None\n\nOutput: \nHello World!");
    }
}
