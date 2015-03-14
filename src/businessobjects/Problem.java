/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessobjects;

/**
 *
 * @author jtgorney
 */
public class Problem {
    /**
     * Problem Number
     */
    public int problemNumber;
    /**
     * Problem Title
     */
    public String problemTitle;
    /**
     * Problem Text
     */
    public String problemText;
    
    /**
     * Constructor
     * @param n Problem number
     * @param t Title
     * @param d Description
     */
    public Problem(int n, String t, String d) {
        problemNumber = n;
        problemTitle = t;
        problemText = d;
    }
    
    /**
     * Set problem number.
     * @param problemNumber Problem Number 
     */
    public void setProblemNumber(int problemNumber) {
        this.problemNumber = problemNumber;
    }
    
    /**
     * Set problem title.
     * @param problemTitle Title 
     */
    public void setProblemTitle(String problemTitle) {
        this.problemTitle = problemTitle;
    }
    
    /**
     * Set problem text.
     * @param problemText Text 
     */
    public void setProblemText(String problemText) {
        this.problemText = problemText;
    }
    
    /**
     * Get problem number.
     * @return 
     */
    public int getProblemNumber() {
        return problemNumber;
    }
    
    /**
     * Get problem title.
     * @return 
     */
    public String getProblemTitle() {
        return problemTitle;
    }
    
    /**
     * Get problem text.
     * @return 
     */
    public String getProblemText() {
        return problemText;
    }
}
