package jNetworking.jNetworkInterface.Commands;

import businessobjects.Clarification;
import com.google.gson.Gson;
import jNetworking.jNetworkInterface.Command;
import jNetworking.jNetworkInterface.jNetworkInterface;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Matthew Mossner
 */
public class RequestClarification implements Command {

    private int userId;
    private int problemNumber;
    private String question;
    
    @Override
    public void setup(ArrayList<String> input, Socket client) {
        userId = Integer.parseInt(jNetworkInterface.base64Decode(input.get(0)));
        problemNumber = Integer.parseInt(jNetworkInterface.base64Decode(input.get(1)));
        question = jNetworkInterface.base64Decode(input.get(2));
    }

    @Override
    public String run() {
        Clarification clarification= new Clarification(new Double(Math.random() * Integer.MAX_VALUE).intValue(), 
                problemNumber, question, null, new Date().getTime());
        return new Gson().toJson(clarification);
    }
    
}
