import java.util.ArrayList;
import java.util.Scanner;

public class Task {
  private static final String SPLIT_LINE = "\n" +
    "\t══════════════════════════════════════════════════\n";

  private static ArrayList<String> tasks;

  public Task() {
    tasks = new ArrayList<>();
  }

  public void add() {
    Scanner sc = new Scanner(System.in);
    String task = sc.nextLine();
    while (!task.equals("bye")) {
      if (task.equals("list")) {
        this.list();
        task = sc.nextLine();
        continue;
      }
      tasks.add(task);
      System.out.println(SPLIT_LINE + "\t added: " + task + SPLIT_LINE);
      task = sc.nextLine();
    }
    sc.close();
    (new Command()).exit();
  }

  public void list() {
    System.out.println(SPLIT_LINE);
    for (int i = 1; i <= tasks.size(); ++i) {
      System.out.println("\t " + i + ". " + tasks.get(i-1));
    }
    System.out.println(SPLIT_LINE);
  }
}
