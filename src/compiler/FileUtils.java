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
