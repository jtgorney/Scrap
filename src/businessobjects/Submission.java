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

/**
 * Submission object
 * @author Jacob Gorney
 */
public class Submission {
        /**
     * Submission Id
     */
    private final int submissionId;
    /**
     * Problem Id from problem set
     */
    private final int problemId;
    /**
     * Team Id
     */
    private final int teamId;
    /**
     * Score from submission
     */
    private final int score;
    /**
     * Team name
     */
    private final String teamName;
    /**
     * Solution accepted
     */
    private final boolean isAccepted;
    
    /**
     * Build a submission object.
     * @param sid Submission Id
     * @param pid Problem Id
     * @param tid Team Id
     * @param sc Score
     * @param tname Team name
     * @param accepted Accepted
     */
    public Submission(int sid, int pid, int tid, int sc, String tname, boolean accepted) {
        submissionId = sid;
        problemId = pid;
        teamId = tid;
        score = sc;
        teamName = tname;
        isAccepted = accepted;
    }
    
    /**
     * Get submission Id.
     * @return Submission Id
     */
    public int getSubmissionId() {
        return submissionId;
    }
    
    /**
     * Get problem Id.
     * @return Problem Id
     */
    public int getProblemId() {
        return problemId;
    }
    
    /**
     * Get team Id.
     * @return Team Id
     */
    public int getTeamId() {
        return teamId;
    }
    
    /**
     * Get score.
     * @return Score
     */
    public int getScore() {
        return score;
    }
    
    /**
     * Get team name.
     * @return Team name
     */
    public String getTeamName() {
        return teamName;
    }
    
    /**
     * Is accepted or not.
     * @return Accepted
     */
    public boolean isAccepted() {
        return isAccepted;
    } 
}
