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
package compiler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author matthew
 */
public class OutputParser {
    
    private String status = "";
    private String stdOutput = "";
    private String stdError = "";
    
    /**
     *
     * @param f
     */
    public OutputParser(File f) throws FileNotFoundException {
        Scanner s = new Scanner(f);
        status = s.nextLine();
        String line = s.nextLine();
        if (line.equals(CodeProcessor.BEGIN_OUTPUT)) {
            line = s.nextLine();
            if (line.equals(CodeProcessor.BEGIN_STD_OUT)) {
                line = s.nextLine();
                while (! line.equals(CodeProcessor.END_STD_OUT)) {
                    stdOutput += line.substring(1) + "\n";
                    line = s.nextLine();
                }
            }
            line = s.nextLine();
            if (line.equals(CodeProcessor.BEGIN_STD_ERR)) {
                line = s.nextLine();
                while (! line.equals(CodeProcessor.END_STD_ERR)) {
                    stdError += line.substring(1) + "\n";
                    line = s.nextLine();
                }
            }
        }
        s.close();
    }
    
    public String getStatusHeader() {
        return status;
    }
    
    public String getStdOutput() {
        return stdOutput;
    }
    
    public String getStdError() {
        return stdError;
    }
}