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
import java.io.IOException;

/**
 * Abstract class for building Applications that run compiled intermediate code
 * files via an interpreter.
 *
 * @author Matthew Mossner
 */
public abstract class CompiledAndInterpretedApplicationBuilder
        extends CompiledApplicationBuilder {

   /**
    * Creates a new Application which runs the provided intermediate code file
    * through the appropriate interpreter.
    *
    * @param compiledCode An intermediate code file
    * @return An Application derived from the provided code file
    */
   abstract Application constructApplication(File compiledCode);

   /**
    * @param code A source code file written in the appropriate language
    * @return An Application derived from the provided source code file
    * @throws java.io.IOException If an I/O exception of some sort occurs during
    * compilation
    */
   @Override
   Application getApplication(File code) throws InterruptedException,
                                                IOException {
      compilerOutput = compile(code);
      if (compilerOutput[0] != null)
         return constructApplication(compilerOutput[0]);
      return null;
   }
}
