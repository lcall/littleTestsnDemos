package g1;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** Hello world! */
public class App {
  public static void main(String[] args) throws Exception {
    /*
    System.out.println("Do you want to find out how many total characters can be held in a list of strings before you run out of memory? (y/n)");
    byte[] answer = new byte[10]; // 10, to eat a few more chars like the typed LF & whatever; not ideal but quick.
    System.in.read(answer);
    if (answer[0] == 'y' || answer[0] == 'Y') {
      List l = new ArrayList<String>();
      try {
        while (true) {
          l.add('x');
          //w/o this next line, got to 293,479,271.  w/ it, at 293,479,271 (same).  Huge speed dif'c.
          //if (counter % 10000== 0) System.gc();
        }
      } catch (OutOfMemoryError e) {
        System.out.println("Failed after: " + l.size());
      }
    }
    //*/

    // /*
    System.out.println("Do you want to write then sort a file w/ 10k lines? (y/n)");
    byte[] answer2 = new byte[10];
    System.in.read(answer2);
    if (answer2[0] == 'y' || answer2[0] == 'Y') {
      File f1 = File.createTempFile("callla-unsorted-", ".txt");
      File f2 = File.createTempFile("callla-sorted-", ".txt");
      File f3 = File.createTempFile("callla-reversed-", ".txt");
      PrintWriter out1 = new PrintWriter(new BufferedWriter(new FileWriter(f1)));
      PrintWriter out2 = new PrintWriter(new BufferedWriter(new FileWriter(f2)));
      PrintWriter out3 = new PrintWriter(new BufferedWriter(new FileWriter(f3)));

      System.out.println("creating file of 10k lines...");
      for (int i = 0; i < 10000; i++) {
        String s = Double.toString(Math.random());
        out1.println(s);
      }
      out1.close();

      System.out.println("reading file of 10k lines...");
      String[] lines = new String[10000];
      BufferedReader br = new BufferedReader(new FileReader(f1));
      String line=null;
      int counter = 0;
      while ((line = br.readLine()) != null) {
        lines[counter++] = line;
      }

      System.out.println("sorting file of 10k lines...");
      java.util.Arrays.sort(lines);

      System.out.println("writing sorted file of 10k lines...");
      for(int i=0;i<lines.length; i++) {
        out2.println(lines[i]);
      }
      out2.close();

      System.out.println("writing reversed sorted file of 10k lines...");
      for(int i=lines.length-1; i>=0; i--) {
        out3.println(lines[i]);
      }
      out3.close();

      System.out.println("unsorted file is: " + f1.getCanonicalPath());
      System.out.println("sorted file is: " + f1.getCanonicalPath());
      System.out.println("reverse sorted file is: " + f1.getCanonicalPath());
    }
    //*/

  }
}
