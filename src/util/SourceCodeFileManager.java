/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import businessobjects.SettingsCommunicator;
import com.alee.utils.FileUtils;
import java.io.File;

/**
 *
 * @author Jacob Gorney
 */
public class SourceCodeFileManager {
    /**
     * Create a source code file.
     * @param teamId Team ID
     * @param problemId Problem set ID
     * @param type cpp or java
     * @param code Code
     */
    public static File writeSourceCode(int teamId, int problemId, String type,
            String code) {
        // Write the code file
        File f = new File(
            SettingsCommunicator.getSavePath() + "code/" + teamId + "/" +
                    problemId + "/" + System.currentTimeMillis() + "." + type);
        FileUtils.writeStringToFile(code, f);
        // Return the new file
        return f;
    }
}
