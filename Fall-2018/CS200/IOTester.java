import java.util.*;
public class IOTester
{
   public static void main(String[] args)
   {
      IOTools test = new IOTools();
      String strData = test.ReadIn("test.txt");
      ArrayList<Integer> data = test.ToArray(strData);
      data.add(99);
      data.add(201);
      test.WriteData(data, "test.txt");
   }
}