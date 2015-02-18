package compiler;

import java.io.File;
import java.io.IOException;

/**
 * Abstract class for building Applications from source code.
 *
 * @author Matthew Mossner
 */
abstract class ApplicationBuilder {

   /**
    * @param code A source code file written in the appropriate language
    * @return An Application derived from the provided source code file
    * @throws java.io.IOException If the source code must be compiled and an I/O
    * exception of some sort occurs during compilation
    */
   abstract Application getApplication(File code) throws InterruptedException,
                                                         IOException;
}