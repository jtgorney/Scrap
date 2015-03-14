//------------------------------------------------------------------------------
// Name: Matthew Mossner
// Course: CS 421
// Semester: Fall 2014
// Instructor: Il-Hyung Cho
// Date Finished: 
// Program Description:
//------------------------------------------------------------------------------
package controllers;

import businessobjects.User;
import java.awt.EventQueue;

/**
 *
 * @author Matthew Mossner
 */
public class ClientClarificationController {
    
    private User user;
    
    public ClientClarificationController(User user) {
        this.user = user;
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                
            }
        });
    }
}
