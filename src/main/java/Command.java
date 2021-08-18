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

  protected boolean run(String cmd) {
    System.out.println(SPLIT_LINE);
    String[] line = cmd.split(" ");
    switch (line[0].toUpperCase()) {
      case "GREET": greet(); break;
      case "BYE": exit(); break;
      case "ECHO": echo(cmd); break;
      case "LIST": list(); break;
      case "DONE": done(Integer.parseInt(line[1])); break;
      default: add(cmd);
    }
    System.out.println(SPLIT_LINE);
    return !cmd.equals("bye");
  }

  protected void greet() {
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

  protected void echo(String str) {
    System.out.println("\t " + str);
  }

  protected void exit() {
    System.out.println("\t Bye. Hope to see you again soon!");
  }

  protected void add(String str) {
    Task t = new Task(str);
    Task.tasks.add(t);
    System.out.println("\t added: " + t.description);
  }

  protected void list() {
    System.out.println("\t Here are the tasks in your list:");
    for (int i = 1; i <= Task.tasks.size(); ++i) {
      Task t = Task.tasks.get(i-1);
      System.out.println("\t " + i + ". [" +
        t.getStatusIcon() + "] " +
        t.description);
    }
  }

  protected void done(int i) {
    Task t = Task.tasks.get(i-1);
    t.markAsDone();
    System.out.println(
      "\t Nice! I've marked this task as done:\n" +
      "\t   [" + t.getStatusIcon() + "] " + t.description);
  }
}
