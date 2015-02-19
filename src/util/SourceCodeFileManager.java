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
