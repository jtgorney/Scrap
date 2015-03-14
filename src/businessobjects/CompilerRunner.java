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
     * Used to identify the runner.
     */
    private long token = System.currentTimeMillis();
    /**
     * Is a submission run for scoring.
     */
    private boolean isSubmission = false;
    /**
     * Accepted solution or not.
     */
    private boolean isAccepted = false;
    
    /**
     * Constructor to build the runner.
     * @param teamId Team ID
     * @param problemId Problem set ID
     * @param type Source code type
     * @param f Source code file
     * @param submission Is a submission run
     */
    public CompilerRunner(int teamId, int problemId, String type, File f,
            boolean submission) {
        this.teamId = teamId;
        this.problemId = problemId;
        this.type = type;
        sourceCode = f;
        isSubmission = submission;
    }
    
    /**
     * Return the runner's token. Used for identification.
     * @return Token
     */
    public long getToken() {
        return token;
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
    
    /**
     * Get submission flag.
     * @return Submission
     */
    public boolean isSubmission() {
        return isSubmission;
    }
    
    /**
     * Set acceptance flag.
     * @param v Value
     */
    public void setAccepted(boolean v) {
        isAccepted = v;
    }
    
    /**
     * Get accepted flag.
     * @return Accepted
     */
    public boolean isAccepted() {
        return isAccepted;
    }
}
