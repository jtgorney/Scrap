/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessobjects;

/**
 *
 * @author Jacob Gorney
 */
public class SettingsCommunicator {
    public static String getServerAddr() {
        return "127.0.0.1";
    }
    public static int getServerPort() {
        return 8888;
    }
    
    public static String getCompetitionName() {
        return "SVSU HSPC 2015";
    }
    
    public static String getCompetitionSchool() {
        return "Saginaw Valley State University";
    }
}
