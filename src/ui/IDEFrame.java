package ui;

import javax.swing.JFrame;

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JMenu;

import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;

import java.awt.Font;

import java.awt.LayoutManager;


public class IDEFrame extends JFrame {
    /**
     * Test solution button.
     */
    public JButton jbtnTestSolution;
    /**
     * Submit Solution button.
     */
    public JButton jbtnSubmitSolution;
    /**
     * IDE code editor.
     */
    public RSyntaxTextArea rstaCode;
    /**
    * Problem set tabs.
    */
    public JTabbedPane tpProblemSet;
    /**
     * Compiler combo box.
     */
    public JComboBox<String> jcmbLanguage;
    /**
     * Exit System option.
     */
    public JMenuItem mntmExitSystem;
    /**
     * Logout option.
     */
    public JMenuItem mntmLogout;
    /**
     * About Scrap option
     */
    public JMenuItem mntmAboutScrap;
    /**
     * Calculator option
     */
    public JMenuItem mntmCalculator;
    /**
     * Calculator option
     */
    public JMenuItem mntmClarification;
    /**
     * Jlabel for school and team information
     */
    public JLabel jlblTopContent;
    /**
     * Create the application.
     */
    public IDEFrame() {
        initialize();
    }
    
    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        
        this.setTitle("Scrap Competition System | Version 1.0.0");
        this.setBounds(100, 100, 1053, 761);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setMargin(new Insets(2, 2, 2, 2));
        this.setJMenuBar(menuBar);

        JMenu mnFile = new JMenu("File");
        menuBar.add(mnFile);

        menuBar.add(Box.createHorizontalStrut(5));

        mntmLogout = new JMenuItem("Logout");
        mnFile.add(mntmLogout);

        mntmExitSystem = new JMenuItem("Exit System");
        mnFile.add(mntmExitSystem);

        JMenu mnCompetitionTools = new JMenu("Competition Tools");
        menuBar.add(mnCompetitionTools);
        
        mntmClarification = new JMenuItem("Request Clarification");
        mnCompetitionTools.add(mntmClarification);
        
        menuBar.add(Box.createHorizontalStrut(5));
        
        mntmCalculator = new JMenuItem("Calculator");
        mnCompetitionTools.add(mntmCalculator);

        JMenu mnHelp = new JMenu("Help");
        menuBar.add(mnHelp);

        mntmAboutScrap = new JMenuItem("About Scrap");
        mnHelp.add(mntmAboutScrap);
        SpringLayout springLayout = new SpringLayout();
        this.getContentPane().setLayout(springLayout);

        tpProblemSet = new JTabbedPane(JTabbedPane.TOP);
        springLayout.putConstraint(SpringLayout.EAST, tpProblemSet, -10, SpringLayout.EAST, this.getContentPane());
        this.getContentPane().add(tpProblemSet);
        
        JPanel jpnlEditor = new JPanel();
        springLayout.putConstraint(SpringLayout.WEST, tpProblemSet, 6, SpringLayout.EAST, jpnlEditor);
        springLayout.putConstraint(SpringLayout.EAST, jpnlEditor, -337, SpringLayout.EAST, this.getContentPane());
        springLayout.putConstraint(SpringLayout.WEST, jpnlEditor, 10, SpringLayout.WEST, this.getContentPane());
        jpnlEditor.setBackground(Color.WHITE);
        this.getContentPane().add(jpnlEditor);
        jpnlEditor.setLayout(new BorderLayout(0, 0));

        // Add the editor
        rstaCode = new RSyntaxTextArea(20, 60);
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
        springLayout.putConstraint(SpringLayout.EAST, jpnlCodeControls, -337, SpringLayout.EAST, this.getContentPane());
        springLayout.putConstraint(SpringLayout.WEST, jpnlCodeControls, 10, SpringLayout.WEST, this.getContentPane());
        this.getContentPane().add(jpnlCodeControls);
        jpnlCodeControls.setLayout(new GridLayout(0, 3, 5, 5));

