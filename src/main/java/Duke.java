import java.util.ArrayList;
import java.util.Scanner;  // Import the Scanner clas

public class Duke {
    public static void main(String[] args) {
        String botName = "HuAI";
        System.out.printf("Hello! I'm %s\n", botName);
        System.out.println("What can I do for you?");
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        TaskList taskList = new TaskList();
        while (true) {
            String command = scanner.nextLine();
            String[] commandSplit = command.split(" ", 2);
            boolean aborted = false;
            switch (commandSplit[0]) {
                case "bye":
                    aborted = true;
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                case "list":
                    ArrayList<Task> tasks = taskList.getTasks();
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.printf("%d. %s\n", i + 1, tasks.get(i));
                    }
                    break;
                case "done":
                    int i = Integer.parseInt(commandSplit[1].trim());
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.printf("%s\n", taskList.markTaskAsDone(i - 1));
                    break;
                case "todo": {
                    System.out.println("Got it. I've added this task: ");
                    String taskName = commandSplit[1].trim();
                    Task newTask = new Todo(taskName);
                    taskList.addTask((newTask));
                    System.out.printf("%s\n", newTask);
                    System.out.printf("Now you have %d tasks in the list.\n", taskList.getSize());
                    break;
                }
                case "deadline": {
                    System.out.println("Got it. I've added this task: ");
                    String[] nameAndTime = commandSplit[1].split("/by");
                    String taskName = nameAndTime[0].trim();
                    String dateTime = nameAndTime[1].trim();
                    Task newTask = new Deadline(taskName, dateTime);
                    taskList.addTask((newTask));
                    System.out.printf("%s\n", newTask);
                    System.out.printf("Now you have %d tasks in the list.\n", taskList.getSize());
                    break;
                }
                case "event": {
                    System.out.println("Got it. I've added this task: ");
                    String[] nameAndTime = commandSplit[1].split("/at");
                    String taskName = nameAndTime[0].trim();
                    String dateTime = nameAndTime[1].trim();
                    Task newTask = new Event(taskName, dateTime);
                    taskList.addTask((newTask));
                    System.out.printf("%s\n", newTask);
                    System.out.printf("Now you have %d tasks in the list.\n", taskList.getSize());
                    break;
                }
                default:
                    break;
            }
            if (aborted) {
                break;
            }
        }
    }
}
