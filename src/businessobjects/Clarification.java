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
