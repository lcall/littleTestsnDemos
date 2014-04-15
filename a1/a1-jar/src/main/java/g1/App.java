package g1;

import java.io.*;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/** Hello world! */
public class App {
  // compiler forces us to do something about Exception (next line), or we would have had to catch things on several lines (and handle somehow).
  public static void main(String[] args) throws Exception {
    // /*  3) in list
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
      } catch (OutOfMemoryError error) {
        // compiler doesn't expect one to catch this either, and even if one does, one might not be able to recover:
        System.out.println("Failed after: " + l.size());
      }
    }
    //*/

    ///*  6)
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
      System.out.println("sorted file is: " + f2.getCanonicalPath());
      System.out.println("reverse sorted file is: " + f3.getCanonicalPath());
    }
    //*/



    // /* showing some exceptions:    8)
    // 1- checked, see comment before main
    // 2- error: search above for error re memory
    // 3- unchecked:
    System.out.println();
    System.out.println();
    System.out.println();
    System.out.println();
    try {
      BufferedReader brFailing = new BufferedReader(new FileReader(new File("doesntexistanywhereihope")));
    } catch (FileNotFoundException unchecked) {
      System.out.println("we did get the FileNotFoundException, though compiler didn't require us to catch it");
    }
    // */



    // /* enums: useful when you know all the instances of a type at compile time; advantage: type safety
    // 9)
    System.out.println();
    System.out.println();
    System.out.println();
    System.out.println();
    System.out.println("The RING finger is #" + FingerType.getNumber(FingerType.RING) + ", as far as i know.");
    System.out.println("And #5 is a " + FingerType.whaddyaCallThisOne(5));
    System.out.println("But which one is #5, on a 6-fingered man? Maybe it's the " + FingerType.whaddyaCallThisOne(5, 6) + " finger.");
    System.out.println("And #11?? on a 99-fingered man? " + FingerType.whaddyaCallThisOne(11, 99));
    // */

    System.out.println();
    System.out.println();
    System.out.println();
    System.out.println("by value vs reference, mutating parms:");
    ArrayList list = new ArrayList<String>();
    list.add("1234");
    int i = 9;
    int j = 10;
    System.out.println("BEFORE method call: " + list + ", " + list.get(0) + ", " + i + ", " + j);
    System.out.println("list == list?: " + (list == list));
    showParameterBehavior(list, i, j);
    System.out.println(" AFTER method call: " + list + ", " + list.get(0) + ", " + i + ", " + j);
    System.out.println("list == list? :" + (list == list));



    // json/logging:
    // (NOW I see that I could probably do the json conversion in log4j.xml itself and that would prob'ly be better, in real life, as for one it
    // would get the level properly into the output.)
    LogLine ld = new LogLine("some informative message to debug", "0");
    LogLine li = new LogLine("some informative message to info", "0");
    ObjectMapper mapper = new ObjectMapper();
    // convert user object to json string, and save to a file
    mapper.writeValue(new File("/tmp/some-json.log"), ld);
    String jsonMessage1 = mapper.writeValueAsString(ld);
    String jsonMessage2 = mapper.writeValueAsString(li);

    //slf4j, using LogLine instead of "this" for quick convenience only:
    System.out.println();
    System.out.println();
    System.out.println();
    System.out.println("slf4j:");
    Logger logger = LoggerFactory.getLogger(LogLine.class); // used LogLine for pure convenience
    logger.debug("logging to debug");
    logger.debug(jsonMessage1);
    logger.info("logging to info");
    logger.info(jsonMessage2);

    // display to console
    //%%next:
    //  does this fill the req't?:  "output a log message to the console and a log file at different logging levels using log4j or slf4j, using the
    //                               department log statement format (turn on DEBUG and show DEBUG logging)"
    //  test it: %%INCLUDE: timestamp,log level, ip, some msg w/ a varnameor whtaever.
    // then how change the level w/o recompile? & do that/show it.
  }

  public enum FingerType { // allows the 6-fingered man to have 2 ring fingers
    THUMB, INDEX, MIDDLE, RING, PINKIE;

    public static int getNumber(FingerType ft) {
      // humans don't start at 0, usually.
      return ft.ordinal() + 1;
    }

    public static String whaddyaCallThisOne(int num) {
      return whaddyaCallThisOne(num, 5);
    }

    public static String whaddyaCallThisOne(int num, int of) {
      if (num == 1) return THUMB.name();
      if (num == 2) return INDEX.name();
      if (num == 3) return MIDDLE.name();
      if (num == 4) return RING.name();
      if (num == 5 && of == 5) return PINKIE.name();
      if (num == 5 && of == 6) return RING.name();
      else return "Beats me. You have a tricky situation.";
      // alternative:
      //else throw new UnsupportedOperationException("You have a tricky situation.");
    }
  }


  public class ThirdPartyFileOpener {
    public final void openFile() {
      System.out.println("opening a file");
    }
    // compiler won't allow inner classes w/ static methods:
    //public static void open2Files() {
    //  System.out.println("opening 2 files");
    //}
  }
  public class XMLThing {
    private ThirdPartyFileOpener myFileOpener;
    // composition:
    XMLThing(ThirdPartyFileOpener fo) {
      myFileOpener = fo;
    }
    // alternative "composition"?: could pass in something else ...
    public void writeIt(ThirdPartyFileOpener fo1) {
      fo1.openFile();
    }
    public void writeOther() {
      try {
        //static:
        File.createTempFile("x", ".txt");
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    // inheritance:
    // now TextThing cannot extend anything else (except by composition). Plus it isn't really
    // a kind of FileOpener .
    public class TextThing extends ThirdPartyFileOpener {
      // compiler error:
      //public void openFile() {
      //  System.out.println("opening it with some xtra textThing-related efficiency");
      //}
      // It also gets a compiler error if the parent implements "writeIt" (such as for another purpose or
      // with undesired behavior for this class).

      public void writeIt() {
        openFile();
      }
    }
    public void writeMore() {
      // composition:
      myFileOpener.openFile();
    }
  }



  //constructor chaining...
  class Chained {
    private int a,b,c,d;
    Chained(int bIn, int cIn, int dIn) {
      b=bIn;
      c=cIn;
      d=dIn;
    }
    Chained() {
      this(2, 3, 4);
      a=1;
    }

    public void doIt() {
      Chained c = new Chained();
    }
  }


  //encapsulation: bad/good
  public class InterruptController {
    public void doManyOperationsOnThePCIBus() {
      System.out.println("very busy here, working hard");
    }
    public void open() {System.out.println("opening");}
    public void close() {System.out.println("closing");}
  }
  //bad, because the AudioGUI method is talking directly to the hardware's internals (logic doesn't belong there, as GUIs shouldn't
  // have to know hardware details: violates the abstraction, law of demeter....
  public class AudioDevice {
    public InterruptController interruptController;
    AudioDevice() {
      interruptController = new InterruptController();
    }
  }
  class AudioGUI {
    AudioDevice ad = new AudioDevice();
    public void doIt() {
      ad.interruptController.open();
      ad.interruptController.doManyOperationsOnThePCIBus();
      ad.interruptController.close();
    }
  }

  //better, because the AudioGUI method is talking only to the device, which in turn talks to the hardware's internals
  public class AudioDevice2 {
    public InterruptController interruptController;
    AudioDevice2() {
      interruptController = new InterruptController();
    }
    // 2 overloaded methods:
    // plus: proliferation/verbosity/complexity in namespace?
    // minus: otherwise you can have messy methods with the bad smell of too many if's/case expressions?
    public void doIt() {
      doIt(false);
    }
    public void doIt(boolean more) {
      interruptController.open();
      interruptController.doManyOperationsOnThePCIBus();
      if (more) {
        interruptController.doManyOperationsOnThePCIBus();
        interruptController.doManyOperationsOnThePCIBus();
        interruptController.doManyOperationsOnThePCIBus();
        interruptController.doManyOperationsOnThePCIBus();
        interruptController.doManyOperationsOnThePCIBus();
      }
      interruptController.close();
    }
  }
  class AudioGUI2 {
    AudioDevice2 ad = new AudioDevice2();
    public void doIt() {
      ad.doIt(true);
    }
  }



  static void showParameterBehavior(ArrayList list, int changed, final int cantChange) {
    list.set(0,"5678");
    changed = 99;
    // compiler error:
    //cantChange = 100;
  }

}
