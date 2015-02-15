package usercode;

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