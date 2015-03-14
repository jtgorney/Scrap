//------------------------------------------------------------------------------
// Name: Matthew Mossner
// Course: CS 421
// Semester: Fall 2014
// Instructor: Il-Hyung Cho
// Date Finished: 
// Program Description:
//------------------------------------------------------------------------------
package businessobjects;

import java.util.Date;

/**
 *
 * @author Matthew Mossner
 */
public class Clarification {
    private long lastUpdate;
    private final int problemId;
    private String question;
    private String answer;
    private boolean answered;
    
    public Clarification(int problemId, String question) {
        this.problemId = problemId;
        this.question = question;
        answered = false;
        lastUpdate = new Date().getTime();
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
        lastUpdate = new Date().getTime();
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
        answered = true;
        lastUpdate = new Date().getTime();
    }

    public long getLastUpdate() {
        return lastUpdate;
    }

    public int getProblemId() {
        return problemId;
    }

    public boolean isAnswered() {
        return answered;
    } 
}
