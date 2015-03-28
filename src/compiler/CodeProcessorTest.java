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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * @author Matthew Mossner
 */
public class CodeProcessorTest {

   private static final CodeProcessor p = CodeProcessor.getInstance();

   private static final File goodOutput =
           new File("testfiles/CodeProcessor/GoodOutput");
   private static final File timeoutOutput =
           new File("testfiles/CodeProcessor/TimeoutOutput");
   private static final File nonexistentFile = new File("");
   private static final File nonexistantCPPFile = new File("phantom.cpp");
   private static final File nullFile = null;
   private static final File inputFile =
           new File("testfiles/CodeProcessor/input");

   private static File inputJava;
   private static File goodJava;
   private static File badJava;
   private static File inputCPP;
   private static File goodCPP;
   private static File badCPP;

   /**
    * Copies source code files to the current working directory before each test
    * run.
    */
   @Before
   public void setup() throws Exception {
      inputJava = Files.copy(
              new File("testfiles/CodeProcessor/InputTestApp.java").toPath(),
              new File("InputTestApp.java").toPath()).toFile();
      goodJava = Files.copy(
              new File("testfiles/CodeProcessor/TestApp.java").toPath(),
              new File("TestApp.java").toPath()).toFile();
      badJava = Files.copy(
              new File("testfiles/CodeProcessor/BadTestApp.java").toPath(),
              new File("BadTestApp.java").toPath()).toFile();
      inputCPP = Files.copy(
              new File("testfiles/CodeProcessor/input.cpp").toPath(),
              new File("input.cpp").toPath()).toFile();
      goodCPP = Files.copy(
              new File("testfiles/CodeProcessor/good.cpp").toPath(),
              new File("good.cpp").toPath()).toFile();
      badCPP = Files.copy(
              new File("testfiles/CodeProcessor/bad.cpp").toPath(),
              new File("bad.cpp").toPath()).toFile();
   }

   /**
    * Tests valid source code files, with and without input.
    */
   @Test
   public void testValidFiles() throws Exception {
      String expected = new String(Files.readAllBytes(goodOutput.toPath()));
      p.add(new File[][]
                    {{goodJava}, {goodCPP}, {inputJava, inputFile},
                            {inputCPP, inputFile}});
      p.process();
      File[] results = p.getResults();
      for (File result : results) {
         String actual = new String(Files.readAllBytes(result.toPath()));
         assertEquals(expected, actual);
      }
   }

   /**
    * Processes source code files which contain compiler errors.
    */
   @Test
   public void testCompilerErrors() throws Exception {
      Scanner s;
      String expected = CodeProcessor.COMPILER_ERROR;
      p.add(new File[][]{{badJava}, {badCPP}});
      p.process();
      File[] results = p.getResults();
      s = new Scanner(results[0]);
      String actual = s.nextLine();
      assertEquals(expected, actual);
      s.close();
      s = new Scanner(results[1]);
      actual = s.nextLine();
      assertEquals(expected, actual);
   }

   /**
    * Processes a nonexistent source code file with no name, producing a result
    * indicating that the language is not supported.  Also processes a
    * nonexistent source code file with a supported file extension, leading to a
    * compiler error.
    */
   @Test
   public void testNonexistentCode() throws Exception {
      Scanner s;
      p.add(new File[]{nonexistentFile});
      p.add(new File[]{nonexistantCPPFile});
      p.process();
      File result = p.getResult();
      s = new Scanner(result);
      String actual = s.nextLine();
      s.close();
      assertEquals(CodeProcessor.UNSUPPORTED_LANGUAGE, actual);
      result = p.getResult();
      s = new Scanner(result);
      actual = s.nextLine();
      s.close();
      assertEquals(CodeProcessor.COMPILER_ERROR, actual);
   }

   /**
    * Adds a null pointer as a source code file, leading to a
    * NullPointerException when attempting to process it.
    */
   @Test(expected = NullPointerException.class)
   public void testNullCode() throws Exception {
      p.add(new File[]{nullFile});
      try {
         p.process();
      }
      catch (NullPointerException e) {
         assertTrue(p.hasRemaining());
         p.removeNextRemaining();
         assertFalse(p.hasRemaining());
         throw e;
      }
   }

   /**
    * Attempts process a source code file in which standard input is read
    * without providing any input files, leading to a timeout.
    */
   @Test
   public void testTimeout() throws Exception {
      p.add(new File[]{inputCPP, null});
      p.process();
      String actual = new String(Files.readAllBytes(p.getResult().toPath()));
      String expected = new String(Files.readAllBytes(timeoutOutput.toPath()));
      assertEquals(expected, actual);
   }

   /**
    * Deletes all remaining copied source code files from the current working
    * directory after each test run.
    */
   @After
   public void tearDown() {
      inputJava.delete();
      goodJava.delete();
      badJava.delete();
      inputCPP.delete();
      goodCPP.delete();
      badCPP.delete();
   }
}