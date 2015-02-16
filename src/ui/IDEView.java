package ui;

import javax.swing.JFrame;

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.SpringLayout;
import javax.swing.UIManager;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import com.alee.laf.WebLookAndFeel;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JMenu;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;

import java.awt.Font;

import javax.swing.JTextArea;

import java.awt.LayoutManager;

public class IDEView {

    private JFrame jfrmIDEView;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        // Set the look and feel of the application.
        try {
            // Set cross-platform Java L&F (also called "Metal")
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
            // Do nothing else.
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    IDEView window = new IDEView();
                    window.jfrmIDEView.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public IDEView() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

        jfrmIDEView = new JFrame();
        jfrmIDEView.setTitle("Scrap Competition System | Version 1.0.0");
        jfrmIDEView.setBounds(100, 100, 1053, 761);
        jfrmIDEView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrmIDEView.setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setMargin(new Insets(2, 2, 2, 2));
        jfrmIDEView.setJMenuBar(menuBar);

        JMenu mnFile = new JMenu("File");
        menuBar.add(mnFile);

        menuBar.add(Box.createHorizontalStrut(5));

        JMenuItem mntmLogout = new JMenuItem("Logout");
        mnFile.add(mntmLogout);

        JMenuItem mntmExitSystem = new JMenuItem("Exit System");
        mnFile.add(mntmExitSystem);

        JMenu mnCompetitionTools = new JMenu("Competition Tools");
        menuBar.add(mnCompetitionTools);

        menuBar.add(Box.createHorizontalStrut(5));

        JMenu mnCode = new JMenu("Code");
        menuBar.add(mnCode);

        menuBar.add(Box.createHorizontalStrut(5));

        JMenu mnHelp = new JMenu("Help");
        menuBar.add(mnHelp);

        JMenuItem mntmAboutScrap = new JMenuItem("About Scrap");
        mnHelp.add(mntmAboutScrap);
        SpringLayout springLayout = new SpringLayout();
        jfrmIDEView.getContentPane().setLayout(springLayout);

        JTabbedPane tpProblemSet = new JTabbedPane(JTabbedPane.TOP);
        springLayout.putConstraint(SpringLayout.EAST, tpProblemSet, -10, SpringLayout.EAST, jfrmIDEView.getContentPane());
        jfrmIDEView.getContentPane().add(tpProblemSet);

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        tpProblemSet.addTab("Problem 1", new ImageIcon(IDEView.class.getResource("/com/alee/extended/filechooser/icons/ok.png")), panel, null);
        panel.setLayout(new BorderLayout(0, 0));

        JTextArea jtxtProblemText = new JTextArea();
        jtxtProblemText.setEditable(false);
        jtxtProblemText.setText("Create a program that outputs the text:\r\n\r\nHello World!\r\n\r\nYour program should run in O(n) time.");
        jtxtProblemText.setFont(new Font("Tahoma", Font.PLAIN, 12));
        jtxtProblemText.setLineWrap(true);
        jtxtProblemText.setMargin(new Insets(10, 10, 10, 10));
        panel.add(jtxtProblemText, BorderLayout.CENTER);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(Color.WHITE);
        tpProblemSet.addTab("Problem 2", new ImageIcon(IDEView.class.getResource("/com/alee/extended/filechooser/icons/ok.png")), panel_1, null);
        panel_1.setLayout(new BorderLayout(0, 0));

        JTextArea txtrCreateAProgram = new JTextArea();
        txtrCreateAProgram.setText("Create a program that outputs the first 7 numbers of the fibonnacci sequence.\r\n\r\nYour method/function should use recursion to output the numbers. No floating points.");
        txtrCreateAProgram.setMargin(new Insets(10, 10, 10, 10));
        txtrCreateAProgram.setLineWrap(true);
        txtrCreateAProgram.setFont(new Font("Tahoma", Font.PLAIN, 12));
        txtrCreateAProgram.setEditable(false);
        panel_1.add(txtrCreateAProgram, BorderLayout.CENTER);

        JPanel panel_2 = new JPanel();
        panel_2.setBackground(Color.WHITE);
        tpProblemSet.addTab("Problem 3", null, panel_2, null);
        panel_2.setLayout(new BorderLayout(0, 0));

        JTextArea textArea_2 = new JTextArea();
        textArea_2.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus quis pretium arcu, ut ornare turpis. Vestibulum id tellus in purus venenatis molestie blandit ut nibh. Phasellus purus mi, imperdiet sed ex convallis, eleifend sollicitudin libero. Aliquam est tortor, mattis non sagittis id, volutpat vitae elit. Nam ac velit sit amet tortor sodales gravida.");
        textArea_2.setMargin(new Insets(10, 10, 10, 10));
        textArea_2.setLineWrap(true);
        textArea_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
        textArea_2.setEditable(false);
        panel_2.add(textArea_2, BorderLayout.CENTER);

        JPanel panel_3 = new JPanel();
        panel_3.setBackground(Color.WHITE);
        tpProblemSet.addTab("Problem 4", null, panel_3, null);
        panel_3.setLayout(new BorderLayout(0, 0));

        JTextArea textArea_3 = new JTextArea();
        textArea_3.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus quis pretium arcu, ut ornare turpis. Vestibulum id tellus in purus venenatis molestie blandit ut nibh. Phasellus purus mi, imperdiet sed ex convallis, eleifend sollicitudin libero. Aliquam est tortor, mattis non sagittis id, volutpat vitae elit. Nam ac velit sit amet tortor sodales gravida.");
        textArea_3.setMargin(new Insets(10, 10, 10, 10));
        textArea_3.setLineWrap(true);
        textArea_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
        textArea_3.setEditable(false);
        panel_3.add(textArea_3, BorderLayout.CENTER);

        JPanel jpnlEditor = new JPanel();
        springLayout.putConstraint(SpringLayout.WEST, tpProblemSet, 6, SpringLayout.EAST, jpnlEditor);
        springLayout.putConstraint(SpringLayout.EAST, jpnlEditor, -337, SpringLayout.EAST, jfrmIDEView.getContentPane());
        springLayout.putConstraint(SpringLayout.WEST, jpnlEditor, 10, SpringLayout.WEST, jfrmIDEView.getContentPane());
        jpnlEditor.setBackground(Color.WHITE);
        jfrmIDEView.getContentPane().add(jpnlEditor);
        jpnlEditor.setLayout(new BorderLayout(0, 0));

        // Add the editor
        RSyntaxTextArea rstaCode = new RSyntaxTextArea(20, 60);
        rstaCode.setCurrentLineHighlightColor(Color.WHITE);
        rstaCode.setTabSize(3);
        rstaCode.setColumns(30);
        rstaCode.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        rstaCode.setAntiAliasingEnabled(true);
        RTextScrollPane spEditor = new RTextScrollPane(rstaCode);
        spEditor.setFoldIndicatorEnabled(true);
        jpnlEditor.add(spEditor);

        JPanel jpnlCodeControls = new JPanel();
        springLayout.putConstraint(SpringLayout.SOUTH, jpnlEditor, -6, SpringLayout.NORTH, jpnlCodeControls);
        springLayout.putConstraint(SpringLayout.EAST, jpnlCodeControls, -337, SpringLayout.EAST, jfrmIDEView.getContentPane());
        springLayout.putConstraint(SpringLayout.WEST, jpnlCodeControls, 10, SpringLayout.WEST, jfrmIDEView.getContentPane());
        jfrmIDEView.getContentPane().add(jpnlCodeControls);
        jpnlCodeControls.setLayout(new GridLayout(0, 3, 5, 5));

        JComboBox<String> jcmbLanguage = new JComboBox<>();
        jcmbLanguage.addItem("Java SE 8.0");
        jcmbLanguage.addItem("C++ 14");
        jpnlCodeControls.add(jcmbLanguage);

        JButton jbtnTestSolution = new JButton("Test Solution");
        jbtnTestSolution.setIcon(new ImageIcon(IDEView.class.getResource("/com/alee/extended/filechooser/icons/forward.png")));
        jpnlCodeControls.add(jbtnTestSolution);

        JButton jbtnSubmitSolution = new JButton("Submit Solution");
        jbtnSubmitSolution.setIcon(new ImageIcon(IDEView.class.getResource("/com/alee/extended/filechooser/icons/ok.png")));
        jpnlCodeControls.add(jbtnSubmitSolution);

        JPanel jpnlTopBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        springLayout.putConstraint(SpringLayout.NORTH, jpnlEditor, 6, SpringLayout.SOUTH, jpnlTopBar);
        springLayout.putConstraint(SpringLayout.NORTH, tpProblemSet, 6, SpringLayout.SOUTH, jpnlTopBar);
        springLayout.putConstraint(SpringLayout.NORTH, jpnlTopBar, 0, SpringLayout.NORTH, jfrmIDEView.getContentPane());
        springLayout.putConstraint(SpringLayout.WEST, jpnlTopBar, 0, SpringLayout.WEST, jfrmIDEView.getContentPane());
        springLayout.putConstraint(SpringLayout.SOUTH, jpnlTopBar, 24, SpringLayout.NORTH, jfrmIDEView.getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, jpnlTopBar, 0, SpringLayout.EAST, jfrmIDEView.getContentPane());
        jpnlTopBar.setBackground(Color.GREEN);
        jfrmIDEView.getContentPane().add(jpnlTopBar);

        JLabel jlblTopContent = new JLabel("Saginaw Valley State University | Test Contest | Time Remaining: 03:00:00 | Team: Pickles");
        jlblTopContent.setFont(new Font("Tahoma", Font.BOLD, 11));
        jlblTopContent.setForeground(Color.WHITE);
        jpnlTopBar.add(jlblTopContent);

        JPanel jpnlBottomBar = new JPanel((LayoutManager) null);
        springLayout.putConstraint(SpringLayout.SOUTH, tpProblemSet, -6, SpringLayout.NORTH, jpnlBottomBar);
        springLayout.putConstraint(SpringLayout.NORTH, jpnlCodeControls, -39, SpringLayout.NORTH, jpnlBottomBar);
        springLayout.putConstraint(SpringLayout.WEST, jpnlBottomBar, 0, SpringLayout.WEST, jfrmIDEView.getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, jpnlBottomBar, 0, SpringLayout.EAST, jfrmIDEView.getContentPane());
        springLayout.putConstraint(SpringLayout.SOUTH, jpnlCodeControls, -6, SpringLayout.NORTH, jpnlBottomBar);
        springLayout.putConstraint(SpringLayout.NORTH, jpnlBottomBar, -24, SpringLayout.SOUTH, jfrmIDEView.getContentPane());
        springLayout.putConstraint(SpringLayout.SOUTH, jpnlBottomBar, 0, SpringLayout.SOUTH, jfrmIDEView.getContentPane());
        jpnlBottomBar.setBackground(Color.GREEN);
        jfrmIDEView.getContentPane().add(jpnlBottomBar);
        FlowLayout fl_jpnlBottomBar = new FlowLayout(FlowLayout.LEFT);
        fl_jpnlBottomBar.setVgap(4);
        jpnlBottomBar.setLayout(fl_jpnlBottomBar);

        JLabel jlblStatusBar = new JLabel("Connection Status: CONNECTED");
        jlblStatusBar.setIcon(new ImageIcon(IDEView.class.getResource("/com/alee/extended/filechooser/icons/ok.png")));
        jlblStatusBar.setForeground(Color.WHITE);
        jlblStatusBar.setFont(new Font("Tahoma", Font.BOLD, 10));
        jpnlBottomBar.add(jlblStatusBar);

        // Center
        jfrmIDEView.setLocationRelativeTo(null);
    }

    public JFrame getFrame() {
        return jfrmIDEView;
    }
}
