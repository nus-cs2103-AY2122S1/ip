import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

  private ArrayList<Task> tasks;

  Duke() { this.tasks = new ArrayList<>(); }

  public static void main(String[] args) {
    String logo = " ____        _\n"
                  + "|  _ \\ _   _| | _____\n"
                  + "| | | | | | | |/ / _ \\\n"
                  + "| |_| | |_| |   <  __/\n"
                  + "|____/ \\__,_|_|\\_\\___|\n";
    System.out.println("Hello from\n" + logo);
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
      input = input.strip();
      String[] splitInput = input.split(" ", 2);
      try {

        if (input.equals("bye") || input.equals("exit")) {
          this.exit();
          break;
        } else if (input.equals("list")) {
          this.list();
        } else if (splitInput[0].equals("done")) {
          this.markDone(splitInput);
        } else if (splitInput[0].equals("todo") || splitInput[0].equals("deadline") || splitInput[0].equals("event")) {
            this.addTask(splitInput);
        } else {
          System.out.println("\tSorry, I do not know this command!");
        }
      } catch (DukeTaskDetailsException | DukeDoneInputException e) {
        System.out.println("\t" + e.toString());
      }
      System.out.println("\t____________________________");
    }
  }

  private void addTask(String[] taskArray) throws DukeTaskDetailsException {
    Task task;
    if (taskArray.length < 2) {
      throw new DukeTaskDetailsException("Please provide task details");
    }
    if (taskArray[0].equals("todo")) {
      task = new Todo(taskArray[1]);
    } else if (taskArray[0].equals("deadline")) {
      String[] deadlineDetails = taskArray[1].split("/by");
      if (deadlineDetails.length != 2) {
        throw new DukeTaskDetailsException("Please provide both task title and deadline, separated by \"/by\"");
      }
      task = new Deadline(deadlineDetails[0], deadlineDetails[1]);
    } else {
      String[] eventDetails = taskArray[1].split("/at");
      if (eventDetails.length != 2) {
        throw new DukeTaskDetailsException("Please provide both event title and date, separated by \"/at\"");
      }
      task = new Event(eventDetails[0], eventDetails[1]);
    }
    this.tasks.add(task);
     System.out.println("\tGot it. I\'ve added this task:");
    System.out.println("\t  " + task.toString());
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

  private void markDone(String[] taskArray) throws DukeDoneInputException {
    if (taskArray.length < 2) {
      throw new DukeDoneInputException("Please enter index of the task to mark done");
    }
    try {
      int index = Integer.parseInt(taskArray[1]);
      this.tasks.get(index - 1).markAsDone();
      System.out.println("\tNice! I\'ve marked this task as done:");
      System.out.println(" \t" + this.tasks.get(index - 1).toString());
    } catch (NumberFormatException e){
      throw new DukeDoneInputException("Please enter index of the task to mark done");
    } catch (IndexOutOfBoundsException e) {
      throw new DukeDoneInputException("Sorry! Unable to find task number " + taskArray[1]);
    }
  }
}
