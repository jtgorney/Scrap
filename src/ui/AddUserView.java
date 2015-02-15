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
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import java.awt.SystemColor;

public class AddUserView {

	private JFrame jfrmTestSolution;
	private JTextField textField;
	private JTextField textField_1;

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
					AddUserView window = new AddUserView();
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
	public AddUserView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		jfrmTestSolution = new JFrame();
		jfrmTestSolution.setAlwaysOnTop(true);
		jfrmTestSolution.setTitle("Add New User");
		jfrmTestSolution.setType(Type.POPUP);
		jfrmTestSolution.setResizable(false);
		jfrmTestSolution.setBounds(100, 100, 402, 285);
		jfrmTestSolution.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfrmTestSolution.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(0, 0, 402, 64);
		jfrmTestSolution.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Scrap User Management");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(0, 11, 392, 24);
		panel.add(lblNewLabel);
		
		JLabel lblProblemNumber = new JLabel("Add New User");
		lblProblemNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblProblemNumber.setForeground(Color.WHITE);
		lblProblemNumber.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblProblemNumber.setBounds(0, 35, 392, 24);
		panel.add(lblProblemNumber);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(186, 219, 110, 30);
		jfrmTestSolution.getContentPane().add(btnNewButton);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(297, 219, 89, 30);
		jfrmTestSolution.getContentPane().add(btnCancel);
		
		textField = new JTextField();
		textField.setBounds(78, 75, 308, 24);
		jfrmTestSolution.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsername.setBounds(10, 80, 58, 14);
		jfrmTestSolution.getContentPane().add(lblUsername);
		
		textField_1 = new JPasswordField();
		textField_1.setColumns(10);
		textField_1.setBounds(78, 105, 308, 24);
		jfrmTestSolution.getContentPane().add(textField_1);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setBounds(10, 110, 58, 14);
		jfrmTestSolution.getContentPane().add(lblPassword);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(78, 138, 308, 24);
		jfrmTestSolution.getContentPane().add(comboBox);
		
		JLabel lblTeam = new JLabel("Team:");
		lblTeam.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTeam.setBounds(10, 143, 58, 14);
		jfrmTestSolution.getContentPane().add(lblTeam);
		
		JTextArea txtrOnceAContestant = new JTextArea();
		txtrOnceAContestant.setWrapStyleWord(true);
		txtrOnceAContestant.setText("Once a contestant is created in the system a contestant ID will be automatically assigned.");
		txtrOnceAContestant.setLineWrap(true);
		txtrOnceAContestant.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtrOnceAContestant.setEditable(false);
		txtrOnceAContestant.setBackground(SystemColor.menu);
		txtrOnceAContestant.setBounds(10, 171, 376, 43);
		jfrmTestSolution.getContentPane().add(txtrOnceAContestant);
		
		// Show JOptionPane
		JOptionPane.showMessageDialog(jfrmTestSolution, "The user was successfully added!");
	}
}
