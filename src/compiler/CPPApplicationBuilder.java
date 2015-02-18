package compiler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * @author Matthew Mossner
 */
class CPPApplicationBuilder extends CompiledApplicationBuilder {

   private static final int COMPILER_TIMEOUT = 0;

   private static CPPApplicationBuilder instance;

   private File workingDirectory;

   private CPPApplicationBuilder() {}

   /**
    * Compiles the provided C++ source code file.
    *
    * @param code A C++ source code file
    * @return An array of three Files, with the first element containing the
    * compiled executable file, or null if any compiler errors occurred, and the
    * remaining two elements containing any standard output and standard error
    * generated by the compiler.
    */
   @Override
   protected File[] compile(File code) throws InterruptedException, IOException {
      File[] result = new File[3];
      Application compiler = new Application(new File("/usr/bin/g++"));
      if (workingDirectory == null) {
         workingDirectory = Files.createTempDirectory("Cpp").toFile();
         workingDirectory.deleteOnExit();
      }
      String executableName = FileUtils.removeFileExtension(code);
      File[] compilerOutput = compiler.run(workingDirectory, null,
                                           COMPILER_TIMEOUT,
                                           code.getAbsolutePath(),
                                           "-o", executableName);
      result[0] = FileUtils.getFile(workingDirectory, executableName);
      if (result[0] != null)
         result[0].deleteOnExit();
      result[1] = compilerOutput[0];
      result[2] = compilerOutput[1];
      return result;
   }

   public static CPPApplicationBuilder getInstance() {
      if (instance == null)
         instance = new CPPApplicationBuilder();
      return instance;
   }
}