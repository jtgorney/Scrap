package ui;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.UIManager;

import java.awt.BorderLayout;

import javax.swing.JList;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.border.LineBorder;

import java.awt.Color;

public class AdminView {

	private JFrame frmScrapAdministrative;

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
					AdminView window = new AdminView();
					window.frmScrapAdministrative.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AdminView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmScrapAdministrative = new JFrame();
		frmScrapAdministrative.setTitle("Scrap | Administrative and Server Settings");
		frmScrapAdministrative.setBounds(100, 100, 601, 330);
		frmScrapAdministrative.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmScrapAdministrative.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenu mnServerControls = new JMenu("Server Controls");
		menuBar.add(mnServerControls);
		frmScrapAdministrative.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmScrapAdministrative.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Server Settings", null, panel, null);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Users", null, panel_1, null);
		SpringLayout sl_panel_1 = new SpringLayout();
		panel_1.setLayout(sl_panel_1);
		
		DefaultListModel<String> listModel = new DefaultListModel();
		JList list = new JList(listModel);
		listModel.addElement("Pickles | Contestant Number: 1");
		listModel.addElement("Oranges | Contestant Number: 2");
		listModel.addElement("Cho | Contestant Number: 3");
		listModel.addElement("Bidgoli | Contestant Number: 4");
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		sl_panel_1.putConstraint(SpringLayout.NORTH, list, 10, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, list, 10, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, list, 181, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, list, 570, SpringLayout.WEST, panel_1);
		panel_1.add(list);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.setIcon(new ImageIcon(AdminView.class.getResource("/com/alee/extended/filechooser/icons/edit.png")));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		sl_panel_1.putConstraint(SpringLayout.NORTH, btnNewButton, 6, SpringLayout.SOUTH, list);
		sl_panel_1.putConstraint(SpringLayout.WEST, btnNewButton, 0, SpringLayout.WEST, list);
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Delete");
		btnNewButton_1.setIcon(new ImageIcon(AdminView.class.getResource("/com/alee/extended/filechooser/icons/cancel.png")));
		sl_panel_1.putConstraint(SpringLayout.NORTH, btnNewButton_1, 6, SpringLayout.SOUTH, list);
		sl_panel_1.putConstraint(SpringLayout.WEST, btnNewButton_1, 6, SpringLayout.EAST, btnNewButton);
		panel_1.add(btnNewButton_1);
		
		JButton btnExit = new JButton("Exit");
		sl_panel_1.putConstraint(SpringLayout.SOUTH, btnExit, -10, SpringLayout.SOUTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, btnExit, -10, SpringLayout.EAST, panel_1);
		panel_1.add(btnExit);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Database Settings", null, panel_2, null);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Problem Set", null, panel_3, null);
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Competition Settings", null, panel_4, null);
	}
}
