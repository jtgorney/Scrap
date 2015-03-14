/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Jacob Gorney, Max Savard, Matt Mossner, Spencer Kokaly
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package jNetworking.jNetworkInterface.Commands;

import businessobjects.CompilerRunner;
import jNetworking.jNetworkInterface.Command;
import businessobjects.Compiler;
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
        CompilerRunner runner = new CompilerRunner(teamId, problemId, type, f, false);
        comp.add(runner);
        // Add the run to the queue.
        return String.valueOf(runner.getToken());
    }
}