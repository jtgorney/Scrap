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
import jNetworking.jNetworkInterface.Compiler;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Jacob Gorney
 */
public class GetResult implements Command {
    
    private long token;
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
        else {
            String fileData;
            try {
            // Parse the results of the runner and return them.
            File f = runner.getResultFile();
            FileInputStream inFile = new FileInputStream(f);
            // Read to buffer
            byte[] buffer = new byte[(int) f.length()];
            new DataInputStream(inFile).readFully(buffer);
            inFile.close();
            fileData = new String(buffer, "UTF-8");
            } catch (IOException ex) {
                return "ERROR";
            }
            // @todo parse the data
            return fileData;
        }
    }
}