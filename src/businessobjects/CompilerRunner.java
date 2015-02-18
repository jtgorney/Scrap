/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessobjects;

import java.io.File;

/**
 * Compiler task runner class.
 * @author Jacob Gorney
 */
public class CompilerRunner {
    /**
     * Team ID
     */
    private final int teamId;
    /**
     * Problem set ID
     */
    private final int problemId;
    /**
     * cpp or java
     */
    private String type;
    /**
     * Source code file
     */
    private final File sourceCode;
    /**
     * Result output file.
     */
    private File resultFile;
    
    /**
     * Constructor to build the runner.
     * @param teamId Team ID
     * @param problemId Problem set ID
     * @param type Source code type
     * @param f Source code file
     */
    public CompilerRunner(int teamId, int problemId, String type, File f) {
        this.teamId = teamId;
        this.problemId = problemId;
        this.type = type;
        sourceCode = f;
    }
    
    /**
     * Get the Team ID.
     * @return Team ID
     */
    public int getTeamId() {
        return teamId;
    }
    
    /**
     * Get the problem set ID.
     * @return Problem ID
     */
    public int getProblemId() {
        return problemId;
    }
    
    /**
     * Get the source code file.
     * @return Source code file
     */
    public File getSourceCode() {
        return sourceCode;
    }
    
    /**
     * Get the compiler result file.
     * @return Compiler result file
     */
    public File getResultFile() {
        return resultFile;
    }
    
    /**
     * Set the compiler result file.
     * @param f Compiler result file
     */
    public void setResultFile(File f) {
        resultFile = f;
    }
}
