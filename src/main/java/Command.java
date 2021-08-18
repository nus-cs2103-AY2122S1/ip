import java.util.Scanner;

public class Command {
  private static final String SPLIT_LINE = "\n" +
    "\t══════════════════════════════════════════════════\n";

  public void greet() {
    String logo = "\n" +
      "\t ██████╗ ██╗      ██████╗  ██████╗ ███╗   ███╗\n" +
      "\t ██╔══██╗██║     ██╔═══██╗██╔═══██╗████╗ ████║\n" +
      "\t ██████╔╝██║     ██║   ██║██║   ██║██╔████╔██║\n" +
      "\t ██╔══██╗██║     ██║   ██║██║   ██║██║╚██╔╝██║\n" +
      "\t ██████╔╝███████╗╚██████╔╝╚██████╔╝██║ ╚═╝ ██║\n" +
      "\t ╚═════╝ ╚══════╝ ╚═════╝  ╚═════╝ ╚═╝     ╚═╝\n" +
      "\n";
    String greeting = 
      "\t Hello! I'm Bloom\n" + 
      "\t What can I do for you?\n";
    System.out.println(SPLIT_LINE + logo + greeting + SPLIT_LINE);
  }

  public void echo() {
    Scanner sc = new Scanner(System.in);
    String cmd = sc.nextLine();
    while (!cmd.equals("bye")) {
      System.out.println(SPLIT_LINE + "\t " + cmd + SPLIT_LINE);
      cmd = sc.nextLine();
    }
    sc.close();
    this.exit();
  }

  public void exit() {
    String message = "\t Bye. Hope to see you again soon!";
    System.out.println(SPLIT_LINE + message + SPLIT_LINE);
  }
}
