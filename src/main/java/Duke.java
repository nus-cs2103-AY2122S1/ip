import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

  private ArrayList<Task> tasks;

  Duke() { this.tasks = new ArrayList<>(); }

  public static void main(String[] args) {
    String logo = " ____        _        \n"
                  + "|  _ \\ _   _| | _____ \n"
                  + "| | | | | | | |/ / _ \\\n"
                  + "| |_| | |_| |   <  __/\n"
                  + "|____/ \\__,_|_|\\_\\___|\n";
    System.out.println("Hello from \n" + logo);
    // System.out.println("\t____________________________");
    // System.out.println("\tHello!, I'm Duke\n\tWhat can I do for you?");
    // System.out.println("\t____________________________");
    Duke duke = new Duke();
    duke.run();
  }

  private void run() {
    Scanner sc = new Scanner(System.in);
    while (sc.hasNextLine()) {
      String input = sc.nextLine();
      System.out.println("\t____________________________");
      input = input.toLowerCase().strip();
      if (input.equals("bye") || input.equals("exit")) {
        this.exit();
        break;
      } else if (input.equals("list")) {
        this.list();
      } else if (input.split(" ")[0].equals("done")) {
        this.markDone(input.split(" ")[1]);
      } else if (input.split(" ")[0].equals("todo")) {
        this.addTodo(input.split(" ", 2)[1]);
      } else if (input.split(" ")[0].equals("deadline")) {
        this.addDeadline(input.split(" ", 2)[1]);
      } else if (input.split(" ")[0].equals("event")) {
        this.addEvent(input.split(" ", 2)[1]);
      }
      System.out.println("\t____________________________");
    }
  }

  private void addTodo(String task) {
    Todo todo = new Todo(task);
    this.tasks.add(todo);
    this.addTask(todo.toString());
  }

  private void addDeadline(String task) {
    String[] taskArray = task.split("/by");
    String title = taskArray[0];
    String time = taskArray[1];
    Deadline deadline = new Deadline(title, time);
    this.tasks.add(deadline);
    this.addTask(deadline.toString());
  }

  private void addEvent(String task) {
    String[] taskArray = task.split("/at");
    String title = taskArray[0];
    String time = taskArray[1];
    Event event = new Event(title, time);
    this.tasks.add(event);
    this.addTask(event.toString());
  }

  private void addTask(String taskString) {
    System.out.println("\tGot it. I\'ve added this task:");
    System.out.println("\t  " + taskString);
    System.out.println("\tNow you have " + this.tasks.size() +
                       " tasks in the list.");
  }

  private void list() {
    for (int i = 0; i < this.tasks.size(); i++) {
      System.out.println("\t" + (i + 1) + ". " + this.tasks.get(i).toString());
    }
  }

  private void exit() {
    System.out.println("\tBye. Hope to see you again soon!");
    System.out.println("\t____________________________");
  }

  private void markDone(String i) {
    try {
      int index = Integer.parseInt(i);
      this.tasks.get(index - 1).markAsDone();
      System.out.println("\tNice! I\'ve marked this task as done:");
      System.out.println(" \t" + this.tasks.get(index - 1).toString());
    } catch (NumberFormatException | IndexOutOfBoundsException e) {
      System.out.println("\tPlease input the index of the task");
    }
  }
}
