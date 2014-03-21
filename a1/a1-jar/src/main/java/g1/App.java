package g1;

import java.util.ArrayList;
import java.util.List;

/** Hello world! */
public class App {
  public static void main(String[] args) throws Exception {
    // /*
    System.out.println("Do you want to find out how many total characters can be held in a list of strings before you run out of memory? (y/n)");
    byte[] answer = new byte[1];
    System.in.read(answer);
    System.out.println("answer was: " + answer[0]);
    if (answer[0] == 'y' || answer[0] == 'Y') {
      List l = new ArrayList<String>();
      //Long counter = 0L;
      try {
        while (true) {
          //counter++;
          l.add('x');
          // faster performance would be from watching the available memory every 1000 or more and only printing
          // when it seems low. but the catch below seems better still.
          //way slow:  System.out.print(counter + " ");

          //w/o this next line, got to 293,479,271.  w/ it, at 293,479,271 (same).
          //if (counter % 10000== 0) System.gc();
        }
      } catch (OutOfMemoryError e) {
        //System.gc();
        System.out.println("Failed after: " + l.size());
      }
    }
    //*/
  }
}
