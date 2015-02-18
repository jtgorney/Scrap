package compiler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

/**
 * This class is used for building Java Applications from source code files.
 *
 * @author Matthew Mossner
 */
class JavaApplicationBuilder extends CompiledAndInterpretedApplicationBuilder {

   private static final int COMPILER_TIMEOUT = 0;

   private static JavaApplicationBuilder instance;

   private File workingDirectory;

   private JavaApplicationBuilder() {}

   @Override
   protected File[] compile(final File code) throws InterruptedException,
                                                    IOException {
      File[] result = new File[3];
      if (workingDirectory == null) {
         workingDirectory = Files.createTempDirectory("Java").toFile();
         workingDirectory.deleteOnExit();
      }
      Application compiler =
              new Application(
                      new File("/usr/lib/jvm/j2sdk1.8-oracle/bin/javac"));
      String className = className(code);
      File newFile = code;
      if (className != null) {
         String parentPath = code.getParent();
         if (parentPath != null)
            newFile = new File(parentPath + "/" + className + ".java");
         else
            newFile = new File(className + ".java");
         code.renameTo(newFile);
      }
      File[] compilerOutput = compiler.run(workingDirectory, null,
                                           COMPILER_TIMEOUT,
                                           newFile.getAbsolutePath(),
                                           "-d", ".");
      result[0] =
              FileUtils.getFile(workingDirectory,
                                FileUtils.removeFileExtension(newFile) +
                                ".class");
      if (result[0] != null)
         result[0].deleteOnExit();
      result[1] = compilerOutput[0];
      result[2] = compilerOutput[1];
      return result;
   }

   @Override
   protected Application constructApplication(File compiledCode) {
      File interpreter =
              new File("/usr/lib/jvm/j2sdk1.8-oracle/bin/java");
      return new Application(interpreter, "-cp",
                             compiledCode.getParentFile().getAbsolutePath(),
                             FileUtils.removeFileExtension(compiledCode));
   }

   static JavaApplicationBuilder getInstance() {
      if (instance == null)
         instance = new JavaApplicationBuilder();
      return instance;
   }


   private String className(File code) throws FileNotFoundException {
      Scanner s = new Scanner(code);
      while (s.hasNext()) {
         String word = s.next();
         if (word.equals("class")) {
            if (s.hasNext())
               return s.next();
         }
      }
      return null;
   }
}
