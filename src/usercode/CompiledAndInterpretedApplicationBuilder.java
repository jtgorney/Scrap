package usercode;

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
