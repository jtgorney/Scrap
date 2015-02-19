/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jNetworking.jNetworkInterface.Commands;

import businessobjects.CompilerRunner;
import jNetworking.jNetworkInterface.Command;
import jNetworking.jNetworkInterface.Compiler;
import jNetworking.jNetworkInterface.jNetworkInterface;
import java.io.File;
import java.net.Socket;
import java.util.ArrayList;
import util.SourceCodeFileManager;

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
     * The problem id.
     */
    private int problemId = -1;
    /**
     * cpp or java.
     */
    private String type;
    /**
     * Code to be executed.
     */
    private String code;
    
    @Override
    public void setup(ArrayList<String> input, Socket client) {
        // Values passed by command
        teamId = Integer.parseInt(jNetworkInterface.base64Decode(input.get(0)));
        problemId = Integer.parseInt(jNetworkInterface.base64Decode(input.get(1)));
        type = jNetworkInterface.base64Decode(input.get(2)).trim();
        code = jNetworkInterface.base64Decode(input.get(3));
    }
    
    @Override
    public String run() {
        // Build the file
        File f = SourceCodeFileManager.writeSourceCode(teamId, problemId, type, code);
        // Add the job to the compiler queue
        Compiler comp = Compiler.getCompiler();
        comp.add(new CompilerRunner(teamId, problemId, type, f));
        // Add the run to the queue.
        return "OK";
    }
}