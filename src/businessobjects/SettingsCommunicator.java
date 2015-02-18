/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessobjects;

/**
 * This class will eventually perform settings lookup via the server.
 * @author Jacob Gorney
 */
public class SettingsCommunicator {
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
     * Get the competition name.
     * @return Competition name
     */
    public static String getCompetitionName() {
        return "SVSU HSPC 2015";
    }
    
    /**
     * Get the competition school.
     * @return School name
     */
    public static String getCompetitionSchool() {
        return "Saginaw Valley State University";
    }
    
    /**
     * Get the path to save files to.
     * @return Save path
     */
    public static String getSavePath() {
        return "C:\\Scrap\\";
    }
}
