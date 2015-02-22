package main;

import controllers.LoginController;
import javax.swing.UIManager;
import ui.LoginFrame;

/**
 * Main class for Scrap Client execution.
 *
 * @author Jacob Gorney
 *
 */
public class Main {

    /**
     * Main method for program.
     *
     * @param args Command line args
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
        // Run the GUI
        LoginController controller = new LoginController(new LoginFrame());
    }
}
