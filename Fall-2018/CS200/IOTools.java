import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.PrintWriter;
import java.util.*;

public class IOTools
{
   // Read a comma deliminated txt file into a string
   // and return that string to the user. If the file
   // does not exist the IOException will be caught and
   // printed to the user. Create the file beforehand
   // in the current working directory. 
   public String ReadIn(String file)
   {
      try
      {
         String str = String.join("\n",Files.readAllLines(Paths.get(file)));
         return str;
      }
      catch(IOException e)
      {
         System.out.println(e);
         return e.getMessage();
      }     
      
   }
   
   // Split that string into a list and then copy that list
   // into an arrayList and return it to user
   public ArrayList<Integer> ToArray(String s)
   {
      ArrayList<Integer> data = new ArrayList<Integer>();
      List<String> rawData = Arrays.asList(s.split(","));
      
      for(String str : rawData)
      {
           data.add(Integer.valueOf(str));
      }
      return data;
   }
   
   // Write the given ArrayList of data to the given file
   // This does not append data, it only writes over it.
   
   // A clever user would pull the file in as data, append that
   // data to a new list and rewrite all of the data to the file 
   public void WriteData(ArrayList<Integer> data, String file)
   {
      try
      {
         PrintWriter writer = new PrintWriter(file, "UTF-8");
         for(Integer n : data)
         {
            writer.println(n+",");
         }
         writer.close();
      }
      catch (IOException e)
      {
         System.out.println(e);
      }
   }
}