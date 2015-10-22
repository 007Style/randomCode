import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExpExample {

  public static void main(String args[]) {
    String fileName = "regex.txt";

    String unadornedClassRE = ".";

    Pattern classPattern = Pattern.compile(unadornedClassRE);
    Matcher classMatcher;

    int lineNumber = 0;

    try {
      BufferedReader br = new BufferedReader(new FileReader(fileName));
      String line;

      while ((line = br.readLine()) != null) {
        lineNumber++;

        classMatcher = classPattern.matcher(line);

				try{
        if (classMatcher.find()) {
          System.out.println(classMatcher.group(1));
        }
        }catch(Exception e){}
        
      }
    } catch (IOException ioe) {
      System.out.println("IOException: " + ioe);
      ioe.printStackTrace();
    }
  }
}

/*
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExpExample {

  public static void main(String args[]) {
    String fileName = "RETestSource.java";

    String unadornedClassRE = "^\\s*class (\\w+)";
    String doubleIdentifierRE = "\\b(\\w+)\\s+\\1\\b";

    Pattern classPattern = Pattern.compile(unadornedClassRE);
    Pattern doublePattern = Pattern.compile(doubleIdentifierRE);
    Matcher classMatcher, doubleMatcher;

    int lineNumber = 0;

    try {
      BufferedReader br = new BufferedReader(new FileReader(fileName));
      String line;

      while ((line = br.readLine()) != null) {
        lineNumber++;

        classMatcher = classPattern.matcher(line);
        doubleMatcher = doublePattern.matcher(line);

        if (classMatcher.find()) {
          System.out.println("The class [" + classMatcher.group(1)
              + "] is not public");
        }

        while (doubleMatcher.find()) {
          System.out.println("The word \"" + doubleMatcher.group(1)
              + "\" occurs twice at position "
              + doubleMatcher.start() + " on line " + lineNumber);
        }
      }
    } catch (IOException ioe) {
      System.out.println("IOException: " + ioe);
      ioe.printStackTrace();
    }
  }
}
*/