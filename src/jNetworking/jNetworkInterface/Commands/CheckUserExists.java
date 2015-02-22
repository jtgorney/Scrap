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

import db.DBMgr;
import jNetworking.jNetworkInterface.Command;
import jNetworking.jNetworkInterface.jNetworkInterface;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Command to check if a username exists.
 * @author Jacob Gorney
 */
public class CheckUserExists implements Command {
    /**
     * Username to check.
     */
    private String username;
    
    @Override
    public void setup(ArrayList<String> input, Socket client) {
        username = jNetworkInterface.base64Decode(input.get(0));
    }

    @Override
    public String run() {
        // Call check exists on DBMgr.
        DBMgr dbmgr = new DBMgr();
        if (!DBMgr.build("mysql.rentalsbyjb.com", "cs421_scrap",
                "cs421_scrap", "cs421#scrap")) {
            System.out.println("Error connecting to database.");
            System.exit(0);
        }
        // Return the result
        if (dbmgr.doesUserExist(username))
            return "EXISTS";
        else
            return "OK";
    }
    
}