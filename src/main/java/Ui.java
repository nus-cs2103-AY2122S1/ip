import java.util.Scanner;

public final class Ui{
  private final static String LOGO = " ____        _        \n"
          + "|  _ \\ _   _| | _____ \n"
          + "| | | | | | | |/ / _ \\\n"
          + "| |_| | |_| |   <  __/\n"
          + "|____/ \\__,_|_|\\_\\___|\n";
  private final static String separator = "     _______________________________________________________";

  private final Scanner sc;

  public Ui() {
    this.sc = new Scanner(System.in);
  }

  public static void welcomeMessage() {
    System.out.println("Hello from\n" + LOGO);
    System.out.println(separator);
    System.out.println("     Hi! I am Duke!\n" + "     What can I do for you?");
    System.out.println(separator);
  }

  public String readLine() {
    String s = " ";
    if (sc.hasNext()) {
      s = sc.nextLine();
    }
    return s;
  }

  public static void showInput(String ... s) {
    System.out.println(separator);
    for (String str : s) {
      System.out.println("     " + str);
    }
    System.out.println(separator);
  }

  public static void showAsUserInput(String s) {
    System.out.println(s);
  }

  public static void showExitMessage() {
    showInput("Bye. Hope to see you again soon!");
  }

  /**
   * To display commands to help user with input as much as possible
   */
  public static void helperMessage() {
    System.out.println(separator);
    System.out.println("     The following can be used:");
    System.out.println("     Types of tasks: 'todo', 'deadline', 'event'");
    System.out.println("     If you wish to add a task," +
            " please input in the form: '<Type of Task> <Name of Task>'" +
            " and include keyword '/at' OR '/by' followed by <Date> if relevant.");
    System.out.println("     If you wish to delete a task, "
            + "please input in the form: 'delete <task index>'.");
    System.out.println("     If you wish to see the current tasks, please input 'list'.");
    System.out.println("     If you wish to mark a task as done, please input 'done <task index>.'");
    System.out.println("     If you wish to terminate the program, please input 'bye'.");
    System.out.println("     If you wish to check items due on a particular day, please " +
            "input 'due YYYY/MM/DD'.");
    System.out.println(separator);
  }
}
