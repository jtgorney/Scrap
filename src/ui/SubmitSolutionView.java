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

public class SubmitSolutionView {

	private JFrame jfrmSubmitSolution;

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
					SubmitSolutionView window = new SubmitSolutionView();
					window.jfrmSubmitSolution.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SubmitSolutionView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		jfrmSubmitSolution = new JFrame();
		jfrmSubmitSolution.setAlwaysOnTop(true);
		jfrmSubmitSolution.setTitle("Submit Your Solution");
		jfrmSubmitSolution.setType(Type.POPUP);
		jfrmSubmitSolution.setResizable(false);
		jfrmSubmitSolution.setBounds(100, 100, 402, 282);
		jfrmSubmitSolution.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfrmSubmitSolution.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 0, 0));
		panel.setBounds(0, 0, 402, 64);
		jfrmSubmitSolution.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Submit Solution");
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
		
		JButton btnNewButton = new JButton("Submit Solution");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(176, 215, 129, 30);
		jfrmSubmitSolution.getContentPane().add(btnNewButton);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(307, 215, 79, 30);
		jfrmSubmitSolution.getContentPane().add(btnCancel);
		
		JTextArea txtrYouAreAbout = new JTextArea();
		txtrYouAreAbout.setBackground(UIManager.getColor("Panel.background"));
		txtrYouAreAbout.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtrYouAreAbout.setLineWrap(true);
		txtrYouAreAbout.setEditable(false);
		txtrYouAreAbout.setWrapStyleWord(true);
		txtrYouAreAbout.setText("You are about to submit your final solution to the server. Failed runs WILL count against your overall score. Results might not be immediate. Click the 'Submit Solution' button to continue.");
		txtrYouAreAbout.setBounds(10, 75, 376, 85);
		jfrmSubmitSolution.getContentPane().add(txtrYouAreAbout);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setIndeterminate(true);
		progressBar.setValue(50);
		progressBar.setBounds(10, 219, 156, 23);
		jfrmSubmitSolution.getContentPane().add(progressBar);
		
		JLabel lblTestingPleaseWait = new JLabel("Submitting, Please Wait...");
		lblTestingPleaseWait.setHorizontalAlignment(SwingConstants.CENTER);
		lblTestingPleaseWait.setBounds(10, 205, 156, 14);
		jfrmSubmitSolution.getContentPane().add(lblTestingPleaseWait);
		
		// Show JOptionPane
		JOptionPane.showMessageDialog(jfrmSubmitSolution, "Your Submission Results:\n\nCompile Errors: None\nRuntime Errors: None\n\nOutput: \nHello World!\n\nSolution Accepted: YES");
	}
}
