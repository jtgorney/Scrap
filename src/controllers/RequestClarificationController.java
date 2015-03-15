//------------------------------------------------------------------------------
// Name: Matthew Mossner
// Course: CS 421
// Semester: Fall 2014
// Instructor: Il-Hyung Cho
// Date Finished: 
// Program Description:
//------------------------------------------------------------------------------
package controllers;

import businessobjects.Clarification;
import businessobjects.Problem;
import businessobjects.SettingsCommunicator;
import businessobjects.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jNetworking.jNetworkInterface.jNetworkInterface;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JLabel;
import ui.RequestClarificationDialog;

/**
 *
 * @author Matthew Mossner
 */
public class RequestClarificationController implements ActionListener {
    
    private Frame dialogOwner;
    private User user;
    private RequestClarificationDialog requestClarificationView;
    private int[] problemNumbers;
    
    public RequestClarificationController(Frame dialogOwner, User user) {
        this.user = user;
        jNetworkInterface client = new jNetworkInterface(
            SettingsCommunicator.getServerAddr(),
            SettingsCommunicator.getServerPort(), false);
        String response = client.sendCommand("getproblemset", null);
        ArrayList<Problem> problems = new Gson().fromJson(response, new TypeToken<ArrayList<Problem>>() {}.getType());
        problemNumbers = new int[problems.size()];
        for (int i = 0; i < problems.size(); i++) {
            problemNumbers[i] = problems.get(i).getProblemNumber();
        }
    }
    
    public Clarification requestClarification() {
        requestClarificationView = new RequestClarificationDialog(dialogOwner, problemNumbers);
        requestClarificationView.jbtnCancel.addActionListener(this);
        requestClarificationView.jbtnSend.addActionListener(this);
        requestClarificationView.setLocationRelativeTo(null);
        requestClarificationView.setTitle("Request Clarification");
        requestClarificationView.setVisible(true);
        if (requestClarificationView != null) {
            JDialog waitDialog = new JDialog(requestClarificationView, false);
            waitDialog.setUndecorated(true);
            waitDialog.add(new JLabel("Processing..."));
            waitDialog.pack();
            waitDialog.setVisible(true);
            jNetworkInterface client = new jNetworkInterface(
            SettingsCommunicator.getServerAddr(),
            SettingsCommunicator.getServerPort(), false);
            ArrayList<String> commandData = new ArrayList<String>();
            try {
                commandData.add(jNetworkInterface.base64Encode(String.valueOf(user.getId())));
                commandData.add(jNetworkInterface.base64Encode(String.valueOf(requestClarificationView.getProblemId())));
                commandData.add(jNetworkInterface.base64Encode(requestClarificationView.getQuestion()));
                String response = client.sendCommand("requestclarification", commandData);
                waitDialog.setVisible(false);
                return new Gson().fromJson(response, Clarification.class);
            }
            catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == requestClarificationView.jbtnCancel)
            jbtnCancelClick();
        else if (e.getSource() == requestClarificationView.jbtnSend)
            jbtnSendClick();
    }
    
    private void jbtnCancelClick() {
        requestClarificationView.setVisible(false);
        requestClarificationView = null;
    }
    
    private void jbtnSendClick() {
        requestClarificationView.setVisible(false);
    }
}
