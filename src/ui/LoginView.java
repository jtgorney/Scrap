package ui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

/**
 * GUI code for the login screen.
 * @author Jacob Gorney
 *
 */
public class LoginView {

	public JFrame jfrmLogin;
	public JTextField jtfUsername;
	public JPasswordField jtfPassword;
	public JLabel lblPassword;
	public JLabel label;
	public JLabel jlblPassword;
	public JPanel panel;
	public JLabel jlblHeader;
	public JLabel jlblSubHeader;
	public JButton jbtnEnter;
	public JButton jbtnExit;

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
					LoginView window = new LoginView();
					window.jfrmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		jfrmLogin = new JFrame();
		jfrmLogin.setTitle("Scrap Competition System");
		jfrmLogin.setResizable(false);
		jfrmLogin.setBounds(100, 100, 373, 330);
		jfrmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfrmLogin.getContentPane().setLayout(null);
		
		jtfUsername = new JTextField();
		jtfUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		jtfUsername.setBounds(10, 135, 347, 35);
		jfrmLogin.getContentPane().add(jtfUsername);
		jtfUsername.setColumns(10);
		
		jbtnEnter = new JButton("Enter Competition");
		jbtnEnter.setIcon(new ImageIcon(LoginView.class.getResource("/com/alee/extended/style/icons/editor/disabled.png")));
		jbtnEnter.setBounds(133, 260, 149, 30);
		jfrmLogin.getContentPane().add(jbtnEnter);
		
		jtfPassword = new JPasswordField();
		jtfPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		jtfPassword.setColumns(10);
		jtfPassword.setBounds(10, 201, 347, 35);
		jfrmLogin.getContentPane().add(jtfPassword);
		
		JLabel jlblUsername = new JLabel("Username/Team Number:");
		jlblUsername.setFont(new Font("Tahoma", Font.PLAIN, 12));
		jlblUsername.setBounds(10, 116, 149, 14);
		jfrmLogin.getContentPane().add(jlblUsername);
		
		jlblPassword = new JLabel("Password:");
		jlblPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		jlblPassword.setBounds(10, 181, 149, 14);
		jfrmLogin.getContentPane().add(jlblPassword);
		
		panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(0, 0, 367, 94);
		jfrmLogin.getContentPane().add(panel);
		panel.setLayout(null);
		
		jlblHeader = new JLabel("Scrap Competition System");
		jlblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		jlblHeader.setFont(new Font("Tahoma", Font.BOLD, 18));
		jlblHeader.setForeground(Color.WHITE);
		jlblHeader.setBounds(10, 25, 347, 30);
		panel.add(jlblHeader);
		
		jlblSubHeader = new JLabel("SCHOOL");
		jlblSubHeader.setHorizontalAlignment(SwingConstants.CENTER);
		jlblSubHeader.setForeground(Color.WHITE);
		jlblSubHeader.setFont(new Font("Tahoma", Font.PLAIN, 11));
		jlblSubHeader.setBounds(10, 55, 347, 17);
		panel.add(jlblSubHeader);
		
		jbtnExit = new JButton("Exit");
		jbtnExit.setBounds(283, 260, 74, 30);
		jfrmLogin.getContentPane().add(jbtnExit);
		
		// Center Frame
		jfrmLogin.setLocationRelativeTo(null);
	}
	
	/**
	 * Get the JFrame object.
	 * @return JFrame
	 */
	public JFrame getFrame() {
		return jfrmLogin;
	}
	
	/**
	 * Set the heading text.
	 * @param header Text
	 */
	public void setHeader(String header) {
		jlblHeader.setText(header);
		jfrmLogin.repaint();
	}
	
	/**
	 * Set the subheader text.
	 * @param sHeader Text
	 */
	public void setSubHeader(String sHeader) {
		jlblSubHeader.setText(sHeader);
		jfrmLogin.repaint();
	}
}
