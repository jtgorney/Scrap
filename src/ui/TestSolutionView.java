package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.Window.Type;

import javax.swing.SpringLayout;
import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.JProgressBar;

public class TestSolutionView {

	private JFrame jfrmTestSolution;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		// Set the look and feel of the application.
        try {
            // Set cross-platform Java L&F (also called "Metal")
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception ex) {
            ex.printStackTrace();
            // Do nothing else.
        }
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestSolutionView window = new TestSolutionView();
					window.jfrmTestSolution.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TestSolutionView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		jfrmTestSolution = new JFrame();
		jfrmTestSolution.setAlwaysOnTop(true);
		jfrmTestSolution.setTitle("Test Your Solution");
		jfrmTestSolution.setType(Type.POPUP);
		jfrmTestSolution.setResizable(false);
		jfrmTestSolution.setBounds(100, 100, 402, 282);
		jfrmTestSolution.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfrmTestSolution.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(0, 0, 402, 64);
		jfrmTestSolution.getContentPane().add(panel);
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
		jfrmTestSolution.getContentPane().add(btnNewButton);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(297, 215, 89, 30);
		jfrmTestSolution.getContentPane().add(btnCancel);
		
		JTextArea txtrYouAreAbout = new JTextArea();
		txtrYouAreAbout.setBackground(UIManager.getColor("Panel.background"));
		txtrYouAreAbout.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtrYouAreAbout.setLineWrap(true);
		txtrYouAreAbout.setEditable(false);
		txtrYouAreAbout.setWrapStyleWord(true);
		txtrYouAreAbout.setText("You are about to submit a test run. Test runs do not hurt your score. Your result may not be immediate. Once you submit a test, please wait for the process to complete to continue. When you are ready to begin this test, click the 'Test Solution' button.");
		txtrYouAreAbout.setBounds(10, 75, 376, 85);
		jfrmTestSolution.getContentPane().add(txtrYouAreAbout);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setIndeterminate(true);
		progressBar.setValue(50);
		progressBar.setBounds(10, 219, 156, 23);
		jfrmTestSolution.getContentPane().add(progressBar);
		
		JLabel lblTestingPleaseWait = new JLabel("Testing, Please Wait...");
		lblTestingPleaseWait.setHorizontalAlignment(SwingConstants.CENTER);
		lblTestingPleaseWait.setBounds(10, 204, 156, 14);
		jfrmTestSolution.getContentPane().add(lblTestingPleaseWait);
		
		// Show JOptionPane
		JOptionPane.showMessageDialog(jfrmTestSolution, "Your Test Results:\n\nCompile Errors: None\nRuntime Errors: None\n\nOutput: \nHello World!");
	}
}
