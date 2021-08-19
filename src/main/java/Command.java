import java.util.Scanner;

public class Command {
  protected static final String SPLIT_LINE = "\n" +
    "\t--------------------------------------------------\n";

  /**
   * Start Bloom. Handle wrong-command, incomplete-complete-command, 
   * invalid-command exceptions. For now, the bot stops working if 
   * there exist such exceptions.
   */
  public void start() {
    Scanner sc = new Scanner(System.in);
    String cmd = "GREET";
    try {
      while (run(cmd))
        cmd = sc.nextLine();
    } catch (WrongCommandBloomException e) {
      String s = e.getMessage().toUpperCase();
      switch (s) {
        case "ALL":
          System.out.println(
            "\t Do you want to list all task?\n" +
            "\t Please use the list command instead.");
          break;
        case "REMOVE":
          System.out.println(
            "\t Do you want to delete the task?\n" +
            "\t Please use the delete command instead.");
          break;
        case "CREATE":
          System.out.println(
            "\t Do you want to add new task?\n" +
            "\t Please consider todo, deadline, event commands.");
          break;
      }
    } catch (IncompleteCommandBloomException e) {
      String s = e.getMessage().toUpperCase();
      switch (s) {
        case "INDEX":
          System.out.println(
            "\t Please specify which task you want to edit.\n" +
            "\t You can view the task order by the list command.");
          break;
        case "INFO":
          System.out.println(
            "\t Please input the description and date with correct format.\n" +
            "\t For task commands, the format should be as follows:\n" +
            "\t       todo [DESCRIPTION]\n" +
            "\t   deadline [DESCRIPTION] /by [DATE]\n" +
            "\t      event [DESCRIPTION] /at [DATE]");
          break;
      }
    } catch (InvalidCommandBloomException e) {
      System.out.println(
        "\t Sorry, I don't understand that command.\n" +
        "\t Please try again :)");
    } catch (BloomException e) {
      System.out.println(
        "\t Sorry, something went wrong!\n" +
        "\t Bloom has withered :(");
    }
    sc.close();
  }

  /**
   * Run a command line as inputted.
   * @param  cmd            the input com
   * @return true           if there no exception is thrown
   *         false          if an exception is thrown
   * @throws BloomException the exceptions related to the bot
   */
  public boolean run(String cmd) throws BloomException {
    System.out.println(SPLIT_LINE);
    String[] line = cmd.split(" ");
    switch (line[0].toUpperCase()) {
      case "GREET": greet(); break;
      case "BYE": exit(); break;
      case "LIST": list(); break;
      case "DONE":
      case "DELETE":
        try {
          if (line[0].equalsIgnoreCase("DONE"))
            done(Integer.parseInt(line[1]));
          else delete(Integer.parseInt(line[1]));
          break;
        } catch (IndexOutOfBoundsException e) {
          throw new IncompleteCommandBloomException("INDEX");
        }
      case "TODO":
        try {
          int i = cmd.indexOf(" ") + 1;
          if (i == 0) throw new IncompleteCommandBloomException("INFO");
          todo(cmd.substring(i));
          break;
        } catch (StringIndexOutOfBoundsException e) {
          throw new IncompleteCommandBloomException("INFO");
        }
      case "DEADLINE":
      case "EVENT":
        try {
          int i = cmd.indexOf(" ") + 1;
          if (i == 0) throw new IncompleteCommandBloomException("INFO");
          String desc = cmd.substring(i, cmd.indexOf("/") - 1);
          String date = cmd.substring(cmd.indexOf("/") + 4);
          if (line[0].equalsIgnoreCase("DEADLINE"))
            deadline(desc, date);
          else event(desc, date);
          break;
        } catch (StringIndexOutOfBoundsException e) {
          throw new IncompleteCommandBloomException("INFO");
        }
      case "ECHO":
      case "ADD":
        String str = cmd.substring(cmd.indexOf(" ") + 1);
        if (line[0].equalsIgnoreCase("ECHO"))
          echo(str);
        else add(str);
        break;
      case "ALL":
      case "REMOVE":
      case "CREATE": throw new WrongCommandBloomException(line[0]);
      default: throw new InvalidCommandBloomException("");
    }
    System.out.println(SPLIT_LINE);
    return !cmd.equals("bye");
  }

  /**
   * Greet users.
   */
  public void greet() {
    String logo =
      "\t   ____  _                       \n" +
      "\t  |  _ \\| |                      \n" +
      "\t  | |_) | | ___   ___  _ __ ___  \n" +
      "\t  |  _ <| |/ _ \\ / _ \\| '_ ` _ \\ \n" +
      "\t  | |_) | | (_) | (_) | | | | | |\n" +
      "\t  |____/|_|\\___/ \\___/|_| |_| |_|\n" +
      "\n";
    String greeting = 
      "\t Hello! I'm Bloom\n" + 
      "\t What can I do for you?";
    System.out.println(logo + greeting);
  }

  /**
   * Echo whatever texts inputted.
   * @param str the text input
   */
  public void echo(String str) {
    System.out.println("\t " + str);
  }

  /**
   * Say goodbye.
   * NOTE: only for testing purpose therefore
   *       exception handling is not supported.
   */
  public void exit() {
    System.out.println("\t Bye. Hope to see you again soon!");
  }

  /**
   * Add new task.
   * NOTE: only for testing purpose therefore
   *       exception handling is not supported.
   * @param desc the task description
   */
  public void add(String desc) {
    Task t = new Task(desc);
    Task.tasks.add(t);
    System.out.println("\t added: " + t.description);
  }

  /**
   * List all tasks.
   */
  public void list() {
    System.out.println("\t Here are the tasks in your list:");
    for (int i = 1; i <= Task.tasks.size(); ++i) {
      Task t = Task.tasks.get(i-1);
      System.out.println("\t " + i + ". " + t);
    }
  }

  /**
   * Mark one task as done.
   * @param i the order of the task as listed
   */
  public void done(int i) {
    Task t = Task.tasks.get(i-1);
    t.markAsDone();
    System.out.println(
      "\t Nice! I've marked this task as done:\n" +
      "\t   " + t);
  }

  /**
   * Add new to-do task.
   * @param desc the task description
   */
  public void todo(String desc) {
    ToDo td = new ToDo(desc);
    Task.tasks.add(td);
    System.out.println(
      "\t Got it. I've added this task:\n" +
      "\t   " + td + "\n" +
      "\t Now you have " + Task.tasks.size() + " tasks in the list.");
  }

  /**
   * Add new deadline task.
   * @param desc the task description
   * @param by   the date by which the task should be completed
   */
  public void deadline(String desc, String by) {
    Deadline d = new Deadline(desc, by);
    Task.tasks.add(d);
    System.out.println(
      "\t Got it. I've added this task:\n" +
      "\t   " + d + "\n" +
      "\t Now you have " + Task.tasks.size() + " tasks in the list.");
  }

  /**
   * Add new event task.
   * @param desc the task description
   * @param at   the date at which the task is happening
   */
  public void event(String desc, String at) {
    Event e = new Event(desc, at);
    Task.tasks.add(e);
    System.out.println(
      "\t Got it. I've added this task:\n" +
      "\t   " + e + "\n" +
      "\t Now you have " + Task.tasks.size() + " tasks in the list.");
  }

  /**
   * Delete one task.
   * @param i the order of the task as listed
   */
  public void delete(int i) {
    Task t = Task.tasks.remove(i-1);
    System.out.println(
      "\t Noted! I've removed this task:\n" +
      "\t   " + t + "\n" +
      "\t Now you have " + Task.tasks.size() + " tasks in the list.");
  }
}
