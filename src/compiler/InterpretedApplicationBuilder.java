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
