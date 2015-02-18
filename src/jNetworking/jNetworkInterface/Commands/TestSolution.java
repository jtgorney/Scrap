/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jNetworking.jNetworkInterface.Commands;

import jNetworking.jNetworkInterface.Command;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Jacob Gorney
 */
public class TestSolution implements Command {
    /**
     * Team id of submission.
     */
    private int teamId = -1;
    /**
     * Code to be executed.
     */
    private String code;
    
    @Override
    public void setup(ArrayList<String> input, Socket client) {
        // Values passed by command
        teamId = Integer.parseInt(input.get(0));
        code = input.get(1);
    }
    
    @Override
    public String run() {
        // Add the run to the queue.
        return null;
    }
}
