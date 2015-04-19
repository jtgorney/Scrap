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

import businessobjects.ProgrammingLanguage;
import businessobjects.SettingsCommunicator;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jNetworking.jNetworkInterface.Command;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Set;

public class SetAvailableLanguages implements Command {

    private Set<ProgrammingLanguage> availableLanguages;
    
    @Override
    public void setup(ArrayList<String> input, Socket client) {
        availableLanguages = new Gson().fromJson(input.get(0), new TypeToken<Set<ProgrammingLanguage>>(){}.getType());
    }

    @Override
    public String run() {
        SettingsCommunicator.setAvailableLanguages(availableLanguages);
        return "OK";
    }

}
