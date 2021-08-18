import java.util.Scanner;

public class Command {
  protected static final String SPLIT_LINE = "\n" +
    "\t══════════════════════════════════════════════════\n";

  public void start() {
    Scanner sc = new Scanner(System.in);
    String cmd = "GREET";
    while (run(cmd)) {
      cmd = sc.nextLine();
    }
    sc.close();
  }

  public boolean run(String cmd) {
    System.out.println(SPLIT_LINE);
    String[] line = cmd.split(" ");
    switch (line[0].toUpperCase()) {
      case "GREET": greet(); break;
      case "ECHO": echo(cmd); break;
      case "BYE": exit(); break;
      case "LIST": list(); break;
      case "DONE": done(Integer.parseInt(line[1])); break;
      case "DELETE": delete(Integer.parseInt(line[1])); break;
      case "TODO": todo(cmd); break;
      case "DEADLINE":
      case "EVENT":
        String desc = cmd.substring(cmd.indexOf(" ") + 1, cmd.indexOf("/") - 1);
        String date = cmd.substring(cmd.indexOf("/") + 4);
        if (line[0].toUpperCase().equals("DEADLINE"))
          deadline(desc, date);
        else event(desc, date);
        break;
      default: add(cmd);
    }
    System.out.println(SPLIT_LINE);
    return !cmd.equals("bye");
  }

  public void greet() {
    String logo =
      "\t ██████╗ ██╗      ██████╗  ██████╗ ███╗   ███╗\n" +
      "\t ██╔══██╗██║     ██╔═══██╗██╔═══██╗████╗ ████║\n" +
      "\t ██████╔╝██║     ██║   ██║██║   ██║██╔████╔██║\n" +
      "\t ██╔══██╗██║     ██║   ██║██║   ██║██║╚██╔╝██║\n" +
      "\t ██████╔╝███████╗╚██████╔╝╚██████╔╝██║ ╚═╝ ██║\n" +
      "\t ╚═════╝ ╚══════╝ ╚═════╝  ╚═════╝ ╚═╝     ╚═╝\n" +
      "\n";
    String greeting = 
      "\t Hello! I'm Bloom\n" + 
      "\t What can I do for you?";
    System.out.println(logo + greeting);
  }

  public void echo(String str) {
    System.out.println("\t " + str);
  }

  public void exit() {
    System.out.println("\t Bye. Hope to see you again soon!");
  }

  public void add(String desc) {
    Task t = new Task(desc);
    Task.tasks.add(t);
    System.out.println("\t added: " + t.description);
  }

  public void list() {
    System.out.println("\t Here are the tasks in your list:");
    for (int i = 1; i <= Task.tasks.size(); ++i) {
      Task t = Task.tasks.get(i-1);
      System.out.println("\t " + i + ". " + t);
    }
  }

  public void done(int i) {
    Task t = Task.tasks.get(i-1);
    t.markAsDone();
    System.out.println(
      "\t Nice! I've marked this task as done:\n" +
      "\t   " + t);
  }

  public void todo(String desc) {
    ToDo td = new ToDo(desc);
    Task.tasks.add(td);
    System.out.println(
      "\t Got it. I've added this task:\n" +
      "\t   " + td + "\n" +
      "\t Now you have " + Task.tasks.size() + " tasks in the list.");
  }

  public void deadline(String desc, String by) {
    Deadline d = new Deadline(desc, by);
    Task.tasks.add(d);
    System.out.println(
      "\t Got it. I've added this task:\n" +
      "\t   " + d + "\n" +
      "\t Now you have " + Task.tasks.size() + " tasks in the list.");
  }

  public void event(String desc, String at) {
    Event e = new Event(desc, at);
    Task.tasks.add(e);
    System.out.println(
      "\t Got it. I've added this task:\n" +
      "\t   " + e + "\n" +
      "\t Now you have " + Task.tasks.size() + " tasks in the list.");
  }

  public void delete(int i) {
    Task t = Task.tasks.remove(i-1);
    System.out.println(
      "\t Noted! I've removed this task:\n" +
      "\t   " + t + "\n" +
      "\t Now you have " + Task.tasks.size() + " tasks in the list.");
  }
}
