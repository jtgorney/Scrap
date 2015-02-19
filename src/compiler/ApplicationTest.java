/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Matt Mossner
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

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Matthew Mossner
 */
public class ApplicationTest {

   private static final String inputStr = "I am standard.";
   private static final String argStr = "I am error.";

   private static final File inputExecutable =
           new File("testfiles/Application/acceptsinput");
   private static final File noInputExecutable =
           new File("testfiles/Application/noinput");
   private static final File newWorkingDir =
           new File("testfiles/Application/output");
   private static final File nonexistantFile = new File("");
   private static final File javac =
           new File("/usr/lib/jvm/j2sdk1.8-oracle/bin/java");
   private static final File testApp =
           new File("testfiles/Application/Test.class");

   private static File outputFile;
   private static File input;

   @BeforeClass
   public static void setUpClass() throws Exception {
      input = File.createTempFile("input", "data");
      input.deleteOnExit();
      PrintStream writer = new PrintStream(input);
      writer.println(inputStr);
      writer.close();
   }

   /**
    * Runs a valid executable application in a valid working directory, with a
    * valid standard input file, and with additional arguments.
    */
   @Test
   public void testRunValidExecutable() throws Exception {
      File[] runOutput;
      String stdOutput;
      String stdError;
      Scanner s;
      Application app = new Application(inputExecutable);
      runOutput = app.run(newWorkingDir, input, 30, argStr);
      s = new Scanner(runOutput[0]);
      stdOutput = s.nextLine();
      s.close();
      s = new Scanner(runOutput[1]);
      stdError = s.nextLine();
      s.close();
      outputFile = new File(newWorkingDir.getAbsolutePath() + "/out.data");
      assertEquals(inputStr, stdOutput);
      assertEquals(argStr, stdError);
      assertTrue("Output file \"" + outputFile.getAbsolutePath()
                 + "\" not found.", outputFile.exists());
   }

   @Test
   /**
    * Runs a valid executable in the working directory of the calling process,
    * with a valid standard input file, and without additional arguments.
    */
   public void testRunValidExecutableSameDir() throws Exception {
      File[] runOutput;
      String stdOutput;
      String stdError;
      Scanner s;
      Application app = new Application(inputExecutable);
      runOutput = app.run(null, input, 30);
      s = new Scanner(runOutput[0]);
      stdOutput = s.nextLine();
      s.close();
      s = new Scanner(runOutput[1]);
      stdError = s.nextLine();
      s.close();
      outputFile = new File("out.data");
      assertEquals(inputStr, stdOutput);
      assertEquals("", stdError);
      assertTrue("Output file \"" + outputFile.getAbsolutePath()
                 + "\" not found.", outputFile.exists());
   }

   @Test
   /**
    * Runs a valid executable in the working directory of the calling process,
    * with no standard input file, and with multiple blank arguments.
    */
   public void testRunValidExecutableNoInput() throws Exception {
      File[] runOutput;
      String stdOutput;
      String stdError;
      Scanner s;
      Application app = new Application(noInputExecutable);
      runOutput = app.run(null, null, 30, "", "");
      s = new Scanner(runOutput[0]);
      stdOutput = s.nextLine();
      s.close();
      s = new Scanner(runOutput[1]);
      stdError = s.nextLine();
      s.close();
      outputFile = new File("out.data");
      assertEquals(inputStr, stdOutput);
      assertEquals("", stdError);
      assertTrue("Output file \"" + outputFile.getAbsolutePath()
                 + "\" not found.", outputFile.exists());
   }

   @Test
   /**
    * Runs a valid interpreted application in a valid working directory, with
    * a valid standard input file, and with additional arguments.
    */
   public void testRunInterpretedApplication() throws Exception {
      File[] runOutput;
      String stdOutput;
      String stdError;
      Scanner s;
      String className = testApp.getName();
      className = className.substring(0, className.length() - 6);
      Application app =
              new Application(javac, "-cp",
                              testApp.getParentFile().getAbsolutePath(),
                              className);
      runOutput = app.run(newWorkingDir, input, 30, argStr);
      s = new Scanner(runOutput[0]);
      stdOutput = s.nextLine();
      s.close();
      s = new Scanner(runOutput[1]);
      stdError = s.nextLine();
      s.close();
      outputFile = new File(newWorkingDir.getAbsolutePath() + "/out.data");
      assertEquals(inputStr, stdOutput);
      assertEquals(argStr, stdError);
      assertTrue("Output file \"" + outputFile.getAbsolutePath()
                 + "\" not found.", outputFile.exists());
   }

   /**
    * Attempts to run a valid executable in an invalid working directory.
    */
   @Test(expected = IOException.class)
   public void testRunValidExecutableInvalidDir() throws Exception {
      Application app = new Application(inputExecutable);
      app.run(nonexistantFile, null, 30);
   }

   /**
    * Attempts to run a valid executable with an invalid input file.
    */
   @Test(expected = IOException.class)
   public void testRunValidExecutableInvalidInput() throws Exception {
      Application app = new Application(inputExecutable);
      app.run(null, nonexistantFile, 30);
   }

   /**
    * Attempts to run an invalid executable file.
    */
   @Test(expected = IOException.class)
   public void testRunInvalidExecutable() throws Exception {
      Application app = new Application(nonexistantFile);
      app.run(null, null, 30);
   }

   @After
   public void tearDown() {
      if (outputFile != null)
         outputFile.delete();
   }
}