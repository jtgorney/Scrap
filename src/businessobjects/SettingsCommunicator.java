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

import ui.AdminFrame;
import controllers.AdminController;
/**
 * This class will eventually perform settings lookup via the server.
 * @author Jacob Gorney
 */
public class SettingsCommunicator {
    /**
     * Competition Name
     */
    public static String strCompetitionName = "HSPC 2015";
   
    /**
     * School Name
     */
    public static String strSchoolName = "Saginaw Valley State University";
    
    /**
     * Get the remote server address.
     * @return Server IP Address
     */
    public static String getServerAddr() {
        return "127.0.0.1";
    }
    
    /**
     * Get the remote server port.
     * @return Server port
     */
    public static int getServerPort() {
        return 8888;
    }
    
    /**
     * Set the competition name.
     * @param strCompName Competition Name
     */
    public static void setCompetitionName(String strCompName) {
        strCompetitionName = strCompName;
    }
    
    /**
     * Get the competition name.
     * @return Competition name
     */
    public static String getCompetitionName() {
        return strCompetitionName;
    }
    
    /**
     * Set the school name.
     * @param strCompSchoolName School Name
     */
    public static void setSchoolName(String strCompSchoolName) {
        strSchoolName = strCompSchoolName;
    }
    
    /**
     * Get the competition school.
     * @return School name
     */
    public static String getSchoolName() {
        return strSchoolName;
    }
    
    /**
     * Get the path to save files to.
     * @return Save path
     */
    public static String getSavePath() {
        return "code/";
    }
}
