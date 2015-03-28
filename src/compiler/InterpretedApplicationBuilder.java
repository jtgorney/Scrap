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
package compiler;

import java.io.File;

/**
 * Abstract class for building Applications which consist of compiled
 * intermediate code that is run through an interpreter.
 *
 * @author Matthew Mossner
 */
public abstract class InterpretedApplicationBuilder extends ApplicationBuilder {

   /**
    * Creates a new Application which runs the provided source code file through
    * the appropriate interpreter.
    *
    * @param code A source code file written in the appropriate language
    * @return An Application derived from the provided code file
    */
   abstract Application constructApplication(File code);

   /**
    * @param code A source code file written in the appropriate language
    * @return An Application derived from the provided code file
    */
   final Application getApplication(File code) {
      return constructApplication(code);
   }
}
