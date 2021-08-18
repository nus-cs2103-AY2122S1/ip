import java.util.Scanner;
import java.util.ArrayList;

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
    ArrayList<String> userText = new ArrayList<>();
    String LINE = "    --------------------------------------------------";
    String INDENTATION = "      ";
    Scanner scan = new Scanner(System.in);
    // abstract out printing of response
    System.out.println(LINE + "\n" + INDENTATION + "Hello! I'm Duke\n" + INDENTATION + "What can I do for you?\n" + LINE);
    
    while (true) {
      String command = scan.nextLine();
      if (command.equals("list")) {
        System.out.println(LINE);
        for (int i = 0; i < userText.size(); i++) {
          int tempNum = i + 1;
          System.out.println(INDENTATION + tempNum  + ". " + userText.get(i));
        }
        System.out.println(LINE);
      } else if (command.equals("bye")) {
        System.out.println(LINE + "\n" + INDENTATION + "Bye. Hope to see you again soon!\n" + LINE);
        break;
      } else {
        userText.add(command);
        System.out.println(LINE + "\n" + INDENTATION + "added: " + command + "\n" + LINE);
      }
    }
  }
}
