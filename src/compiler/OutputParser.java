/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
    
    private final String status;
    private String stdOutput;
    private String stdError;
    
    /**
     *
     * @param f
     */
    public OutputParser(File f) throws FileNotFoundException {
        Scanner s = new Scanner(f);
        status = s.nextLine();
        String line = s.nextLine();
        if (! line.equals(CodeProcessor.END_FILE)) {
            line = s.nextLine();
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