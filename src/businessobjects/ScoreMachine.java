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

import compiler.OutputParser;
import db.DBMgr;
import java.io.FileNotFoundException;

/**
 * Class to score solutions and record them.
 * @author Jacob Gorney
 */
public class ScoreMachine {
    /**
     * Score awarded for an accepted solution.
     */
    public static final int ACCEPTED_SOLUTION = 1000;
    /**
     * Score taken for rejected solution.
     */
    public static final int REJECTED_SOLUTION = -10;
    /**
     * Score taken for compile time errors.
     */
    public static final int COMPILE_ERROR = -5;
    
    /**
     * Score a solution and add to DB.
     * @param runner Compiler runner file for submission.
     * @return Accepted or not
     */
    public boolean score(CompilerRunner runner) {
        // Current score. Treated as relative.
        int score = 0;
        OutputParser parser;
        try {
            parser = new OutputParser(runner.getResultFile());
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return false;
        }
        // Check for compile error first
        if (parser.getStatusHeader().equals("COMPILE ERROR")) {
            // Penalized for compiler error because come on!
            // Rutheless Vengence
            score += COMPILE_ERROR + REJECTED_SOLUTION;
            runner.setAccepted(false);
            // Record the changes
            insertScoreToDB(runner, score);
            return false;
        } else {
            // Get the solution text
            String theirSolution = parser.getStdOutput();
            String ourSolution = getSolutionFromDB(runner.getProblemId());
            // Compare solutions
            // I decided to be generous and trim the solution of whitespace.
            if (theirSolution.trim().equals(ourSolution)) {
                score += ACCEPTED_SOLUTION;
                runner.setAccepted(true);
                // Record the changes
                insertScoreToDB(runner, score);
                return true;
            } else {
                // Solution doesn't match. lol.
                score += REJECTED_SOLUTION;
                insertScoreToDB(runner, score);
                return false;
            }
        }
    }
    
    /**
     * Get the solution for a problem from the DB.
     * @param problemNumber Problem number
     */
    private String getSolutionFromDB(int problemNumber) {
        DBMgr dbmgr = new DBMgr();
        return dbmgr.getProblemSolution(problemNumber);
    }
    
    /**
     * Record the entry.
     * @param runner Compiler runner
     * @param isAccepted Accepted or not
     */
    private void insertScoreToDB(CompilerRunner runner, int score) {
        // Score is treated as an adjustment.
        DBMgr dbmgr = new DBMgr();
        dbmgr.insertScore(runner, score);
    }
}
