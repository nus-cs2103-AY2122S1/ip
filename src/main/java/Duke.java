import java.util.Scanner;

public class Duke {
  public static void main(String[] args) {
    /*
       String logo = " ____        _        \n"
       + "|  _ \\ _   _| | _____ \n"
       + "| | | | | | | |/ / _ \\\n"
       + "| |_| | |_| |   <  __/\n"
       + "|____/ \\__,_|_|\\_\\___|\n";
       System.out.println("Hello from\n" + logo);
    */
    String LINE = "    --------------------------------------------------";
    String INDENTATION = "      ";
    Scanner scan = new Scanner(System.in);
    // abstract out printing of response
    System.out.println(LINE + "\n" + INDENTATION + "Hello! I'm Duke\n" + INDENTATION + "What can I do for you?\n" + LINE);
    
    while (true) {
      String command = scan.next();
      if (!command.equals("bye")) {
        System.out.println(LINE + "\n" + INDENTATION + command + "\n" + LINE);
      } else {
        System.out.println(LINE + "\n" + INDENTATION + "Bye. Hope to see you again soon!\n" + LINE);
        break;
      }
    }
  }
}
