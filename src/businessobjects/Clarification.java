package businessobjects;

/**
 *
 * @author Matthew Mossner
 */
public class Clarification {
    
    private int id;
    private final int problemNumber;
    private String question;
    private String answer;
    private long timestamp;
    
    public Clarification(int id, int problemNumber, String question, String answer, long timestamp) {
        this.id = id;
        this.problemNumber = problemNumber;
        this.question = question;
        this.answer = answer;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }
    
    public int getProblemNumber() {
        return problemNumber;
    }
    
    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public boolean isAnswered() {
        return (answer != null);
    } 

    @Override
    public boolean equals(Object obj) {
        if (obj == null || ! (obj instanceof Clarification))
            return false;
        return (id == ((Clarification) obj).getId());
    }
}
