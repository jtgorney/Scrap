package compiler;

import org.junit.Test;

import java.io.File;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author Matthew Mossner
 */
public class CPPApplicationBuilderTest {

   private final ApplicationBuilder builder = CPPApplicationBuilder.getInstance();
   private final File goodCode =
           new File("testfiles/CPPApplicationBuilder/good.cpp");
   private final File badCode =
           new File("testfiles/CPPApplicationBuilder/bad.cpp");

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
}