        jcmbLanguage = new JComboBox<>();
        jcmbLanguage.addItem("C++ 14");
        jcmbLanguage.addItem("Java SE 8.0");
        jpnlCodeControls.add(jcmbLanguage);

        jbtnTestSolution = new JButton("Test Solution");
        jbtnTestSolution.setIcon(new ImageIcon(IDEFrame.class.getResource("/com/alee/extended/filechooser/icons/forward.png")));
        jpnlCodeControls.add(jbtnTestSolution);

        jbtnSubmitSolution = new JButton("Submit Solution");
        jbtnSubmitSolution.setIcon(new ImageIcon(IDEFrame.class.getResource("/com/alee/extended/filechooser/icons/ok.png")));
        jpnlCodeControls.add(jbtnSubmitSolution);

        JPanel jpnlTopBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        springLayout.putConstraint(SpringLayout.NORTH, jpnlEditor, 6, SpringLayout.SOUTH, jpnlTopBar);
        springLayout.putConstraint(SpringLayout.NORTH, tpProblemSet, 6, SpringLayout.SOUTH, jpnlTopBar);
        springLayout.putConstraint(SpringLayout.NORTH, jpnlTopBar, 0, SpringLayout.NORTH, this.getContentPane());
        springLayout.putConstraint(SpringLayout.WEST, jpnlTopBar, 0, SpringLayout.WEST, this.getContentPane());
        springLayout.putConstraint(SpringLayout.SOUTH, jpnlTopBar, 24, SpringLayout.NORTH, this.getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, jpnlTopBar, 0, SpringLayout.EAST, this.getContentPane());
        jpnlTopBar.setBackground(new java.awt.Color(0, 153, 51));
        this.getContentPane().add(jpnlTopBar);
        
        jlblTopContent = new JLabel("");
        jlblTopContent.setFont(new Font("Tahoma", Font.BOLD, 11));
        jlblTopContent.setForeground(Color.WHITE);
        jpnlTopBar.add(jlblTopContent);

        JPanel jpnlBottomBar = new JPanel((LayoutManager) null);
        springLayout.putConstraint(SpringLayout.SOUTH, tpProblemSet, -6, SpringLayout.NORTH, jpnlBottomBar);
        springLayout.putConstraint(SpringLayout.NORTH, jpnlCodeControls, -39, SpringLayout.NORTH, jpnlBottomBar);
        springLayout.putConstraint(SpringLayout.WEST, jpnlBottomBar, 0, SpringLayout.WEST, this.getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, jpnlBottomBar, 0, SpringLayout.EAST, this.getContentPane());
        springLayout.putConstraint(SpringLayout.SOUTH, jpnlCodeControls, -6, SpringLayout.NORTH, jpnlBottomBar);
        springLayout.putConstraint(SpringLayout.NORTH, jpnlBottomBar, -24, SpringLayout.SOUTH, this.getContentPane());
        springLayout.putConstraint(SpringLayout.SOUTH, jpnlBottomBar, 0, SpringLayout.SOUTH, this.getContentPane());
        jpnlBottomBar.setBackground(new java.awt.Color(0, 153, 51));
        this.getContentPane().add(jpnlBottomBar);
        FlowLayout fl_jpnlBottomBar = new FlowLayout(FlowLayout.LEFT);
        fl_jpnlBottomBar.setVgap(4);
        jpnlBottomBar.setLayout(fl_jpnlBottomBar);

        JLabel jlblStatusBar = new JLabel("Connection Status: CONNECTED");
        jlblStatusBar.setIcon(new ImageIcon(IDEFrame.class.getResource("/com/alee/extended/filechooser/icons/ok.png")));
        jlblStatusBar.setForeground(Color.WHITE);
        jlblStatusBar.setFont(new Font("Tahoma", Font.BOLD, 10));
        jpnlBottomBar.add(jlblStatusBar);

        // Center
        this.setLocationRelativeTo(null);
    }
}
