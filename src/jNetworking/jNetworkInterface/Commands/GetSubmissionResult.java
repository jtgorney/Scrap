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
import compiler.OutputParser;
import jNetworking.jNetworkInterface.Command;
import businessobjects.Compiler;
import jNetworking.jNetworkInterface.jNetworkInterface;
import java.io.File;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Get the result of a compiler job.
 * @author Jacob Gorney
 */
public class GetSubmissionResult implements Command {
    /**
     * Token of job.
     */
    private long token;
    /**
     * Team ID associated with job.
     */
    private int teamId;
    
    @Override
    public void setup(ArrayList<String> input, Socket client) {
        // Input the team id and the run token
        teamId = Integer.parseInt(input.get(0));
        token = Long.parseLong(input.get(1));
    }

    @Override
    public String run() {
        Compiler c = Compiler.getCompiler();
        CompilerRunner runner = c.searchCompletedRunners(token, teamId);
        // Return results
        if (runner == null)
            return "PROCESSING";
        else
            if (runner.isAccepted())
                return "ACCEPTED";
            else
                return "INVALID";
    }
}
