package usercode;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Provides convenience utilities for working with files.
 *
 * @author Matthew Mossner
 */
public class FileUtils {

   /**
    * Retrieves an returns the file extension of the provided File.
    *
    * @param file The File to retrieve the extension from
    * @return The file extension of the provided File
    */
   public static String fileExtension(File file) {
      String fileName = file.getName();
      if (fileName.contains("."))
         return fileName.substring(fileName.lastIndexOf('.'));
      else
         return "";
   }

   /**
    * Removes the file extension from the name of the provided File and returns
    * the result.
    *
    * @param file The File to remove the file extension from
    * @return The name of the File, without the file extension
    */
   public static String removeFileExtension(File file) {
      String fileName = file.getName();
      if (fileName.contains("."))
         return fileName.substring(0, fileName.lastIndexOf('.'));
      return fileName;
   }

   /**
    * Searches the provided directory for a file with the provided name.
    *
    * @param directory The directory to search
    * @param fileName The name of the file to search
    * @return The searched for file if located; null otherwise
    * @throws java.lang.NullPointerException if the provided directory does not
    * exist
    */
   public static File getFile(File directory, final String fileName) {
      File[] files;
      files = directory.listFiles(new FilenameFilter() {
    	  @Override
    	  public boolean accept(File dir, String name) {
    		  return (name.equals(fileName));
    	  }
      });
      if (files.length > 0)
         return files[0];
      return null;
   }
}
