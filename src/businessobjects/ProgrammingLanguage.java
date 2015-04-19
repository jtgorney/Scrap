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
public class ProgrammingLanguage {

    private String name;
    private boolean compiled;
    private String compilerExecutable;
    private String compilerArgs;
    private String compilerInExt;
    private String compilerOutExt;
    private boolean interpreted;
    private String interpreterExecutable;
    private String interpreterArgs;
    private String interpreterInExt;
    private String fileNameRegEx;

    public ProgrammingLanguage(String name, boolean compiled, boolean interpreted) {
        this.name = name;
    }

    public boolean isCompiled() {
        return compiled;
    }

    public String getCompilerExecutable() {
        return compilerExecutable;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCompiled(boolean compiled) {
        this.compiled = compiled;
    }

    public void setCompilerExecutable(String compilerExecutable) {
        this.compilerExecutable = compilerExecutable;
    }

    public void setCompilerArgs(String compilerArgs) {
        this.compilerArgs = compilerArgs;
    }

    public void setCompilerInExt(String compilerInExt) {
        this.compilerInExt = compilerInExt;
    }

    public void setCompilerOutExt(String compilerOutExt) {
        this.compilerOutExt = compilerOutExt;
    }

    public void setInterpreted(boolean interpreted) {
        this.interpreted = interpreted;
    }

    public void setInterpreterExecutable(String interpreterExecutable) {
        this.interpreterExecutable = interpreterExecutable;
    }

    public void setInterpreterArgs(String interpreterArgs) {
        this.interpreterArgs = interpreterArgs;
    }

    public void setInterpreterInExt(String interpreterInExt) {
        this.interpreterInExt = interpreterInExt;
    }

    public void setFileNameRegEx(String fileNameRegEx) {
        this.fileNameRegEx = fileNameRegEx;
    }

    public String getCompilerArgs() {
        return compilerArgs;
    }

    public String getCompilerInExt() {
        return compilerInExt;
    }

    public String getCompilerOutExt() {
        return compilerOutExt;
    }

    public boolean isInterpreted() {
        return interpreted;
    }

    public String getInterpreterExecutable() {
        return interpreterExecutable;
    }

    public String getInterpreterArgs() {
        return interpreterArgs;
    }

    public String getInterpreterInExt() {
        return interpreterInExt;
    }

    public String getFileNameRegEx() {
        return fileNameRegEx;
    }    
}
