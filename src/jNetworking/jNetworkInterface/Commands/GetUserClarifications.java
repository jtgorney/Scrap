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
package jNetworking.jNetworkInterface.Commands;

import businessobjects.Clarification;
import com.google.gson.Gson;
import db.DBMgr;
import jNetworking.jNetworkInterface.Command;
import jNetworking.jNetworkInterface.jNetworkInterface;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Get clarifications initiated by a particular user.
 * @author Matthew Mossner
 */
public class GetUserClarifications implements Command {

    private int userId;
    private long lastUpdate;
    
    @Override
    public void setup(ArrayList<String> input, Socket client) {
        userId = Integer.parseInt(jNetworkInterface.base64Decode(input.get(0)));
        lastUpdate = Integer.parseInt(jNetworkInterface.base64Decode(input.get(1)));
    }

    @Override
    public String run() {
        DBMgr dbmgr = new DBMgr();
        ArrayList<Clarification> clarifications = dbmgr.getUserClarifications(userId, lastUpdate);
        return new Gson().toJson(clarifications);
    }
    
}