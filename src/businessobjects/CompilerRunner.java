/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessobjects;

import java.io.File;

/**
 *
 * @author Jacob Gorney
 */
public class CompilerRunner {
    private int teamId;
    private int problemId;
    private File sourceCode;
    private File resultFile;
    
    public CompilerRunner(int teamId, int problemId, File f) {
        this.teamId = teamId;
        this.problemId = problemId;
        sourceCode = f;
    }
    
    public int getTeamId() {
        return teamId;
    }
    
    public int getProblemId() {
        return problemId;
    }
    
    public File getSourceCode() {
        return sourceCode;
    }
    
    public File getResultFile() {
        return resultFile;
    }
    
    public void setResultFile(File f) {
        resultFile = f;
    }
}
