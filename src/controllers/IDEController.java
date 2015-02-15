package controllers;

import java.awt.EventQueue;
import businessobjects.User;
import ui.IDEView;

/**
 * IDEView Controller for programming interaction.
 *
 * @author Jacob Gorney
 *
 */
public class IDEController {

    /**
     * The GUI reference to IDEView.
     */
    private IDEView ideView;

    /**
     * Constructor for IDEView GUI.
     *
     * @param ideView IDEView class
     * @param user User object
     */
    public IDEController(final IDEView ideView, User user) {
        // Nothing for now. Just show GUI
        this.ideView = ideView;
        // For now, just display the GUI until we build the IDEView controller
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ideView.getFrame().setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
