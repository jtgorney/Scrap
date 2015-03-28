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

import org.junit.AfterClass;
import org.junit.Test;

import java.io.File;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author Matthew Mossner
 */
public class JavaApplicationBuilderTest {

   private final ApplicationBuilder builder = JavaApplicationBuilder.getInstance();
   private static final File goodCode =
           new File("testfiles/JavaApplicationBuilder/good.java");
   private static final File badCode =
           new File("testfiles/JavaApplicationBuilder/bad.java");

   @Test
   public void testGetApplication() throws Exception {
      Application app = builder.getApplication(goodCode);
      File[] runOutput = app.run(null, null, 30);
      Scanner s = new Scanner(runOutput[0]);
      String stdOutput = s.nextLine();
      s.close();
      assertEquals("I am standard.", stdOutput);
   }

   @Test
   public void testGetApplicationCompilerError() throws Exception {
      Application app = builder.getApplication(badCode);
      assertNull(app);
   }

   @AfterClass
   public static void tearDownClass() {
      File newGoodCode =
              new File("testfiles/JavaApplicationBuilder/GoodTestApp.java");
      File newBadCode =
              new File("testfiles/JavaApplicationBuilder/BadTestApp.java");
      newGoodCode.renameTo(goodCode);
      newBadCode.renameTo(badCode);
   }
}